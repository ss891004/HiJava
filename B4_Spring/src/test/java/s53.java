import hm.s53_multi_db.config.Db1Config;
import hm.s53_multi_db.config.Db2Config;
import hm.s53_multi_db.model.User1Model;
import hm.s53_multi_db.model.User2Model;
import hm.s53_multi_db.service.UserService;
import hm.s53_mybatis.IUserService;
import hm.s53_mybatis.MainConfig;
import hm.s53_mybatis.UserModel;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Spring集成MyBatis
 */
public class s53 {

    // 1、方式1：mapper xml文件放在resource目录，和Mapper接口不在一个目录的情况
    // 2、方式2：mapper xml文件和Mapper接口在同一个目录
    @Test
    public void insert() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        IUserService userService = context.getBean(IUserService.class);
        UserModel userModel = UserModel.builder().name("20220917").build();
        userService.insert(userModel);
        System.out.println(userModel);
    }

    @Test
    public void getList() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        IUserService userService = context.getBean(IUserService.class);
        List<UserModel> userModelList = userService.getList();
        System.out.println(userModelList);
    }



    //每个数据源对应一个SqlSessionFactory，@MapperScan注解中可以通过sqlSessionTemplateRef来指定SqlSessionFactory的bean名称。

    /////////////////////////////////////////////////////////
    //多个数据源



}