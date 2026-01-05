package com.dansmultipro;

import com.dansmultipro.annotation.onCreate;

import java.lang.reflect.Constructor;

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

    public static void main(String[] args) throws Exception {
        // Reflection
        Class<?> clazz = Class.forName("com.dansmultipro.Main");

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("\n" + constructor.getName());
        }
    }

    @onCreate(priority = 1)
    public void test() {

    }
}