package org.wigs;

public class VerticalStarsBaseUp {
    public static void main(String[] args) {
        int[] arr = {2,4,3}; // Given array
        printVerticalStarsBaseUp(arr);
    }

    public static void printVerticalStarsBaseUp(int[] arr) {
        // Find the maximum height
        int maxHeight = 0;
        for (int stars : arr) {
            if (stars > maxHeight) {
                maxHeight = stars;
            }
        }

        System.out.println("maxHeight : "+maxHeight);

        // Print the vertical stars
        for (int i = 1; i <= maxHeight; i++) { // number of rows = max height

            for (int j =0; j < arr.length; j++){ // Number of column in each row
                // value in columns
                if(arr[j] >= i){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }

            }

            System.out.println(""); // Trim to remove extra space at the end
        }
    }
}
