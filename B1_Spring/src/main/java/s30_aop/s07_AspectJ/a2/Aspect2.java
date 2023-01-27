package s30_aop.s07_AspectJ.a2;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Aspect2 {

    //@Pointcut("within(C1)")  // 类型必须是C1类型的才匹配
    @Pointcut("within(C1+)")
    // @Pointcut("within(C2)")
    public void a2() {
    }

    @Before(value = "a2()")
    public void before(JoinPoint joinPoint) {
        //输出连接点的信息
        System.out.println("前置通知，" + joinPoint);
    }

}
