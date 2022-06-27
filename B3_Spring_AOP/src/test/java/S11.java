import hm.s11_schedule.MainConfig1;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class S11 {


    @Test
    public void test1() throws InterruptedException {
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext();
        aca.register(MainConfig1.class);
        aca.refresh();

        Thread.sleep(50000);
    }
}
