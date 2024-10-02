package org.wigs.lrucache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LRUCache<T> {

    private final int capacity;
    private int size;
    private final HashMap<String, Node> map;
    private DoublyLinkedList internalQueue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.internalQueue = new DoublyLinkedList();
        this.size = 0;
    }

    public T get(String key) {
        Node node = map.get(key);
        if (Objects.isNull(node)) {
            return null;
        }

        internalQueue.moveNodeToFront(node);
        return node.value;
    }

    public void put(String key, T value) {
        //check if node exists
        //check size and capacity > removeNodeFromRear;
        //Add node to front
        //move node to front

        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            internalQueue.moveNodeToFront(node);
            return;
        }

        if (size == capacity) {
            String rearKey = internalQueue.getRearKey();
            internalQueue.removeNodeFromRear();
            map.remove(rearKey);
            size--;
        }

        node = new Node(key, value);
        internalQueue.addNodeToFront(node);
        map.put(key, node);
        size++;
    }

    private class DoublyLinkedList {
        private Node front, rear;

        public DoublyLinkedList() {
            front = rear = null;
        }

        public void addNodeToFront(Node node) {
            if (Objects.isNull(node)) {
                rear = front = null;
            }

            if(rear == null) {
                front = rear = node;
                return;
            }

            node.next = front;
            front.prev = node;
            front = node;
            System.out.println("node added to front : "+node);
        }

        public void moveNodeToFront(Node node) {
            if (front == node) {
                System.out.println("node already on front : "+node);
                return;
            }

            if (rear == node) {
                rear = rear.prev;
                rear.next = null;
            } else {
                //Somewhere in middle
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.prev = null;
            node.next = front;
            front.prev = node;
            front = node;
            System.out.println("node moved to front : "+node);
        }

        public String getRearKey() {
            return rear.key;
        }

        public void removeNodeFromRear() {
            if (rear == null) {
                return;
            }

            System.out.println("Removing node with key: " + getRearKey());
            if(front == rear) {
                front = rear = null;
            } else {
                rear = rear.prev;
                rear.next = null;
            }
        }
    }

    private class Node {

        String key;
        T value;
        Node next, prev;

        public Node(final String key, final T value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
