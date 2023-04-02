package ioc;

public class S26 {

    // 国际化简单理解，就是对于不同的语言，做出不同的响应。

    // java.util.Locale来表示地区语言这个对象


    // 步骤一：创建国际化文件
    //
    //步骤二：向容器中注册一个MessageSource类型的bean，bean名称必须为：messageSource
    //
    //步骤三：调用AbstractApplicationContext中的getMessage来获取国际化信息，其内部将交给第二步中注册的messageSource名称的bean进行处理


    // 国际化文件命名格式：名称_语言_地区.properties
}
