package s25_annotation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
public class Serv4 {

    // 和@Bean一起标注在方法上
    @Bean
    @DependsOn({"s2"})
    public Serv1 s1(){
        return new Serv1();
    }

    @Bean
    public Serv2 s2(){
        return  new Serv2();
    }

}
