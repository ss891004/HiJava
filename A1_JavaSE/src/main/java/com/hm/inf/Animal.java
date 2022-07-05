package com.hm.inf;

/**
 * 父类Animal
 * 在class的前面加上abstract，即声明成这样：abstract class Animal
 * 这样Animal类就成了一个抽象类了
 */
abstract class Animal {

    public String name;

    public Animal(String name) {
        this.name = name;
    }

    /**
     * 抽象方法
     * 这里只有方法的定义，没有方法的实现。
     */
    public abstract void enjoy();

}