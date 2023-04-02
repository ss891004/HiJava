package s17_3_import.t2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module1 {
    @Bean
    public String m1()
    {
        return "我是模块1配置类！";
    }
}
