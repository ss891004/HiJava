package ioc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s01_xml.ThreadScope;

import java.util.concurrent.TimeUnit;


//bean作用域scope
public class S01_Bean_Scope {

    //singleton
   /*
   当scope的值设置为singleton的时候，整个spring容器中只会存在一个bean实例，
   通过容器多次查找bean的时候（调用BeanFactory的getBean方法或者bean之间注入依赖的bean对象的时候），
   返回的都是同一个bean对象，singleton是scope的默认值，所以spring容器中默认创建的bean对象是单例的，
   通常spring容器在启动的时候，会将scope为singleton的bean创建好放在容器中
   （有个特殊的情况，当bean的lazy被设置为true的时候，表示懒加载，那么使用的时候才会创建），用的时候直接返回。
   */

    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        System.out.println("spring容器准备启动.....");
        //1.bean配置文件位置
        String beanXml = "classpath:s01-1.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        this.context = new ClassPathXmlApplicationContext(beanXml);
        System.out.println("spring容器启动完毕！");
    }

    /**
     * 单例bean
     */
    @Test
    public void singletonBean() {
        System.out.println("---------单例bean，每次获取的bean实例都一样---------");
        System.out.println(context.getBean("singletonBean"));
        System.out.println(context.getBean("singletonBean"));
        System.out.println(context.getBean("singletonBean"));
    }

    //prototype 表示这个bean是多例的，通过容器每次获取的bean都是不同的实例，每次获取都会重新创建一个bean实例对象。

    @Test
    public void prototypeBean() {
        System.out.println("---------多例bean，每次获取的bean实例都不一样---------");
        System.out.println(context.getBean("prototypeBean"));
        System.out.println(context.getBean("prototypeBean"));
        System.out.println(context.getBean("prototypeBean"));
    }


    //自定义scope
    @Test
    public void threadBean() throws InterruptedException {
        String beanXml = "classpath:s01-1.xml";
        //手动创建容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext() {
            @Override
            protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
                //向容器中注册自定义的scope
                beanFactory.registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());//@1
                super.postProcessBeanFactory(beanFactory);
            }
        };
        //设置配置文件位置
        context.setConfigLocation(beanXml);
        //启动容器
        context.refresh();
        //使用容器获取bean
        //bean在同样的线程中获取到的是同一个bean的实例，不同的线程中bean的实例是不同的。
        for (int i = 0; i < 2; i++) {//@2
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + context.getBean("threadBean"));
                System.out.println(Thread.currentThread() + "," + context.getBean("threadBean"));
            }).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }


}
