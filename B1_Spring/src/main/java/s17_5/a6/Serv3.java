package s17_5.a6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Serv3 {

    @Autowired
    private IServ serv1;   // 通过  serv1 注入

    @Autowired
    private IServ aaa; // 有 serv1，serv2 2个bean， 无法通过aaa 的名字 注入

}
