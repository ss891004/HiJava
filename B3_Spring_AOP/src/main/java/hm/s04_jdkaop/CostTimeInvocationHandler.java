package hm.s04_jdkaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CostTimeInvocationHandler implements InvocationHandler {

    private Object target;

    public CostTimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startime = System.nanoTime();
        Object result = method.invoke(this.target, args);
        System.out.println(method + "，耗时(纳秒):" + (System.nanoTime() - startime));
        return result;
    }
}
