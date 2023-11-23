package easy;

public class PalindromeNumber {
    public static void main(String... args) {
        System.out.println((new PalindromeNumber()).isPalindrome(121));
    }

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
