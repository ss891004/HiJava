package com.hm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = Db1Config.PACKAGE, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class Db1Config {


    // PACKAGE是master接口地址
    static final String PACKAGE = "com.hm.mapper.db1";
    //MAPPER_LOCATION是master的xml
    static final String MAPPER_LOCATION = "classpath:com.hm.mapper/db1/*.xml";

    //下面读取的是application.yml文件的配置
    @Value("${first.datasource.url}")
    private String url;

    @Value("${first.datasource.username}")
    private String user;

    @Value("${first.datasource.password}")
    private String password;

    @Value("${first.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "ds1")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
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
