package org.wigs;

public class PermMissingElem {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4, 6};
        int missingValue = findMissing(arr);
        System.out.println("missingValue : "+missingValue);
    }

    private static int findMissing(int[] arr) {
        // as per question total numbers are n+1
        int sum = (arr.length+1)*(arr.length+2)/2;
        int arrSum = 0;
        for (int i : arr){
            arrSum +=i;
        }
        return sum - arrSum;
    }
}
