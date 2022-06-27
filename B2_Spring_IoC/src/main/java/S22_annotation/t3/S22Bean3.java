package S22_annotation.t3;


import S22_annotation.t3.m1.ScanBean1;
import S22_annotation.t3.m2.ScanBean2;
import org.springframework.context.annotation.Import;

// 通过@Import导入每个模块中的组件扫描类
@Import({ScanBean1.class, ScanBean2.class})
public class S22Bean3 {
}
