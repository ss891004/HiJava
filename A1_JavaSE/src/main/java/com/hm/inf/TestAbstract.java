package com.hm.inf;

public class TestAbstract {

    /**
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 把Cat类声明成一个抽象类以后，就不能再对Cat类进行实例化了，
         * 因为抽象类是残缺不全的，缺胳膊少腿的，因此抽象类不能被实例化。
         */
        //Cat c = new Cat("Catname","blue");
        Dog d = new Dog("dogname","black");
        d.enjoy();//调用自己实现了的enjoy方法

        BlueCat c = new BlueCat("BlueCatname","blue");
        c.enjoy();//调用自己实现了的enjoy方法
    }
}