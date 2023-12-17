package edu.hw10.Task1Test;

public class MyClass {
    private String name;
    private int age;

    // Пример фабричного метода
    public static MyClass create(String name, int age) {
        MyClass myClass = new MyClass();
        myClass.name = name;
        myClass.age = age;
        return myClass;
    }

    @Override
    public String toString() {
        return "MyClass{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}

