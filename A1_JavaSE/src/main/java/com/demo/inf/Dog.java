package com.demo.inf;

/**
 * 子类Dog继承抽象类Animal，并且实现了抽象方法enjoy
 * @author gacl
 *
 */
class Dog extends Animal {
    /**
     * Dog类添加自己特有的属性
     */
    public String furColor;

    public Dog(String n, String c) {
        super(n);//调用父类Animal的构造方法
        this.furColor = c;
    }

    @Override
    public void enjoy() {
        System.out.println("狗叫....");
    }

}
