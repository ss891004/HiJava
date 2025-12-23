package s30_aop;

import s30_aop.s05_aop.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.Objects;

public class S05 {

    //AOP: 在程序中具有公共特性的某些类/某些方法上进行拦截, 在方法执行的前面/后面/执行结果返回后增加执行一些方法。

    /**
     * 目标对象(target)
     *  目标对象指将要被增强的对象，即包含主业务逻辑的类对象。
     * 连接点(JoinPoint)
     *  连接点，程序运行的某一个点，比如执行某个方法，在Spring AOP中Join Point总是表示一个方法的执行
     * 代理对象(Proxy)
     *  AOP中会通过代理的方式，对目标对象生成一个代理对象，代理对象中会加入需要增强功能，通过代理对象来间接的方式目标对象，起到增强目标对象的效果。
     * 通知(Advice)
     *  需要在目标对象中增强的功能，如上面说的：业务方法前验证用户的功能、方法执行之后打印方法的执行日志。
     *  通知中有2个重要的信息：方法的什么地方，执行什么操作，这2个信息通过通知来指定。
     *  方法的什么地方？之前、之后、包裹目标方法、方法抛出异常后等。
     * 切入点(Pointcut)
     *  用来指定需要将通知使用到哪些地方，比如需要用在哪些类的哪些方法上，切入点就是做这个配置的。
     * 切面（Aspect）
     *  通知（Advice）和切入点（Pointcut）的组合。切面来定义在哪些地方（Pointcut）执行什么操作（Advice）。
     * 顾问（Advisor)
     *  Advisor 其实它就是 Pointcut 与 Advice 的组合，Advice 是要增强的逻辑，而增强的逻辑要在什么地方执行是通过Pointcut来指定的，
     *  所以 Advice 必需与 Pointcut 组合在一起，这就诞生了 Advisor 这个类，spring Aop中提供了一个Advisor接口将Pointcut 与 Advice 的组合起来。
     */


    //需求：在work方法执行之前，打印一句：你好：userName
    @Test
    public void test1() {
        //定义目标对象
        UserService target = new UserService();

        //创建pointcut，用来拦截UserService中的work方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                //判断是否是UserService类型的
                return clazz -> UserService.class.isAssignableFrom(clazz);
            }
            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        //判断方法名称是否是work
                        return "work".equals(method.getName());
                    }
                    @Override
                    public boolean isRuntime() {
                        return false;
                    }
                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };

        //创建通知，此处需要在方法之前执行操作，所以需要用到MethodBeforeAdvice类型的通知
        MethodBeforeAdvice advice = (method, args, tgt) -> System.out.println("你好:" + args[0]);

        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        //通过spring提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用addAdvisor方法，为目标添加增强的功能，即添加Advisor，可以为目标添加很多个Advisor
        proxyFactory.addAdvisor(advisor);

        //通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();
        //调用代理的work方法
        userServiceProxy.work("ww");
    }



    // 需求：统计一下work方法的耗时，将耗时输出
    @Test
    public void test2() {
        //定义目标对象
        UserService target = new UserService();

        //创建pointcut，用来拦截UserService中的work方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                //判断是否是UserService类型的
                return clazz -> UserService.class.isAssignableFrom(clazz);
            }
            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        //判断方法名称是否是work
                        return "work".equals(method.getName());
                    }
                    @Override
                    public boolean isRuntime() {
                        return false;
                    }
                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };

        //创建通知，需要拦截方法的执行，所以需要用到MethodInterceptor类型的通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("准备调用:" + invocation.getMethod());
                long starTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(invocation.getMethod() + "，调用结束！");
                System.out.println("耗时(纳秒):" + (endTime - starTime));
                return result;
            }
        };

        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        //通过spring提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用addAdvisor方法，为目标添加增强的功能，即添加Advisor，可以为目标添加很多个Advisor
        proxyFactory.addAdvisor(advisor);
        //通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();
        //调用代理的work方法
        userServiceProxy.work("路人");
    }

    // 需求：userName中包含“粉丝”关键字，输出一句：感谢您一路的支持
    @Test
    public void test3() {
        //定义目标对象
        UserService target = new UserService();

        //创建pointcut，用来拦截UserService中的work方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                //判断是否是UserService类型的
                return clazz -> UserService.class.isAssignableFrom(clazz);
            }
            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        //判断方法名称是否是work
                        return "work".equals(method.getName());
                    }
                    @Override
                    public boolean isRuntime() {
                        return true; // @1：注意这个地方要返回true
                    }
                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        // @2：isRuntime为true的时候，会执行这个方法
                        if (Objects.nonNull(args) && args.length == 1) {
                            String userName = (String) args[0];
                            return userName.contains("粉丝");
                        }
                        return false;
                    }
                };
            }
        };
        //创建通知，此处需要在方法之前执行操作，所以需要用到MethodBeforeAdvice类型的通知
        MethodBeforeAdvice advice = (method, args, target1) -> System.out.println("感谢您一路的支持!");
        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        //通过spring提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用addAdvisor方法，为目标添加增强的功能，即添加Advisor，可以为目标添加很多个Advisor
        proxyFactory.addAdvisor(advisor);
        //通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();
        //调用代理的work方法
        userServiceProxy.work("粉丝：A");
    }


}
