package org.wigs.ims;

import java.util.*;

public class SortDuplicatesDescending {
    public static void main(String[] args) {
        int[] arr = {4, 2, 9, 2, 8, 4, 4, 7, 8, 2, 5, 9};

        int[] result = sortDuplicates(arr);

        System.out.println("Sorted duplicates array: " + Arrays.toString(result));
    }

    public static int[] sortDuplicates(int[] arr) {

        System.out.println("arr : "+Arrays.toString(arr));

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count occurrences of each element
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        System.out.println("frequencyMap : "+frequencyMap);

        // Extract only duplicates
        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }


        // Sort duplicates in descending order
        duplicates.sort(Collections.reverseOrder());

        System.out.println("duplicates : "+duplicates);

        // Reconstruct the array, replacing duplicates with sorted values
        //TODO: This logic is incorrect.
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (frequencyMap.get(arr[i]) > 1) {
                arr[i] = duplicates.get(index++);
            }
        }

        return arr;
    }
}

