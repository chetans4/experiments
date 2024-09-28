package org.wigs;

public class VerticalStars {
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 6}; // Given array
        printVerticalStars(arr);
    }

    public static void printVerticalStars(int[] arr) {
        // Find the maximum height
        int maxHeight = 0;
        for (int stars : arr) {
            if (stars > maxHeight) {
                maxHeight = stars;
            }
        }

        System.out.println("maxHeight : "+maxHeight);

        // Print the vertical stars
        for (int i = 0; i < maxHeight; i++) { // number of rows = max height
            for (int j = 0; j < arr.length; j++) { // columns will be same as items in provided array
                // Check if we need to print a star or a space
//                System.out.println("arr[j] "+arr[j]+" maxHeight - i : "+(maxHeight - i));
                if (arr[j] >= maxHeight - i) {
                    System.out.print("*");
                } else {
                    System.out.print(" "); // Two spaces for alignment
                }
            }
            System.out.println(""); // Trim to remove extra space at the end
        }
    }
}
