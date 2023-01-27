import s43_tx.MainConfig6;
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
