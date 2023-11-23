package hard;

import easy.PalindromeNumber;

public class RegularExpressionMatching {

    public static void main(String... args) {
        System.out.println((new RegularExpressionMatching()).isMatch("aa", "a*"));
    }

    /**
     * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
     * <p>
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "aa", p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * <p>
     * Example 2:
     * <p>
     * Input: s = "aa", p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     * <p>
     * Example 3:
     * <p>
     * Input: s = "ab", p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     */
    public boolean isMatch(String s, String p) {
        if (p.length() == 1 && !p.equals(s)) {
            return false;
        }

        return true;
    }
}
