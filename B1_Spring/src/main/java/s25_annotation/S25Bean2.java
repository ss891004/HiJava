package s25_annotation;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ImportResource({"classpath:beans1.xml","classpath:beans2.xml"})
public class S25Bean2 {
}
