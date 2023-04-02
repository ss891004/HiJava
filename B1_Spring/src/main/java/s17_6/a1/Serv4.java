package s17_6.a1;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
public class Serv4 {

    //  优先创建 s2， 再创建s1
    @Bean
    @DependsOn({"s2"})
    public Serv1 s1(){
        System.out.println("s1-----");
        return new Serv1();
    }

    @Bean
    public Serv2 s2(){
        System.out.println("s2------");
        return  new Serv2();
    }

}
