import s30_aop.s03_cglib.*;
import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class s30_3_cglib_proxy {

    // cglib代理
    @Test
    public void test1() {
        //使用Enhancer来给某个类创建代理类，步骤
        //1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();

        //2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(Service1.class);

        /*3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
        此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor，也是一个接口，实现了Callback接口，
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("调用方法:" + method);
            //可以调用MethodProxy的invokeSuper调用被代理类的方法
            Object result = methodProxy.invokeSuper(o, objects);
            return result;
        });


        //4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        Service1 proxy = (Service1) enhancer.create();


        //5.调用代理对象的方法
        proxy.m1();
        proxy.m2();
    }


    // 看出m1和m2方法都被拦截器处理了，而m2方法是在Service1的m1方法中调用的，也被拦截处理了。
    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("调用方法:" + method);
            Object result = methodProxy.invokeSuper(o, objects);
            return result;
        });
        Service2 proxy = (Service2) enhancer.create();
        proxy.m1(); //拦截Service2 中所有的方法，只要方法被调用，都会被拦截
    }

    // 拦截所有方法并返回固定值（FixedValue）
    @Test
    public void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback((FixedValue) () -> "固定值");
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());//@1
        System.out.println(proxy.m2()); //@2
        System.out.println(proxy);//@3
    }

    // 直接放行，不做任何操作（NoOp.INSTANCE）
    @Test
    public void test4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
    }

    //不同的方法使用不同的拦截器（CallbackFilter）
    @Test
    public void test5() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service5.class);

        //创建2个Callback
        Callback[] callbacks = {
                //以insert开头的方法需要统计方法耗时
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        long starTime = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long endTime = System.nanoTime();
                        System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
                        return result;
                    }
                },
                //以get开头的的方法直接返回固定字符串
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "固定值";
                    }
                }
        };


        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /**
         * 设置过滤器CallbackFilter
         * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方法
         * 返回的是callbacks数组的下标
         */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                //获取当前调用的方法的名称
                String methodName = method.getName();
                /**
                 * 方法名称以insert开头，
                 * 返回callbacks中的第1个Callback对象来处理当前方法，
                 * 否则使用第二个Callback处理被调用的方法
                 */
                return methodName.startsWith("insert") ? 0 : 1;
            }
        });


        Service5 proxy = (Service5) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }


    //对test5的优化（CallbackHelper）
    @Test
    public void test5_new() {
        Enhancer enhancer = new Enhancer();
        //创建2个Callback
        Callback costTimeCallback = (MethodInterceptor) (Object o, Method method, Object[] objects, MethodProxy methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };

        //下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixedValueCallback = (FixedValue) () -> "test5-fixed";

        CallbackHelper callbackHelper = new CallbackHelper(Service5.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixedValueCallback;
            }
        };

        enhancer.setSuperclass(Service5.class);
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        //设置CallbackFilter,用来判断某个方法具体走哪个Callback
        enhancer.setCallbackFilter(callbackHelper);

        Service5 proxy = (Service5) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }


    //实现通用的统计任意类方法耗时代理类
    @Test
    public void test7() {
        //创建Service1代理
        Service1 service1 = CostTimeProxy.createProxy(new Service1());
        service1.m1();
        //创建Service3代理
        Service3 service3 = CostTimeProxy.createProxy(new Service3());
        System.out.println(service3.m1());
    }

}
