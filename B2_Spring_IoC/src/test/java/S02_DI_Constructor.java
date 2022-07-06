import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import s01_xml.Student;

public class S02_DI_Constructor {

    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        System.out.println("spring容器准备启动.....");
        //1.bean配置文件位置
        String beanXml = "classpath:s02-DI-Constructor.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        this.context = new ClassPathXmlApplicationContext(beanXml);
        System.out.println("spring容器启动完毕！");
    }

    @Test
    public void Test1() {

        Student student1 = context.getBean("DIByConstructorParamIndex", Student.class);
        //根据构造器参数索引注入

        /*参数位置的注入对参数顺序有很强的依赖性，若构造函数参数位置被人调整过，会导致注入出错。
        不过通常情况下，不建议去在代码中修改构造函数，如果需要新增参数的，可以新增一个构造函数来实现，这算是一种扩展，不会影响目前已有的功能。*/

        System.out.println(student1);
    }


    @Test
    public void Test2() {
        Student student2 = context.getBean("DIByConstructorParamType", Student.class);
        //根据构造器参数类型注入
        System.out.println(student2);
    }

    @Test
    public void Test3() {
        Student student3 = context.getBean("DIByConstructorParamName", Student.class);

        //根据构造器参数名称注入
        System.out.println(student3);
    }

}
