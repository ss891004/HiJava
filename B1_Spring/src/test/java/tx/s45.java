import s44.MainConfig6;
import s43_tx.TxService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Spring事务中7种传播行为
 */
public class s45 {

    //事务传播行为
/*
Propagation是个枚举，有7种值，如下：
事务传播行为类型	说明
REQUIRED	如果当前事务管理器中没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择，是默认的传播行为。
SUPPORTS	支持当前事务，如果当前事务管理器中没有事务，就以非事务方式执行。
MANDATORY	使用当前的事务，如果当前事务管理器中没有事务，就抛出异常。
REQUIRES_NEW	新建事务，如果当前事务管理器中存在事务，把当前事务挂起，然后会新建一个事务。
NOT_SUPPORTED	以非事务方式执行操作，如果当前事务管理器中存在事务，就把当前事务挂起。
NEVER	以非事务方式执行，如果当前事务管理器中存在事务，则抛出异常。
NESTED	如果当前事务管理器中存在事务，则在嵌套事务内执行；如果当前事务管理器中没有事务，则执行与PROPAGATION_REQUIRED类似的操作。*/


    private TxService txService;
    private JdbcTemplate jdbcTemplate;

    //每个@Test用例执行之前先启动一下spring容器，并清理一下user1、user2中的数据
    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig6.class);
        txService = context.getBean(TxService.class);
        jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.update("truncate table user1");
        jdbcTemplate.update("truncate table user2");
    }

    @After
    public void after() {
        System.out.println("user1表数据：" + jdbcTemplate.queryForList("SELECT * from user1"));
        System.out.println("user2表数据：" + jdbcTemplate.queryForList("SELECT * from user2"));
    }

    //TxService的方法中去调用另外2个service，所以TxService中的方法统称外围方法，另外2个service中的方法称内部方法。

    @Test
    public void notransaction_exception_required_required() {
        txService.notransaction_exception_required_required();
    }

    @Test
    public void notransaction_required_required_exception() {
        txService.notransaction_required_required_exception();
    }

    //-----------------------------------------------------
    @Test
    public void transaction_exception_required_required() {
        txService.transaction_exception_required_required();
    }

    @Test
    public void transaction_required_required_exception() {
        txService.transaction_required_required_exception();
    }

    @Test
    public void transaction_required_required_exception_try() {
        txService.transaction_required_required_exception_try();
    }

    //==============================================


    @Test
    public void notransaction_exception_requiresNew_requiresNew() {
        txService.notransaction_exception_requiresNew_requiresNew();
    }

    @Test
    public void notransaction_requiresNew_requiresNew_exception() {
        txService.notransaction_requiresNew_requiresNew_exception();
    }

    //-----------------------------------------------
    @Test
    public void transaction_exception_required_requiresNew_requiresNew() {
        txService.transaction_exception_required_requiresNew_requiresNew();
    }

    @Test
    public void transaction_required_requiresNew_requiresNew_exception() {
        txService.transaction_required_requiresNew_requiresNew_exception();
    }

    @Test
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        txService.transaction_required_requiresNew_requiresNew_exception_try();
    }

    //===================================================
    @Test
    public void notransaction_exception_nested_nested() {
        txService.notransaction_exception_nested_nested();
    }

    @Test
    public void notransaction_nested_nested_exception() {
        txService.notransaction_nested_nested_exception();
    }

    //-----------------------------------------
    @Test
    public void transaction_exception_nested_nested() {
        txService.transaction_exception_nested_nested();
    }

    @Test
    public void transaction_nested_nested_exception() {
        txService.transaction_nested_nested_exception();
    }

    @Test
    public void transaction_nested_nested_exception_try() {
        txService.transaction_nested_nested_exception_try();
    }
    //========================================================

}
