package s24_annotation.p1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {

    @Bean
    public IServ1 b1(){
       return new Serv01();
    }

    @Bean
    @Primary
    public IServ1 b2(){
        return new Serv0();
    }

}
