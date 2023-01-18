import hm.s53_dbs.Db1Config;
import hm.s53_dbs.Db2Config;
import hm.s53_dbs.model.User1Model;
import hm.s53_dbs.model.User2Model;
import hm.s53_dbs.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring集成多数据源
 */
public class s53_dbs {

    //多个数据源
    @Test
    public void insert() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Db1Config.class);
        UserService userService = context.getBean(UserService.class);
        User1Model userModel = User1Model.builder().name("20220917").build();
        userService.insert(userModel);
        System.out.println(userModel);

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(Db2Config.class);
        UserService userService2 = context.getBean(UserService.class);
        User2Model userModel2 = User2Model.builder().name("20220917").build();
        userService2.insert2(userModel2);
        System.out.println(userModel2);

    }
}