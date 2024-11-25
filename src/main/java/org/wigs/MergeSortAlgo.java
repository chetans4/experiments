package org.wigs;

import java.util.Arrays;

/**
 * Divide and Conquer Sorting Algo
 */
public class MergeSortAlgo {

    private int[] array;
    private int[] tempArray;

    public static void main(String[] args) {
        int arr[] = {4,7,6,1,2,8,5,3,0};
        new MergeSortAlgo().arrangementsForMergeSort(arr);
        System.out.println("\nAfter sorting");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + "\t");

//        Arrays.sort(new String[]{"", "", ""});

    }

    private void arrangementsForMergeSort(int[] arr) {
        this.array = arr;
        this.tempArray = new int[arr.length];
        doMergeSort(0, arr.length -1);
    }

    private void doMergeSort(int low, int high) {
        if(low < high){
            int middle = low + (high - low) / 2;
            doMergeSort(low, middle);
            doMergeSort(middle +1, high);
            mergeElements(low, middle, high);
        }

    }

    private void mergeElements(int low, int middle, int high) {

        for (int i = low; i <= high; i++) {
            tempArray[i] = array[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high){
            if (tempArray[i] <= array[j]){
                array[k] = tempArray[i];
                i++;
            }else {
                array[k] = tempArray[j];
                j++;
            }
            k++;
        }

        //Items which are not less than half part elements
        while (i <= middle){
            array[k] = tempArray[i];
            k++;
            i++;
        }

    }

}
