package s14;

public class Serv3 {

    public void make() {
        // 当调用 get方法时，spirng 会安装 lookup-method 的配置，进行拦截，即可以每次返回一实例化的对象
        serv_1 = this.getServ_1();

        System.out.println("this:" + this + ",serv_1:" + serv_1);

    }

    public Serv1 getServ_1() {
        System.out.println("getServ_1---什么都没干～～～～～");
        return null;
    }

    private Serv1 serv_1;

}
