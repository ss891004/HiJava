package com.demo.generic;

interface Info<T>{        // 在接口上定义泛型
    public T getVar() ; // 定义方法，方法的返回值就是泛型类型
}