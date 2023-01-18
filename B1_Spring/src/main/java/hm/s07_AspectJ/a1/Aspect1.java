package hm.s07_AspectJ.a1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
//@1：这个类需要使用@Aspect进行标注
@Aspect
public class Aspect1 {
    //@2：定义了一个切入点，可以匹配Service1中所有方法
    @Pointcut("execution(* hm.s07_AspectJ.a1.Service1.*(..))")
    public void pointcut1() {
    }
    //@3：使用@Before标注在方法上面，定义了一个前置通知，通过value引用了上面已经定义的切入点，
    // 表示这个通知会对Service1中的所有方法生效，在通知中可以通过这个类名.方法名()引用@Pointcut定义的切入点，表示这个通知对这些切入点有效，
    // 若@Before和@Pointcut在一个类的时候，直接通过方法名()引用当前类中定义的切入点
    @Before(value = "pointcut1()")
    public void before(JoinPoint joinPoint) {
        //输出连接点的信息
        System.out.println("前置通知，" + joinPoint);
    }
    //@4：这个使用@AfterThrowing定义了一个异常通知，也是对通过value引用了上面已经定义的切入点，表示这个通知会对Service1中的所有方法生效，
    // 若Service1中的方法抛出了Exception类型的异常，都会回调afterThrowing方法。
    @AfterThrowing(value = "pointcut1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        //发生异常之后输出异常信息
        System.out.println(joinPoint + ",发生异常：" + e.getMessage());
    }
}
