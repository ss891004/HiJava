import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
import s30_beanLife.Car;
import s30_beanLife.CompositeObj;
import s30_beanLife.User;

import java.util.Arrays;

public class S30_BeanLife {

//阶段1：Bean元信息配置阶段
    //API的方式
    //Xml文件方式
    //properties文件的方式
    //注解的方式

//阶段2：Bean元信息解析阶段
    // Bean元信息的解析就是将各种方式定义的bean配置信息解析为BeanDefinition对象。
        // xml文件定义bean的解析
        // properties文件定义bean的解析
        // 注解方式定义bean的解析

//阶段3：将Bean注册到容器中
//阶段4：BeanDefinition合并阶段
//阶段5：Bean Class加载阶段
//阶段6：Bean实例化阶段（2个小阶段）
    //Bean实例化前阶段
    //Bean实例化阶段

//阶段7：合并后的BeanDefinition处理
//阶段8：属性赋值阶段（3个小阶段）
    //Bean实例化后阶段
    //Bean属性赋值前阶段
    //Bean属性赋值阶段
//阶段9：Bean初始化阶段（5个小阶段）
    //Bean Aware接口回调阶段
    //Bean初始化前阶段
    //Bean初始化阶段
    //Bean初始化后阶段

//阶段10：所有单例bean初始化完成后阶段
//阶段11：Bean的使用阶段
//阶段12：Bean销毁前阶段
//阶段13：Bean销毁阶段


    // 组装一个简单的bean
    // <bean class="com.javacode2018.lesson002.demo1.Car" />
    @Test
    public void test1() {
        //指定class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        //获取BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);
    }


    // 组装一个有属性的bean
    @Test
    public void test2() {
        //指定class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        //设置普通类型属性
        beanDefinitionBuilder.addPropertyValue("name", "奥迪"); //@1
        //获取BeanDefinition
        BeanDefinition carBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(carBeanDefinition);

        //创建spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory(); //@2
        //调用registerBeanDefinition向容器中注册bean
        factory.registerBeanDefinition("car", carBeanDefinition); //@3
        Car bean = factory.getBean("car", Car.class); //@4
        System.out.println(bean);
    }


    // 组装一个有依赖关系的bean
    /*
<bean id="car" class="com.javacode2018.lesson002.demo1.Car">
    <property name="name" value="奥迪"/>
</bean>
<bean id="user" class="com.javacode2018.lesson002.demo1.User">
    <property name="name" value="路人甲Java"/>
    <property name="car" ref="car"/>
</bean>
* */
    @Test
    public void test3() {
        //先创建car这个BeanDefinition
        BeanDefinition carBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName()).addPropertyValue("name", "奥迪").getBeanDefinition();
        //创建User这个BeanDefinition
        BeanDefinition userBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class.getName()).
                addPropertyValue("name", "路人甲Java").
                addPropertyReference("car", "car"). //@1
                        getBeanDefinition();
        //创建spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //调用registerBeanDefinition向容器中注册bean
        factory.registerBeanDefinition("car", carBeanDefinition);
        factory.registerBeanDefinition("user", userBeanDefinition);
        System.out.println(factory.getBean("car"));
        System.out.println(factory.getBean("user"));
    }

    // 来2个有父子关系的bean
    /*
    <bean id="car1" class="com.javacode2018.lesson002.demo1.Car">
        <property name="name" value="保时捷"/>
    </bean>
    <bean id="car2" parent="car1" />
    */
    @Test
    public void test4() {
        //先创建car这个BeanDefinition
        BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "保时捷").
                getBeanDefinition();
        BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder.
                genericBeanDefinition(). //内部生成一个GenericBeanDefinition对象
                        setParentName("car1"). //@1：设置父bean的名称为car1
                        getBeanDefinition();
        //创建spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //调用registerBeanDefinition向容器中注册bean
        //注册car1->carBeanDefinition1
        factory.registerBeanDefinition("car1", carBeanDefinition1);
        //注册car2->carBeanDefinition2
        factory.registerBeanDefinition("car2", carBeanDefinition2);
        //从容器中获取car1
        System.out.println(String.format("car1->%s", factory.getBean("car1")));
        //从容器中获取car2
        System.out.println(String.format("car2->%s", factory.getBean("car2")));
    }

    // 通过api设置（Map、Set、List）属性
    @Test
    public void test5() {
        //定义car1
        BeanDefinition car1 = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "奥迪").
                getBeanDefinition();
        //定义car2
        BeanDefinition car2 = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "保时捷").
                getBeanDefinition();
        //定义CompositeObj这个bean
        //创建stringList这个属性对应的值
        ManagedList<String> stringList = new ManagedList<>();
        stringList.addAll(Arrays.asList("java高并发系列", "mysql系列", "maven高手系列"));
        //创建carList这个属性对应的值,内部引用其他两个bean的名称[car1,car2]
        ManagedList<RuntimeBeanReference> carList = new ManagedList<>();
        carList.add(new RuntimeBeanReference("car1")); //用来表示bean引用类型，类似于xml中的ref
        carList.add(new RuntimeBeanReference("car2"));
        //创建stringList这个属性对应的值
        ManagedSet<String> stringSet = new ManagedSet<>();
        stringSet.addAll(Arrays.asList("java高并发系列", "mysql系列", "maven高手系列"));
        //创建carSet这个属性对应的值,内部引用其他两个bean的名称[car1,car2]
        ManagedList<RuntimeBeanReference> carSet = new ManagedList<>();
        carSet.add(new RuntimeBeanReference("car1"));
        carSet.add(new RuntimeBeanReference("car2"));
        //创建stringMap这个属性对应的值
        ManagedMap<String, String> stringMap = new ManagedMap<>();
        stringMap.put("系列1", "java高并发系列");
        stringMap.put("系列2", "Maven高手系列");
        stringMap.put("系列3", "mysql系列");
        ManagedMap<String, RuntimeBeanReference> stringCarMap = new ManagedMap<>();
        stringCarMap.put("car1", new RuntimeBeanReference("car1"));
        stringCarMap.put("car2", new RuntimeBeanReference("car2"));
        //下面我们使用原生的api来创建BeanDefinition
        GenericBeanDefinition compositeObj = new GenericBeanDefinition();
        compositeObj.setBeanClassName(CompositeObj.class.getName());
        compositeObj.getPropertyValues().add("name", "路人甲Java").
                add("salary", 50000).
                add("car1", new RuntimeBeanReference("car1")).
                add("stringList", stringList).
                add("carList", carList).
                add("stringSet", stringSet).
                add("carSet", carSet).
                add("stringMap", stringMap).
                add("stringCarMap", stringCarMap);
        //将上面bean 注册到容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car1", car1);
        factory.registerBeanDefinition("car2", car2);
        factory.registerBeanDefinition("compositeObj", compositeObj);
        //下面我们将容器中所有的bean输出
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.println(String.format("%s->%s", beanName, factory.getBean(beanName)));
        }
    }


}
