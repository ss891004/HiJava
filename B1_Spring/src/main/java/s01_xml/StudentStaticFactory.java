package s01_xml;

public class StudentStaticFactory {

    public static Student buildStudent1() {
        System.out.println(StudentStaticFactory.class + ".buildStudent1()");
        Student userModel = new Student();
        userModel.setName("我是无参静态构造方法创建的!");
        return userModel;
    }

    public static Student buildStudent2(String name, int age) {
        System.out.println(StudentStaticFactory.class + ".buildStudent2()");
        Student userModel = new Student();
        userModel.setName(name);
        userModel.setAge(age);
        return userModel;
    }


}
