package org.wigs.ims;

import java.util.*;

public class TopTwoFrequentNumbersWithHeap {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5);

        // Step 1: Count occurrences using a HashMap
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a Max Heap (PriorityQueue) to store entries based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        maxHeap.addAll(frequencyMap.entrySet());

        // Step 3: Print the top 2 most frequent elements
        for (int i = 0; i < 2 && !maxHeap.isEmpty(); i++) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

