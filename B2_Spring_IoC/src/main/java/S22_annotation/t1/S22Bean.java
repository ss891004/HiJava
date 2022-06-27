package S22_annotation.t1;

import org.springframework.context.annotation.Import;

//value为普通的类
@Import({Service1.class,Service2.class})
public class S22Bean {
}
