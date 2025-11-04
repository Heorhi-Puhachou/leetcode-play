package medium;

/*
    Given two integers dividend and divisor,
    divide two integers without using multiplication, division, and mod operator.
 */

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {

        if (dividend == divisor) {
            return 1;
        }

        if (dividend == -divisor) {
            return -1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == -1) {
            return -dividend;
        }
        boolean sameSign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }

        int result = 0;
        int currentDividend = dividend;


        while (currentDividend <= divisor) {
            int partOfDividend = 0;
            int level = 0;
            for (; partOfDividend >= currentDividend && partOfDividend <= 0 && partOfDividend != Integer.MIN_VALUE; level++) {
                partOfDividend = divisor << level;
            }
            level = level - 2;
            partOfDividend = divisor << level;
            result = result - (1 << level);
            currentDividend = currentDividend - partOfDividend;
        }

        return sameSign ? -result : result;
    }

    public static void main(String... args) {
        DivideTwoIntegers test = new DivideTwoIntegers();
        long startTime = System.nanoTime();
        System.out.println(test.divide(-2147483648, -1));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

        System.out.println(13 >> 2);
    }
}
