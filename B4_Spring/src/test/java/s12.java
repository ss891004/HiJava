public class s12 {


    //每个db对应一个模块，通过包区分不同的模块，每个模块中指定一个spring的配置类，
    // 配置类需配置3个bean：数据源、事务管理器、SqlSessionFactory，
    // 下面是一个模块的spring配置类，注意下面代码的@MapperScan注解，
    // 当系统中有多个sqlSessionFactory的时候需要用过sqlSessionFactoryRef属性指定了sqlSessionFactory的bean名称。

    // 每个db对应一个datasource，每个datasource需要指定一个事务管理器，
    // 通过@Transaction注解的transactionManager属性指定事务管理器。

}