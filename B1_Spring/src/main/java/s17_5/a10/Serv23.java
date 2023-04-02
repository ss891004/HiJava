package s17_5.a10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv23 {

    // 用在类上
    @Autowired
    private IServ2 s1; // 有2个bean serv21， serv22， 其中有一个主bean

    // 用在方法上，结合@Bean使用
    @Autowired
    private IServ1 ss0;  //使用主bean

    @Autowired
    private IServ1 b1;

    @Override
    public String toString() {
        return "Serv3{" +
                "s1=" + s1 +
                ", ss0=" + ss0 +
                ", serv11=" + b1 +
                '}';
    }
}
