import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import s54_junit.MathUtils;
import s54_junit.S54Config;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {S54Config.class})
public class s54_junit2 {
    @BeforeClass
    public static void bc() {
        System.out.println("@BeforeClass");
        System.out.println("-----------------");
    }

    @AfterClass
    public static void ac() {
        System.out.println("@AfterClass");
    }

    @Before
    public void bf() {
        System.out.println("@Before:" + this);
    }

    @After
    public void af() {
        System.out.println("@After:" + this);
        System.out.println("##################");
    }

    @Test
    public void max() throws Throwable {
        System.out.println("max():" + this);
        int result = MathUtils.max(1, 2, 3);
        //判断测试结果和我们期望的结果是否一致
        Assert.assertEquals(result, 3);
    }

    @Test
    public void min() throws Exception {
        System.out.println("min():" + this);
        int result = MathUtils.min(1, 2, 3);
        //判断测试结果和我们期望的结果是否一致
        Assert.assertEquals(result, 1);
    }

    //方法运行时间超过了timeout，表示测试用例运行失败
    @Test(timeout = 1000)
    public void timeOutTest() throws InterruptedException {
        System.out.println("timeOutTest():" + this);
        TimeUnit.SECONDS.sleep(2000);
    }

    //方法若未抛出expected指定的异常，表示测试用例运行失败
    @Test(expected = NullPointerException.class)
    public void expectedTest() {
        System.out.println("expectedTest():" + this);
        new RuntimeException("异常不匹配");
    }

    @Test
    @Ignore
    public void ignoredMethod() {
        System.out.println("我是被忽略的方法");
    }

//    public static void main(String[] args) {
//        Result result = JUnitCore.runClasses(s54_junit2.class);
//        System.out.println("+++++++++++++++");
//        System.out.println("运行测试用例个数:" + result.getRunCount());
//        System.out.println("失败用例个数:" + result.getFailures().size());
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure);
//        }
//        System.out.println("运行测试用例总耗时(ms):" + result.getRunTime());
//        System.out.println("测试用例是否都成功了：" + result.wasSuccessful());
//    }
}
