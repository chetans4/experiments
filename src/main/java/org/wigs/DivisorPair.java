package org.wigs;

import java.util.*;

public class DivisorPair {

    public static void main(String[] args) {
        int[] arr = {5, 10, 1, 9, 14, 1, 16, 20, 5, 40, 30};
        int k = 5;

//        O(n^2)
        int pairCount = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if((arr[i] + arr[j]) % k == 0){
                    pairCount++;
                }
            }
        }
        System.out.println("Pair count with O(n^2) : "+pairCount);

//      == =====================================================================
//        O(n)
//        int[] arr = {5, 10, 1, 9, 14, 1, 16, 20, 5, 40, 30};
//        int k = 5;
        pairCount = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int reminder = arr[i] % k;
            if(!map.containsKey(reminder)){
                map.put(reminder, new ArrayList<>());
            }
            map.get(reminder).add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            int reminder = arr[i] % k;
//            System.out.println(i+" reminder : "+reminder);

            int diff = reminder !=0 ? k - reminder : 0;
//            System.out.println(i+" diff : "+diff);

            List<Integer> list = map.get(diff);
//            System.out.println(i+"list : "+list);

            pairCount = pairCount + (Objects.nonNull(list) ? list.size() : 0);

//            if(diff == reminder){
//                pairCount--;
//            }
        }

        System.out.println("reminder map : "+map);
        System.out.println("Pair count with O(n) : "+pairCount);

    }
}
