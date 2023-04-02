package s17_6.a2;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ImportResource({"classpath:s17_6_beans1.xml","classpath:s17_6_beans2.xml"})
public class Bean17_6_2 {
}
