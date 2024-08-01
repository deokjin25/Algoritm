package com.example;

class SuperClass {
    String x = "super";

    public void method() {
        System.out.println("super class method");
    }
}

class SubClass extends SuperClass {
    String x = 'sub';

    @Override
    public void method() {
        System.out.println("sub class method");
    }
}
