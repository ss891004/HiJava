package s24_annotation.q1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ss1")
public class Serv1 implements IServ{


}
