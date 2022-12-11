import hm.s43_tx.MainConfig4;
import hm.s43_tx.UserService4;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class s44 {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig4.class);
        context.refresh();
        UserService4 userService4 = context.getBean(UserService4.class);
        //先执行插入操作
        int count = userService4.insertBatch(
                "java高并发系列",
                "mysql系列",
                "maven系列",
                "mybatis系列");
        System.out.println("插入成功（条）：" + count);
        //然后查询一下
        System.out.println(userService4.userList());
    }

}
