package org.wigs;

public class BinaryGap {

    public static void main(String[] args) {

        int maxGap = findMaxBinaryGap(1041);
        System.out.println("max gap is : "+maxGap);
    }

    private static int findMaxBinaryGap(int positiveNumber) {
        String binaryStr = Integer.toBinaryString(positiveNumber);
        System.out.println("Binary Representation of "+positiveNumber+", is : "+binaryStr);

        int maxGap = 0;
        int currentGap = 0;
        boolean counting = false;

        for(char digit : binaryStr.toCharArray()){
            if(digit == '1'){
                //Start counting
                if (counting && maxGap < currentGap){
                    maxGap = currentGap;
                }

                counting= true;
                currentGap = 0;
            }else if (counting){
                currentGap ++;
            }
        }
        return maxGap;
    }

}
