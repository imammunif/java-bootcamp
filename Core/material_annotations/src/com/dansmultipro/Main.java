package com.dansmultipro;

import com.dansmultipro.annotation.onCreate;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    private String name;
    private int age;

    // 1st Constructor (Empty constructor for testing other overload constructor)
    public Main() {
    }

    // 2nd Constructor
    public Main(String param1) {
    }

    // 3rd Constructor
    public Main(String param1, String param2) {

    }

    /*
    * 1. Class
    * 2. Method
    * 3. Object
    */

    public static void main(String[] args) throws Exception {
        // Reflection
        Class<?> clazz = Class.forName("com.dansmultipro.Main");
        // Get the 3rd constructor with 2 parameters
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        Object obj = constructor.newInstance("param1", "param2");
        Method m  = clazz.getMethod("test");
        m.invoke(obj);

        //    for (Constructor<?> constructor : constructors) {
        //        System.out.println("\n" + constructor.getName());
        //    }
        //
        //    for (Method m : clazz.getDeclaredMethods()) {
        //        System.out.println("\n" + m.getName());
        //    }
        //
        //    for (Field f : clazz.getDeclaredFields()) {
        //        System.out.println("\n"+ f.getName());
        //    }
    }

    @onCreate(priority = 1)
    public void test() {
        System.out.println("test...1....2....3");
    }
}