package hm.s12_dbs;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@MapperScan(annotationClass = Mapper.class, sqlSessionFactoryRef = Module1SpringConfig.SQL_SESSION_FACTORY_BEAN_NAME)
@ComponentScan
@EnableTransactionManagement
public class Module1SpringConfig {
    public final static String DATASOURCE_BEAN_NAME = "dataSourcemodule1";
    public final static String TRANSACTION_MANAGER_BEAN_NAME = "transactionManagermodule1";
    public final static String SQL_SESSION_FACTORY_BEAN_NAME = "sqlSessionFactorymodule1";
    //定义数据源
    @Bean
    public DataSource dataSourcemodule1() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/module1?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root123");
        dataSource.setInitialSize(5);
        return dataSource;
    }
    //定义事务管理器
    @Bean
    public TransactionManager transactionManagermodule1(@Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    //定义SqlSessionFactoryBean，用来创建SqlSessionFactory
    @Bean
    public SqlSessionFactoryBean sqlSessionFactorymodule1(@Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
