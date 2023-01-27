package s30_aop.s04_cglibaop;

public class UserModel {
    private String name;

    public UserModel() {
    }

    public UserModel(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("你好：" + name);
    }
}
