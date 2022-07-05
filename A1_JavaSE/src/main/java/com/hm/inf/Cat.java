package com.hm.inf;

/**
 * 这里的子类Cat从抽象类Animal继承下来，自然也继承了Animal类里面声明的抽象方法enjoy()，
 * 但子类Cat觉得自己去实现这个enjoy()方法也不合适，因此它把它自己也声明成一个抽象的类，
 * 那么，谁去实现这个抽象的enjoy方法，谁继承了子类，那谁就去实现这个抽象方法enjoy()。
 * @author gacl
 *
 */
abstract class Cat extends Animal {

    /**
     * Cat添加自己独有的属性
     */
    public String eyeColor;

    public Cat(String n, String c) {
        super(n);//调用父类Animal的构造方法
        this.eyeColor = c;
    }
}