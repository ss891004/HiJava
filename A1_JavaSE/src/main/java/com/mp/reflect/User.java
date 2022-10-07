package com.mp.reflect;


public class User {
    private int no;
    private String name;

    public User(){
        System.out.printf("this is User");
    }

    public User(int no, String name){
        System.out.printf("this is User2");
        this.no=no;
        this.name=name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
