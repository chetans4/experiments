package org.wigs;

public class InterfaceVsAbstractClass {

    public static void main(String[] args) {
        CircleInterface redCircleWithoutState = new CircleInterfaceImpl();
        redCircleWithoutState.setColor("RED");
        System.out.println("is red : "+redCircleWithoutState.isValid());
    }
}
