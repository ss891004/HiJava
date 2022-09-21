package hm.s44_spring_tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TxService {
    @Autowired
    private User1Service user1Service;
    @Autowired
    private User2Service user2Service;

    // 在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰
    // 1、REQUIRED
    public void notransaction_exception_required_required() {
        this.user1Service.required("张三");
        this.user2Service.required("李四");
        throw new RuntimeException();
    }

    public void notransaction_required_required_exception() {
        this.user1Service.required("张三");
        this.user2Service.required_exception("李四");
    }

    //////////////////////////////////////////////////////////////////////////////


    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        user1Service.required("张三");
        user2Service.required("李四");
        throw new RuntimeException();
    }

    //////////////////////////////////////////////////////////////////////////////

    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        user1Service.required("张三");
        user2Service.required_exception("李四");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try() {
        user1Service.required("张三");
        try {
            user2Service.required_exception("李四");
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
    //**********************************************************


    /**
     * public void notransaction_exception_requiresNew_requiresNew(){
     *     user1Service.requires_new("张三");
     *     user2Service.requires_new("李四");
     *     throw new RuntimeException();
     * }
     */
}