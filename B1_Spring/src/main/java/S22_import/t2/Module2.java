package S22_import.t2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module2 {

    @Bean
    public String m2()
    {
        return "我是模块2配置类！";
    }
}
