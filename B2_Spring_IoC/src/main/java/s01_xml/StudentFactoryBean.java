package s01_xml;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class StudentFactoryBean implements FactoryBean<Student> {
    int count = 1;
    @Nullable
    @Override
    public Student getObject() throws Exception { //@1
        Student userModel = new Student();
        userModel.setName("我是通过FactoryBean创建的第"+count+++ "对象");//@4
        return userModel;
    }
    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Student.class; //@2
    }
    @Override
    public boolean isSingleton() {
        return false; //@3  是否是单例
    }
}