public class ReverseInteger {

    public static void main(String... args) {
        System.out.println((new ReverseInteger()).reverse(-123));
    }

    /**
     * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     * <p>
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: x = 123
     * Output: 321
     * <p>
     * Example 2:
     * <p>
     * Input: x = -123
     * Output: -321
     * <p>
     * Example 3:
     * <p>
     * Input: x = 120
     * Output: 21
     */

    public int reverse(int x) {
        if (x == -2147483648) {
            return 0;
        }
        boolean isNegative = x < 0;
        char[] toCompare = {'2', '1', '4', '7', '4', '8', '3', '6', '4', '7'};
        char[] chars = isNegative
                ?
                Integer.toString(x * -1).toCharArray()
                :
                Integer.toString(x).toCharArray();
        boolean continueCheck = chars.length == 10;
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            int reverseIndex = chars.length - i - 1;
            int reverseValue = chars[reverseIndex] - 48;
            if (continueCheck) {
                int toCompareValue = toCompare[i] - 48;
                if (reverseValue > toCompareValue) {
                    return 0;
                }
                if (reverseValue < toCompareValue) {
                    continueCheck = false;
                }
            }

            int valuePow = (int) Math.pow(10, reverseIndex);
            int finalValue = reverseValue * valuePow;
            result = result + finalValue;
        }
        if (isNegative) {
            return result * -1;
        }
        return result;
    }
}
