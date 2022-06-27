package s24_annotation.a6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv3 implements IServ{

    @Autowired
    private IServ aaa;

}
