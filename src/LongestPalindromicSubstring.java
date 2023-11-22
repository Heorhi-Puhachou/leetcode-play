public class LongestPalindromicSubstring {
    public static void main(String... args) {
        System.out.println((new LongestPalindromicSubstring()).longestPalindrome("abb"));
    }

    /**
     * Given a string s, return the longest
     * palindromic
     * substring
     * in s.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "babad"
     * Output: "bab"
     * Explanation: "aba" is also a valid answer.
     * <p>
     * Example 2:
     * <p>
     * Input: s = "cbbd"
     * Output: "bb"
     */

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        if (chars.length == 2 && chars[0] == chars[1]) {
            return s;
        }
        int palindromeCenter = 0;
        int palindromeRadius = 0;
        //abccba - palindrome with 2 central elements
        //abcba - palindrome with 1 central element
        boolean palindromeMonoCenter = true;

        for (int tempCenter = 0; tempCenter < chars.length; tempCenter++) {

            //palindrome1 check
            int tempRadius = getMonoPalindromeRadius(chars, tempCenter);
            boolean tempPalindromeMonoCenter = true;

            //palindrome2 check

            int doubleCenterRadius = get2CenterPalindromeRadius(chars, tempCenter);
            if (doubleCenterRadius >= tempRadius) {
                tempRadius = doubleCenterRadius;
                tempPalindromeMonoCenter = false;
            }

            if (tempRadius > palindromeRadius
                    ||
                    (tempRadius == palindromeRadius && palindromeMonoCenter && !tempPalindromeMonoCenter)) {
                palindromeCenter = tempCenter;
                palindromeRadius = tempRadius;
                palindromeMonoCenter = tempPalindromeMonoCenter;
            }
        }

        if (palindromeMonoCenter) {
            return s.substring(palindromeCenter - palindromeRadius, palindromeCenter + palindromeRadius + 1);
        } else {
            return s.substring(palindromeCenter - palindromeRadius, palindromeCenter + 2 + palindromeRadius);
        }
    }

    private int getMonoPalindromeRadius(char[] chars, int center) {
        int radius = 0;
        while (center - radius > 0 && center + radius < chars.length - 1) {
            if (chars[center + radius + 1] == chars[center - radius - 1]) {
                radius++;
            } else {
                return radius;
            }
        }
        return radius;
    }

    private int get2CenterPalindromeRadius(char[] chars, int leftCenter) {
        int radius = -1;
        if(leftCenter+1==chars.length){
            return radius;
        }
        if (chars[leftCenter] == chars[leftCenter + 1]) {
            radius = 0;
        }
        while (leftCenter - radius > 0 && leftCenter + 1 + radius < chars.length - 1) {
            if (chars[leftCenter + 1 + radius + 1] == chars[leftCenter - radius - 1]) {
                radius++;
            } else {
                return radius;
            }
        }
        return radius;
    }
}
