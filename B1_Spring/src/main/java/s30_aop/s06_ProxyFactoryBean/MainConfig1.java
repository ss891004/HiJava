package s30_aop.s06_ProxyFactoryBean;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//在spring容器中注册上面这个类的bean，名称为service1，通过代理的方式来对这个bean进行增强，来2个通知
//一个前置通知：在调用service1中的任意方法之前，输出一条信息：准备调用xxxx方法
//一个环绕通知：复制统计所有方法的耗时。

@Configuration
public class MainConfig1 {

    //注册目标对象
    @Bean
    public Service1 service1() {
        return new Service1();
    }
    //注册一个前置通知
    @Bean
    public MethodBeforeAdvice beforeAdvice() {
        return (method, args, target) -> System.out.println("准备调用：" + method);
    }
    //注册一个后置通知
    @Bean
    public MethodInterceptor costTimeInterceptor() {
        return invocation -> {
            long starTime = System.nanoTime();
            Object result = invocation.proceed();
            long endTime = System.nanoTime();
            System.out.println(invocation.getMethod() + ",耗时(纳秒)：" + (endTime - starTime));
            return result;
        };
    }
    //注册ProxyFactoryBean
    @Bean
    public ProxyFactoryBean service1Proxy() {
        //1.创建ProxyFactoryBean
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //2.设置目标对象的bean名称
        proxyFactoryBean.setTargetName("service1");
        //3.设置拦截器的bean名称列表，此处2个（advice1和advice2)
        proxyFactoryBean.setInterceptorNames("beforeAdvice", "costTimeInterceptor");
        return proxyFactoryBean;
    }
}
