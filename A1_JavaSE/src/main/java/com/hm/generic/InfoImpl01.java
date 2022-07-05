package com.hm.generic;

/**
 * 传入泛型实参时：
 * 定义一个是类实现这个接口,虽然我们只创建了一个泛型接口Info<T>
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * 即：InfoImpl01<T>，public String getVar();中的的T都要替换成传入的String类型。
 */
class InfoImpl01 implements Info<String> {   // 定义泛型接口的子类
    private String var;
    public InfoImpl01(String var) {
        this.setVar(var);
    }
    public void setVar(String var) {
        this.var = var;
    }
    public String getVar() {
        return this.var;
    }
}
