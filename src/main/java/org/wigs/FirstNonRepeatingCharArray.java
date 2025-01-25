package org.wigs;

public class FirstNonRepeatingCharArray {

    public static void main(String[] args) {
        String str = "abcbscdaslwdlmsw";
        Character c = getFirstCharNonRep(str);
        System.out.println("First char is : "+c);
    }

    private static Character getFirstCharNonRep(String str){
        int[] charCount = new int[256]; // ASCII supports up to 256 characters

        for (int i = 0; i < str.length(); i++) {
            charCount[str.charAt(i)]++;
        }

//        System.out.println("Array count values: "+ Arrays.toString(charCount));

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (charCount[c] == 1) {
                return c;
            }
        }
        return null;
    }
}
