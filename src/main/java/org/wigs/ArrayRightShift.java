package org.wigs;

import java.util.Arrays;

public class ArrayRightShift {

    public static void main(String[] args) {
        int arr[] = {3, 8, 9, 7, 6};
        int k =3;
        int shifted[] = shiftRightBy(arr, k);
        System.out.println("Shifted array is : "+ Arrays.toString(shifted));
    }

    private static int[] shiftRightBy(int[] arr, int k) {
        int arrayLength = arr.length;
        for(int i = 0; i<k; i++){
            int lastElement = arr[arrayLength-1];
            for(int j=arrayLength-1;  j> 0 ; j--){
                arr[j]=arr[j-1];
            }
            arr[0]=lastElement;
        }
        return arr;
    }

}
