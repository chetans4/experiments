package org.wigs;

public class StarPyramid {

    public static void main(String[] args) {
        int height = 7;

        invertedPyramid(height);
//        pyramid(height);
    }

    private static void invertedPyramid(int height) {

        for (int i = height; i >= 1; i--){ // row

            for(int j = i; j < height; j++){ // column
                System.out.print(" ");
            }

            for(int k = 1 ; k <= (i * 2 - 1); k++){ // addition to previous column
                System.out.print("*");
            }
            System.out.println();
        }

    }

    private static void pyramid(int height) {
        for (int i = 1; i <= height; i++) { //Row
            // Append leading spaces
            for (int j = height - i; j > 0; j--) { // column
                System.out.print(" ");
            }

            // Append stars
            for (int k = 1; k <= (2 * i - 1); k++) { // column
                System.out.print("*");
            }

            System.out.println();
        }
    }


}
