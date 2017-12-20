package com.epam.partA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomHandler implements InvocationHandler {

    Object obj;

    public CustomHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(obj,args);
        System.out.println("after");
        return result;
    }
}
