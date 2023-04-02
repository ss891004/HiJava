package s17_5.a2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv2 {
    @Autowired
    public void setServ1(Serv1 serv1) {
        this.serv1 = serv1;
    }

    private Serv1 serv1;

    @Override
    public String toString() {
        return "Serv2{" +
                "serv1=" + serv1 +
                '}';
    }
}
