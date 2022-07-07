package s08_lookup;

public class S08_3 {

    public S08_1 getS08_1() {
        return null;
    }

    public void make() {
        // 当调用 get方法时，spirng 会安装 lookup-method 的配置，进行拦截，即可以每次返回一实例化的对象

        s08_1 = this.getS08_1();

        System.out.println("this:" + this + ",s08_1:" + s08_1);

    }

    private S08_1 s08_1;

}
