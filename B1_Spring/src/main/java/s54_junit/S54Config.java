package s54_junit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S54Config {

    @Bean
    public String name() {
        return "spring 集成Junit";
    }
    @Bean
    public int age() {
        return 30;
    }
}
