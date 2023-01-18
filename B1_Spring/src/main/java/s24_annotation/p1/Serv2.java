package s24_annotation.p1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Serv2 implements IServ{
    //@Primary：设置为主要候选者
}
