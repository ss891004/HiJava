package s17_5.a7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Serv4 implements IServ {

    @Autowired
    private List<IServ> aaa;

    @Autowired
    private Map<String, IServ> bbb;

}
