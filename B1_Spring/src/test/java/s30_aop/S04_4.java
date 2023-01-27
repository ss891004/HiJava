package s30_aop;

import s30_aop.s04_cglibaop.UserModel;
import org.junit.Test;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Enhancer;

import java.util.UUID;

// Dispatcher的话每次对增强bean进行方法调用都会触发回调。
public class S04_4 {


    @Test
    public void fun() {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserModel.class);

        enhancer.setCallback(
                (Dispatcher) () -> {
                    System.out.println("Dispatcher.....");
                    return new UserModel("LazyLoader"+ UUID.randomUUID().toString());
                }

        );

        UserModel proxy = (UserModel) enhancer.create();

        // LazyLoader是cglib用于实现懒加载的callback。当被增强bean的方法初次被调用时，会触发回调，
        // 之后每次再进行方法调用都是对LazyLoader第一次返回的bean调用
        proxy.say();
        proxy.say();

    }
}
