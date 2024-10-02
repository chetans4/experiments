package org.wigs.lrucache;

public class UseLRUCache {

    public static void main(String[] args) {
        LRUCache<Integer> lruCache = new LRUCache<>(4);

        lruCache.put("one", 11);
        lruCache.put("two", 22);
        System.out.println("getting one key : "+lruCache.get("one"));

        lruCache.put("three", 33);
        lruCache.put("three", 33);
        System.out.println("getting two key : "+lruCache.get("two"));
        System.out.println("getting five key : "+lruCache.get("five"));
        lruCache.put("four", 44);
        lruCache.put("five", 55);
    }
}
