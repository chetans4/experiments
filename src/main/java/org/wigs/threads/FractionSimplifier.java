package org.wigs.threads;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FractionSimplifier {
    public static void main(String[] args) {
        double num = 3.75;
        BigDecimal bd = new BigDecimal(num).multiply(BigDecimal.valueOf(100));
        BigInteger numerator = bd.toBigInteger();
        System.out.println("numerator : "+numerator);

        BigInteger denominator = BigInteger.valueOf(100);
        BigInteger gcd = numerator.gcd(denominator);
        System.out.println("gcd : "+gcd);

        System.out.println("Simplified: " + numerator.divide(gcd) + "/" + denominator.divide(gcd));
    }
}

