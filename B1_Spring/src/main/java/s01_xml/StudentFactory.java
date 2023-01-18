package s01_xml;

public class StudentFactory {

    public Student buildUser1() {
        System.out.println("----------------------1");
        Student userModel = new Student();
        userModel.setName("bean实例方法创建的对象!");
        return userModel;
    }
    public Student buildUser2(String name, int age) {
        System.out.println("----------------------2");
        Student userModel = new Student();
        userModel.setName(name);
        userModel.setAge(age);
        return userModel;
    }
}
