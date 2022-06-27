package s25_annotation;


import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

//和@Compontent一起使用在类上
@Component
@DependsOn({"serv1","serv2"})
public class Serv3 {
}
