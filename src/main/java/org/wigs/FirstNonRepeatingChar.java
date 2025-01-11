package org.wigs;

import java.util.LinkedHashMap;

public class FirstNonRepeatingChar {

    //a b c b c d a s l w
    public static void main(String[] args) {
        String str = "abcbcdaslw";
//        char nonRepeting = str.charAt(0);
//        for (int i = 1; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if(nonRepeting )
//        }

        Character c = getFirstCharNonRep(str);
        System.out.println("First char is : "+c);

    }

    private static Character getFirstCharNonRep(String str) {
        LinkedHashMap<Character, Integer> charCounts = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        for (Character key : charCounts.keySet()){
            if(charCounts.get(key) == 1){
                return key;
            }
        }
        return null;
    }
}
