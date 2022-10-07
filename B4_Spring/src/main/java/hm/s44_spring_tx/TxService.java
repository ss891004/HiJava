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

    // 1-1、REQUIRED
    //通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。

    public void notransaction_exception_required_required() {
        this.user1Service.required("张三");
        this.user2Service.required("李四");
        throw new RuntimeException();
    }

    public void notransaction_required_required_exception() {
        this.user1Service.required("张三");
        this.user2Service.required_exception("李四");
    }
    ///------------------------------------------------------

    // 1-2、外围方法开启事务（Propagation.REQUIRED）
    // 在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，
    // 所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        user1Service.required("张三");
        user2Service.required("李四");
        throw new RuntimeException();
    }

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

    //=============================================================

    // 2-1
    // 在外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
    public void notransaction_exception_requiresNew_requiresNew() {
        user1Service.requires_new("张三");
        user2Service.requires_new("李四");
        throw new RuntimeException();
    }

    public void notransaction_requiresNew_requiresNew_exception() {
        user1Service.requires_new("张三");
        user2Service.requires_new_exception("李四");
    }

    //2-2.
    // 在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew() {
        // 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中，
        // 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三”的方法回滚。
        user1Service.required("张三");
        user2Service.requires_new("李四");
        user2Service.requires_new("王五");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception() {
        // 外围方法开启事务，插入“张三”方法和外围方法一个事务，
        // 插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
        // 插入“王五”方法抛出异常，首先插入 “王五”方法的事务被回滚，
        // 异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入“张三”方法也被回滚。
        user1Service.required("张三");
        user2Service.requires_new("李四");
        user2Service.requires_new_exception("王五");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        //外围方法开启事务，插入“张三”方法和外围方法一个事务，
        // 插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
        // 插入“王五”方法抛出异常，首先插入“王五”方法的事务被回滚，异常被catch不会被外围方法感知，外围方法事务不回滚，故插入“张三”方法插入成功。
        user1Service.required("张三");
        user2Service.requires_new("李四");
        try {
            user2Service.requires_new_exception("王五");
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }

    // 3-1. PROPAGATION_NESTED
    // 在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，
    // 修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
    public void notransaction_exception_nested_nested() {
        //外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
        user1Service.nested("张三");
        user2Service.nested("李四");
        throw new RuntimeException();
    }

    public void notransaction_nested_nested_exception() {
        //外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
        user1Service.nested("张三");
        user2Service.nested_exception("李四");
    }

    //---------------------------------------------------------
    // 3-2.
    // 在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
    @Transactional
    public void transaction_exception_nested_nested() {
        // 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
        user1Service.nested("张三");
        user2Service.nested("李四");
        throw new RuntimeException();
    }

    @Transactional
    public void transaction_nested_nested_exception() {
        //外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
        user1Service.nested("张三");
        user2Service.nested_exception("李四");
    }

    @Transactional
    public void transaction_nested_nested_exception_try() {
        //外围方法开启事务，内部事务为外围事务的子事务，插入“李四”内部方法抛出异常，可以单独对子事务回滚。
        user1Service.nested("张三");
        try {
            user2Service.nested_exception("李四");
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

}