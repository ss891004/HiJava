package s30_aop;

import s30_aop.s04_cglibaop.UserModel;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

import java.util.UUID;

//LazyLoader
public class S04_3 {


    @Test
    public void fun() {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserModel.class);

        enhancer.setCallback(
                (LazyLoader) () -> {
                    System.out.println("LazyLoader.....");
                    return new UserModel("LazyLoader" + UUID.randomUUID().toString());
                }

        );

        UserModel proxy = (UserModel) enhancer.create();

        // LazyLoader是cglib用于实现懒加载的callback。当被增强bean的方法初次被调用时，会触发回调，
        // 之后每次再进行方法调用都是对LazyLoader第一次返回的bean调用
        proxy.say();
        proxy.say();

    }
}
