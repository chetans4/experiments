package org.wigs.lrucache;

import java.util.LinkedHashMap;

/**
 * Java 8 introduced the accessOrder attribute for LinkedHashMap,
 * allowing you to specify whether the map should maintain insertion order or access order.
 *
 * Every time a key is accessed, it is moved to the end of the list.
 * This can be enabled by passing true for the accessOrder parameter in the constructor.
 *
 */
public class LRULinkedHashMap {
    public static void main(String[] args) {
        // Every time a key is accessed, it is moved to the end of the list.
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        map.get("banana"); // Access the "banana" key

//        map.remove("cherry");

        // Now traversal reflects access order:
        for (String key : map.keySet()) {
            System.out.println(key); // Output: apple, cherry, banana
        }

    }
}
