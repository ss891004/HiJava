package s24_annotation.p1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Serv3 {

    // 用在类上
    @Autowired
    private IServ s1;

    // 用在方法上，结合@Bean使用
    @Autowired
    private IServ1 ss0;

    @Override
    public String toString() {
        return "Serv3{" +
                "s1=" + s1 +
                ", ss0=" + ss0 +
                '}';
    }
}
