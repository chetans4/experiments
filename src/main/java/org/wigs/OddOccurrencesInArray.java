package org.wigs;

public class OddOccurrencesInArray {

    public static void main(String[] args) {
        int arr[] = {4,9,3,9,3,9,7,9,7};
//        int unPairedElement = findUnpairedElement(arr);
        int unPairedElement = efficientAlgorithm(arr);
        System.out.println("final unpairedElement : "+unPairedElement);
    }

    private static int efficientAlgorithm(int[] arr) {
        int result = 0;

        // XOR all elements of the array
        for (int i = 0; i < arr.length; i++) {
            System.out.println("result : "+result);
            System.out.println("num : "+arr[i]);
            result ^= arr[i];
            System.out.println("result post xor : "+result);
        }

        return result;
    }

    private static int findUnpairedElement(int[] arr) {

        int unpairedElement = 0;
        boolean foundPair = false;
        for (int i = 0; i < arr.length; i++) {
            int checkingPairFor = arr[i];
//            System.out.println("checkingPairFor : "+checkingPairFor);
            for (int j = i+1; j < arr.length; j++) {
                if(checkingPairFor == arr[j]){
//                    System.out.println("found pair : "+arr[j]);
                    foundPair = true;
                }
            }
            if(!foundPair){
                unpairedElement = checkingPairFor;
//                System.out.println("unpaired : "+unpairedElement);
            }
        }
        return unpairedElement;
    }

}
