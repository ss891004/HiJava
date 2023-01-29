package s14;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Serv2 {
    public Serv2(Serv1 serv_1) {
        this.serv_1 = serv_1;
    }

    public Serv2() {
    }

    public Serv1 getServ_1() {
        return serv_1;
    }

    public void setServ_1(Serv1 serv_1) {
        this.serv_1 = serv_1;
    }

    private Serv1 serv_1;
}
