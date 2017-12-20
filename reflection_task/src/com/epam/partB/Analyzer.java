package com.epam.partB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Analyzer {

    public boolean equalObjects(Object object1, Object object2) throws IllegalAccessException {
        Class class1 = object1.getClass();
        Class class2 = object1.getClass();
        while (class1.getSuperclass()!=null||class2.getSuperclass()!=null) {
            List<Field> firstObjList = getFields(class1).stream().filter(x -> x.getAnnotation(Equal.class) != null).collect(Collectors.toList());
            List<Field> secondObjList = getFields(class2).stream().filter(x -> x.getAnnotation(Equal.class) != null).collect(Collectors.toList());
            if (firstObjList.size() != secondObjList.size()) {
                return false;
            }
            for (int i = 0; i < firstObjList.size(); i++) {
                Field field1 = firstObjList.get(i);
                Field field2 = secondObjList.get(i);
                if (!field1.get(object1).equals(field2.get(object2))) {
                    return false;
                }
            }
            class1 = class1.getSuperclass();
            class2 = class2.getSuperclass();
        }
        return true;
    }

    private List<Field> getFields(Class clazz) {
        List<Field> fieldList = new ArrayList<>();
        fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return fieldList;
    }

    public static void main(String[] args) throws IllegalAccessException {
        A a1 = new A();
        A a2 = new A();
        Analyzer analyzer = new Analyzer();
        System.out.println(analyzer.equalObjects(a1, a2));
        a1.setA(2);
        a1.setB(1);
        System.out.println(analyzer.equalObjects(a1, a2));
    }
}
