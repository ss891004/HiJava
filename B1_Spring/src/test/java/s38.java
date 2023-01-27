import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-quartz.xml")
public class s38 {

    @Test
    public void test1() throws InterruptedException {
        System.out.println("hello~~~s38");
        Thread.sleep(60000);
    }

    @Test
    public void test2() throws SchedulerException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-quartz.xml");

        StdScheduler ss = (StdScheduler) ac.getBean("scheduler");

        // 进行对任务的暂停，删除，新增相关操作
        System.out.println(ss.getContext().toString());

    }
}
