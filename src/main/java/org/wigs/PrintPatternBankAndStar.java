package org.wigs;

public class PrintPatternBankAndStar {

    /**
     *         *
     *       * *
     *     * * *
     *   * * * *
     * * * * * *
     * @param args
     */
    public static void main(String[] args) {

        int n =5;
        for(int i =0; i<5; i++ ){ // row
            for(int j=1; j<=5; j++){ // column
                if(j >= n-i){
                    System.out.print("* "); // extra space to se the alignment
                }else {
                    System.out.print("  ");
                }

            }
            System.out.println("");
        }


    }

}
