package s17_6.a1;


import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

// 优先创建 serv1和serv2对象
@Component
@DependsOn({"serv1","serv2"})
public class Serv3 {
    public Serv3() {
        System.out.println("create Serv3");
    }
}
