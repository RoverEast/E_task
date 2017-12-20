package com.epam.partA;

@Proxy
public class B implements A {

    @Override
    public void doSmth() {
        System.out.println("do something");
    }
}
