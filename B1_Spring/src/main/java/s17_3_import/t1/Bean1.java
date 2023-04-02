package s17_3_import.t1;

import org.springframework.context.annotation.Import;

//value为普通的类
@Import({Service1.class,Service2.class})
public class Bean1 {
}
