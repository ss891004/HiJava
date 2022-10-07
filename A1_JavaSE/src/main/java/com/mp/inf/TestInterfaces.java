package com.mp.inf;

/**
 * 这里定义了接口：Painter。 在Painter接口里面定义了paint()和eat()这两个抽象方法。
 */
interface Painter {
    public void eat();

    public void paint();
}

/**
 * 这里定义了两个接口：Singer 在Singer接口里面定义了sing()和sleep()这两个抽象方法。
 */
interface Singer {
    public void sing();

    public void sleep();
}

/**
 * 类Student实现了Singer这个接口
 */
class Student implements Singer {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 实现接口中定义的sing方法
     */
    @Override
    public void sing() {
        System.out.println("student is singing");
    }

    /**
     * 实现接口中定义的sleep方法
     */
    @Override
    public void sleep() {
        System.out.println("student is sleeping");
    }

    public void study() {
        System.out.println("Studying...");
    }

}

/**
 * Teacher这个类实现了两个接口：Singer和Painter。 这里Teacher这个类通过实现两个不相关的接口而实现了多重继承。
 */
class Teacher implements Singer, Painter {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    /**
     * 在Teacher类里面重写了这两个接口里面的抽象方法，
     * 通过重写抽象方法实现了这两个接口里面的抽象方法。
     */
    @Override
    public void eat() {
        System.out.println("teacher is eating");
    }

    public String getName() {
        return name;
    }

    @Override
    public void paint() {
        System.out.println("teacher is painting");
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sing() {
        System.out.println("teacher is singing");
    }

    @Override
    public void sleep() {
        System.out.println("teacher is sleeping");
    }

    public void teach() {
        System.out.println("teaching...");
    }
}

public class TestInterfaces {

    //一个类可以实现多个无关的接口
    //多个无关的类可以实现同一接口

    public static void main(String[] args) {
        /**
         * 这里定义了一个接口类型的变量s1
         */
        Singer s1 = new Student("le");
        s1.sing();
        s1.sleep();
        Singer s2 = new Teacher("steven");
        s2.sing();
        s2.sleep();
        Painter p1 = (Painter)s2;
        p1.paint();
        p1.eat();
    }
}