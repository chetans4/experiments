package org.wigs;

public class NormalStarPattern {

    public static void main(String[] args) {
        //
        for (int i=4; i>=0 ; i--){ // to determine number of rows
            for (int j=0; j<=i; j++){ //to determine number of columns in a row
                System.out.print("*");
            }
            System.out.println("");
        }

    }
}
