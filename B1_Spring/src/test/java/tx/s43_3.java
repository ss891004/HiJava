import s43_tx.MainConfig3;
import s43_tx.MainConfig4;
import s43_tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s43_tx.UserService4;

public class s43_3 {

    //  声明式事务
//  配置文件的方式，即在spring xml文件中进行统一配置，开发者基本上就不用关注事务的事情了，代码中无需关心任何和事务相关的代码，一切交给spring处理。
//  注解的方式，只需在需要spring来帮忙管理事务的方法上加上@Transaction注解就可以了，注解的方式相对来说更简洁一些，都需要开发者自己去进行配置，

    @Test
    public void test1() {
        //1、启用Spring的注释驱动事务管理功能
        //2、定义事务管理器
        //3、需使用事务的目标上加@Transaction注解
//            @Transaction放在接口上，那么接口的实现类中所有public都被spring自动加上事务
//            @Transaction放在类上，那么当前类以及其下无限级子类中所有pubilc方法将被spring自动加上事务
//            @Transaction放在public方法上，那么该方法将被spring自动加上事务
//            注意：@Transaction只对public方法有效

        //4、执行db业务操作


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
