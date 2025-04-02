package org.wigs.ims;

import java.util.*;

public class FrequencySort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 5, 4, 3, 4};
        System.out.println(Arrays.toString(sortByFrequency(arr)));
    }

    public static int[] sortByFrequency(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : arr) list.add(num);

        list.sort((a, b) -> {
            int freqCompare = freqMap.get(b).compareTo(freqMap.get(a)); // Descending frequency
            return freqCompare != 0 ? freqCompare : a.compareTo(b); // Ascending value if equal
        });

        return list.stream().mapToInt(i -> i).toArray();
    }
}

