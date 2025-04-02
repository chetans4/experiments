package org.wigs.ims;

import java.util.HashMap;
import java.util.Map;

public class SecondHighestFrequency {
    public static void main(String[] args) {
        int[] arr = {4, 2, 9, 2, 8, 4, 4, 7, 8, 2, 5, 9, 8, 8};

        Integer result = findSecondHighestFrequency(arr);

        if (result != null) {
            System.out.println("Element with second highest frequency: " + result);
        } else {
            System.out.println("No second highest frequency found.");
        }
    }

    public static Integer findSecondHighestFrequency(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Step 1: Count occurrences
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        System.out.println("frequencyMap : "+frequencyMap);

        // Step 2: Find the highest and second-highest frequencies
        int highestFreq = 0, secondHighestFreq = 0;

        for (int freq : frequencyMap.values()) {
            if (freq > highestFreq) {
                secondHighestFreq = highestFreq;
                highestFreq = freq;
            } else if (freq > secondHighestFreq && freq < highestFreq) {
                secondHighestFreq = freq;
            }
        }

        // Step 3: Find the element with the second highest frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == secondHighestFreq) {
                return entry.getKey();
            }
        }

        return null; // No second highest frequency found
    }
}

