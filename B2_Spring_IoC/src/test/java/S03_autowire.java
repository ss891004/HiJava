import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-autowire.xml")
public class S03_autowire {


    @Test
    public void Test1() {
        //自动注入是采用约定大约配置的方式来实现的，程序和spring容器之间约定好，遵守某一种都认同的规则，来实现自动注入。

        // 3种自动注入方式详解及案例
        // 按名称自动注入
        // 按类型自动注入
        // 按构造器进行自动注入

        // 按类型自动注入某种类型的所有bean给List和Map（重点）

        System.out.printf("......");
    }

}
