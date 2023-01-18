package s24_annotation.q1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("ss3")
public class Serv5 {

    //@Qualifier 用在类上
    @Autowired
    @Qualifier("ss1")
    private Map<String,IServ> ser;

    //@Autowired结合@Qulifier指定注入的bean
    @Autowired
    @Qualifier("serv3")
    private Map<String,IServ> ser1;

    // @Qualifier 用在方法参数上
    @Autowired
    public Serv5(@Qualifier("serv0") IServ s0) {
        this.s0 = s0;
    }

    private final IServ s0;


    public IServ getS4() {
        return s4;
    }

    // 用在setter方法上
    @Autowired
    @Qualifier("serv4")
    public void setS4(IServ s4) {
        this.s4 = s4;
    }

    private IServ s4;


    @Override
    public String toString() {
        return "Serv5{" +
                "ser=" + ser +
                ", ser1=" + ser1 +
                ", s0=" + s0 +
                ", s4=" + s4 +
                '}';
    }
}
