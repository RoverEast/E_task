package com.epam.partA;

import java.lang.reflect.Proxy;
public class FactoryClass {

    private B b = new B();
    private CustomHandler invocationHandler = new CustomHandler(b);


    public Object getInstance(Class a) throws IllegalAccessException, InstantiationException {
        if (a.getAnnotation(com.epam.partA.Proxy.class)!=null){
            System.out.println("Class has @Proxy annotation");
            return Proxy.newProxyInstance(FactoryClass.class.getClassLoader(), new Class[]{A.class}, invocationHandler);
        }
        System.out.println("Class has not @Proxy annotation");
        return a.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        A b = new B();
        FactoryClass factoryClass = new FactoryClass();
        b = (A) factoryClass.getInstance(b.getClass());
        b.doSmth();
    }
}
