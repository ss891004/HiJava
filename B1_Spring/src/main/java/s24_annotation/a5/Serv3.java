package s24_annotation.a5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv3 {

    @Autowired
    private Serv1 serv1;

    @Autowired
    private Serv2 serv2;

}
