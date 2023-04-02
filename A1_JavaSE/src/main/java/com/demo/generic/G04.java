package com.demo.generic;




public class G04 {
    public static void main(String[] args) {
        G04 test_genericMethod = new G04();
        Integer integer = test_genericMethod.genericMethod(12);
        System.out.println(integer);
    }
    /**
     * 说明：
     * 1、public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2、只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3、<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     * 4、<T> 后面的这个T，代表这个方法的返回值类型，T的类型由调用方决定(参数类型或者接收类型决定的)
     * 4、与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(T a) {
        return a;
    }
}
