package s17_5.a4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv2 {

    @Autowired
    public void aa(Serv1 serv1 , @Autowired(required = false)  String bbbb) {
        this.serv1 = serv1;
        System.out.println(bbbb);
    }

    private Serv1 serv1;

    @Override
    public String toString() {
        return "Serv2{" +
                "serv1=" + serv1 +
                '}';
    }
}
