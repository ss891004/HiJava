package s30_aop;

import s30_aop.s04_cglibaop.UserModel;
import org.junit.Test;
import org.springframework.cglib.core.DefaultNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;


public class S04_5 {


    /**
     * 接口NamingPolicy表示生成代理类的名字的策略，通过Enhancer.setNamingPolicy方法设置命名策略。
     * 默认的实现类：DefaultNamingPolicy， 具体cglib动态生成类的命名控制。
     * DefaultNamingPolicy中有个getTag方法。
     * DefaultNamingPolicy生成的代理类的类名命名规则：
     * 被代理class name + "$$" + 使用cglib处理的class name + "ByCGLIB" + "$$" + key的hashcode
     */
    @Test
    public void fun() {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserModel.class);

        enhancer.setCallback( NoOp.INSTANCE );

        enhancer.setNamingPolicy(
                new DefaultNamingPolicy(){
                    @Override
                    protected String getTag() {
                        return "sssss";
                    }
                }
        );

        System.out.println(enhancer.create().getClass()); ;

    }
}
