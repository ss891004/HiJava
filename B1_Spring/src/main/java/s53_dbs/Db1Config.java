package s53_dbs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;



//系统中需要用到多个数据源，每个数据源对应一个SqlSessionFactory，@MapperScan注解中可以通过sqlSessionTemplateRef来指定SqlSessionFactory的bean名称。
@Configuration
@ComponentScan
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = Db1Config.PACKAGE, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class Db1Config {


    // PACKAGE是master接口地址
    static final String PACKAGE = "s53_dbs.mapper.db1";
    //MAPPER_LOCATION是master的xml
    static final String MAPPER_LOCATION = "classpath:com.hm.mapper/db1/*.xml";

    //下面读取的是application.yml文件的配置
   // @Value("${first.datasource.url}")
    private String url="jdbc:mysql://127.0.0.1:3306/mp1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";

   // @Value("${first.datasource.username}")
    private String user="root";

  //  @Value("${first.datasource.password}")
    private String password="123456";

   // @Value("${first.datasource.driver-class-name}")
    private String driverClass="com.mysql.cj.jdbc.Driver";

    @Bean(name = "ds1")
    public DataSource masterDataSource() {
        //DruidDataSource dataSource = new DruidDataSource();
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "db1Txn")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("ds1") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(Db1Config.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
