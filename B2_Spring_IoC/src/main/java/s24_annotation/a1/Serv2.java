package s24_annotation.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv2 {

    private Serv1 serv1;

    //通过@Autowired指定注入的构造器
    @Autowired
    public Serv2(Serv1 serv1) {
        this.serv1 = serv1;
    }

    public Serv2() {
    }

    @Override
    public String toString() {
        return "Serv2{" +
                "serv1=" + serv1 +
                '}';
    }
}
