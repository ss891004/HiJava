package s17_5.a8;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Serv3 {
    @Resource
    private IServ serv1;
    @Resource
    private IServ serv2;
    @Resource(name = "serv1")
    private IServ serv3;

    @Override
    public String toString() {
        return "Serv3{" +
                "serv1=" + serv1 +
                ", serv2=" + serv2 +
                ", serv3=" + serv3 +
                '}';
    }
}
