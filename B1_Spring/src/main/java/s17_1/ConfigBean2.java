package s17_1;

import org.springframework.context.annotation.Bean;

//@Configuration
public class ConfigBean2 {

    // 被@Bean修饰的方法都只被调用了一次

    @Bean
    public ServA servA() {
        System.out.println("调用serviceA()方法"); //@0
        return new ServA();
    }

    // servB1 和servB2 使用同一个servA实例

    // 被@Configuration修饰的类，spring容器中会通过cglib给这个类创建一个代理，代理会拦截所有被@Bean修饰的方法，
    // 默认情况（bean为单例）下确保这些方法只被调用一次，从而确保这些bean是同一个bean，即单例的。

    @Bean
    public ServB servB1() {
        System.out.println("servB1()方法");
        ServA sa = this.servA();
        return new ServB(sa);

    }

    @Bean
    public ServB servB2() {
        System.out.println("servB2()方法");
        ServA sa = this.servA();
        return new ServB(sa);

    }

}
