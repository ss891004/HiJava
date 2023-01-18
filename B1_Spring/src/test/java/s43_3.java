import hm.s43_tx.MainConfig3;
import hm.s43_tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class s43_3 {

    // 方法三， 使用编程式事务
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        UserService userService = context.getBean(UserService.class);
        userService.bus1();
        System.out.println(userService.userList());
    }
}
