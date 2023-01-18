package aop;

import hm.s04_cglibaop.UserModel;
import org.junit.Test;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;


public class S04_6 {


    @Test
    public void test1() {

        //Objenesis，通过这个接口可以解决上面这种问题，它专门用来创建对象，
        // 即使你没有空的构造函数，都木有问题，它不使用构造方法创建Java对象，所以即使你有空的构造方法，也是不会执行的。
        Objenesis obj = new ObjenesisStd();
        UserModel u = obj.newInstance(UserModel.class);
        System.out.println(u);
    }

    @Test
    public void test2() {
        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator<UserModel> userObjectInstantiate = objenesis.getInstantiatorOf(UserModel.class);
        UserModel user1 = userObjectInstantiate.newInstance();
        System.out.println(user1);
        UserModel user2 = userObjectInstantiate.newInstance();
        System.out.println(user2);
        System.out.println(user1 == user2);
    }
}
