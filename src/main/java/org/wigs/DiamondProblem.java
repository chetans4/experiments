package org.wigs;

interface InterfaceA {
    default void display() {
        System.out.println("InterfaceA");
    }
}

interface InterfaceB {
     static void display() {
        System.out.println("InterfaceB");
    }
}

class MyClass implements InterfaceA, InterfaceB {
    // Resolve the conflict by overriding the method

    @Override
    public void display() {
        InterfaceA.super.display();
    }

    public static void main(String[] args) {
        new MyClass().display();
    }
}
