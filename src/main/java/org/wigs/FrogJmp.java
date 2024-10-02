package org.wigs;

public class FrogJmp {


    public static void main(String[] args) {
        int X = 10, Y = 101, D = 30;
        int jumpCount = jumpCount(X, Y, D);
        System.out.println("total jump will be : "+jumpCount);
    }

    private static int jumpCount(int x, int y, int d) {
        if(((y-x) % d) > 0){
            return ((y-x) / d) + 1;
        }else{
            return (y-x) / d;
        }
    }
}
