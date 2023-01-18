package hm.s07_AspectJ.a4;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Aspect4 {

    @Pointcut("target(hm.s07_AspectJ.a3.C3)")
    public void p4() {
    }

    @Before(value = "p4()")
    public void before(JoinPoint joinPoint) {
        //输出连接点的信息
        System.out.println("前置通知，" + joinPoint);
    }

}
