package easy;

public class PalindromeNumber {
    public static void main(String... args) {
        System.out.println((new PalindromeNumber()).isPalindrome(121));
    }

    /**
     * Given an integer x, return true if x is a
     * palindrome, and false otherwise.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: x = 121
     * Output: true
     * Explanation: 121 reads as 121 from left to right and from right to left.
     * <p>
     * Example 2:
     * <p>
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * <p>
     * Example 3:
     * <p>
     * Input: x = 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     */

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        return x == reverse(x);
    }

    public int reverse(int input) {
        int reversedNum = 0;

        while (input != 0) {
            reversedNum = reversedNum * 10 + input % 10;
            input = input / 10;
        }
        return reversedNum;
    }
}
