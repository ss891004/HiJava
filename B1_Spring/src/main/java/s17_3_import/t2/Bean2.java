package s17_3_import.t2;

import org.springframework.context.annotation.Import;

//value为@Configuration标注的配置类
@Import({Module1.class,Module2.class})
public class Bean2 {
}
