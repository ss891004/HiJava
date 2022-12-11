package hm.s53_mybatis;


import hm.s53_mybatis.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/*
1、@MapperScan注解：这个注解是关键，会扫描标记有@Mapper注解的Mapper接口类，然后给Mapper接口生成代理对象，将其注册到spring容器中，这个注解有几个属性需要注意下

    value或者basePackages：String类型的数组，用来指定扫描的包
    basePackageClasses：可以指定一个类，扫描范围为这个类所在的包及其所有子包
    sqlSessionFactoryRef：用来指定sqlSessionFactory的bean名称，当我们的系统中需要操作多个库的时候，每个库对应一个SqlSessionFactory，此时可以通过这个属性指定需要使用哪个SqlSessionFactory。

2、定义SqlSessionFactoryBean：通过名字大家可以看出，这个是用来生成SqlSessionFactory的，内部需要指定数据源和本地mapper xml的位置，我们将mapper xml文件放在resouce/mapper文件中，此处我们采用通配符的方式，加载classpath中mapper目录及子目录中的所有xml文件
*/
@EnableTransactionManagement
@ComponentScan
@MapperScan(basePackageClasses = {UserMapper.class}, annotationClass = Mapper.class)
@Configuration
public class MainConfig {

    //定义数据源
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
        return dataSource;
    }

    //定义事务管理器
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //获取所有mapper.xml文件
        //Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:/*.xml");

        return sqlSessionFactoryBean;
    }
}
