package org.wigs.ims;

public class CustomHashMap<K, V> {
    private static final int SIZE = 16; // Default bucket size
    private Node<K, V>[] buckets;

    // Custom Node class for key-value pairs
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Node[SIZE];
    }

    // Returns index for null key as 0; for non-null keys, use hashCode.
    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % SIZE;
    }

    // Put method: Inserts or updates the key-value pair
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> head = buckets[index];

        if (head == null) {
            buckets[index] = newNode;
            return;
        }

        Node<K, V> current = head;
        Node<K, V> prev = null;
        while (current != null) {
            // Use safe equality check to handle potential null keys
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                current.value = value; // Update existing key
                return;
            }
            prev = current;
            current = current.next;
        }
        // Append new node at the end of the chain
        prev.next = newNode;
    }

    // Get method: Returns the value associated with the key
    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Key not found
    }

    // Delete method: Removes the key-value pair from the map
    public void delete(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                if (prev == null) {
                    // Remove head of the chain
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // Helper method to print the entire map's contents
    public void printMap() {
        for (int i = 0; i < SIZE; i++) {
            Node<K, V> current = buckets[i];
            if (current != null) {
                System.out.print("Bucket " + i + ": ");
                while (current != null) {
                    System.out.print("[" + current.key + "=" + current.value + "] -> ");
                    current = current.next;
                }
                System.out.println("null");
            }
        }
    }

    // Main method for demonstration and testing
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("B", 25);  // Updating the value for key "B"
        map.put("D", 40);
        map.put(null, 50); // Testing a null key

        System.out.println("Get B: " + map.get("B"));      // Expected output: 25
        System.out.println("Get null: " + map.get(null));    // Expected output: 50

        map.delete("B");
        System.out.println("Get B after deletion: " + map.get("B")); // Expected output: null

        map.printMap();
    }
}

