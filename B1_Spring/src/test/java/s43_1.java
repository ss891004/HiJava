import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class s43_1 {


    //方式1：通过PlatformTransactionManager控制事务
    @Test
    public void test1() throws Exception {
        /**
         * 1. 定义事务管理器PlatformTransactionManager
         *  JpaTransactionManager：如果你用jpa来操作db，那么需要用这个管理器来帮你控制事务。
         *  DataSourceTransactionManager：如果你用是指定数据源的方式，比如操作数据库用的是：JdbcTemplate、mybatis、ibatis，那么需要用这个管理器来帮你控制事务。
         *  HibernateTransactionManager：如果你用hibernate来操作db，那么需要用这个管理器来帮你控制事务。
         *  JtaTransactionManager：如果你用的是java中的jta来操作db，这种通常是分布式事务，此时需要用这种管理器来控制事务。
         *
         * 2. 定义事务属性TransactionDefinition
         * 3. 开启事务
         * 4. 执行业务操作
         * 5. 提交 or 回滚
         */

        //定义一个数据源
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);

        //定义一个JdbcTemplate，用来方便执行数据库增删改查
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //1.定义事务管理器，给其指定一个数据源（可以把事务管理器想象为一个人，这个人来负责事务的控制操作）
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource);
        //2.定义事务属性：TransactionDefinition，TransactionDefinition可以用来配置事务的属性信息，比如事务隔离级别、事务超时时间、事务传播方式、是否是只读事务等等。
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //3.开启事务：调用platformTransactionManager.getTransaction开启事务操作，得到事务状态(TransactionStatus)对象
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        //4.执行业务操作，下面就执行2个插入操作
        try {
            System.out.println("before:" + jdbcTemplate.queryForList("SELECT * from t_user"));
            jdbcTemplate.update("insert into t_user (name) values (?)", "test1-1");
            jdbcTemplate.update("insert into t_user (name) values (?)", "test1-2");
            //5.提交事务：platformTransactionManager.commit
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            //6.回滚事务：platformTransactionManager.rollback
            platformTransactionManager.rollback(transactionStatus);
        }
        System.out.println("after:" + jdbcTemplate.queryForList("SELECT * from t_user"));
    }
}
