package com.hm.inf;

/**
 * 子类BlueCat继承抽象类Cat，并且实现了从父类Cat继承下来的抽象方法enjoy
 * @author gacl
 *
 */
class BlueCat extends Cat {

    public BlueCat(String n, String c) {
        super(n, c);
    }

    /**
     * 实现了抽象方法enjoy
     */
    @Override
    public void enjoy() {
        System.out.println("蓝猫叫...");
    }

}