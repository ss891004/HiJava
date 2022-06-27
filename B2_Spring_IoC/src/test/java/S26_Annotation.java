import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s25_annotation.S25Bean1;
import s25_annotation.S25Bean2;
import s26_annotation.DbConfig;
import s26_annotation.MainConfig;

public class S26_Annotation {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 1. 使用@PropertySource注解引入配置文件
        context.register(MainConfig.class);
        context.refresh();

        // 2. 使用@Value注解引用配置文件的值
        DbConfig dbConfig = context.getBean(DbConfig.class);
        System.out.println(dbConfig);
    }

}