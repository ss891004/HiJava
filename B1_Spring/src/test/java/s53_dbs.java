import  s53_dbs.Db1Config;
import  s53_dbs.Db2Config;
import s53_dbs.model.User1Model;
import s53_dbs.model.User2Model;
import s53_dbs.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring集成多数据源
 */
public class s53_dbs {

    //每个db对应一个模块，通过包区分不同的模块，每个模块中指定一个spring的配置类，
    // 配置类需配置3个bean：数据源、事务管理器、SqlSessionFactory，
    // 下面是一个模块的spring配置类，注意下面代码的@MapperScan注解，
    // 当系统中有多个sqlSessionFactory的时候需要用过sqlSessionFactoryRef属性指定了sqlSessionFactory的bean名称。

    // 每个db对应一个datasource，每个datasource需要指定一个事务管理器，
    // 通过@Transaction注解的transactionManager属性指定事务管理器。

    //多个数据源
    @Test
    public void insert() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Db1Config.class);
        UserService userService = context.getBean(UserService.class);
        User1Model userModel = User1Model.builder().name("20220917").build();
        userService.insert(userModel);
        System.out.println(userModel);

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(Db2Config.class);
        UserService userService2 = context2.getBean(UserService.class);
        User2Model userModel2 = User2Model.builder().name("20220917").build();
        userService2.insert2(userModel2);
        System.out.println(userModel2);

    }
}