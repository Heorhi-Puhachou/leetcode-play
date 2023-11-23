package hard;

public class RegularExpressionMatching {

    public static void main(String... args) {
        //System.out.println((new RegularExpressionMatching()).isMatch("mississippi", "mis*is*p*."));
        //System.out.println((new RegularExpressionMatching()).isMatch("mississippi", "mis*is*ip*."));
        //System.out.println((new RegularExpressionMatching()).isMatch("bbbba", ".*a*a"));
        //System.out.println((new RegularExpressionMatching()).isMatch("aaa", "aaaa"));
        //System.out.println((new RegularExpressionMatching()).isMatch("aa", "a*"));
        //System.out.println((new RegularExpressionMatching()).isMatch("ab", ".*"));
        //System.out.println((new RegularExpressionMatching()).isMatch("aaa", "a*a"));
        //System.out.println((new RegularExpressionMatching()).isMatch("ab", ".*c"));
        //System.out.println((new RegularExpressionMatching()).isMatch("ab", ".*.."));
        //System.out.println((new RegularExpressionMatching()).isMatch("cdede", ".*de"));
        System.out.println((new RegularExpressionMatching()).isMatch("aaaaaaaaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*"));
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
        p = simplifyPattern(p);
        if (p.equals(".*")) {
            return true;
        }
        return isMatch(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    public boolean isMatch(char[] string, char[] pattern, int stringStart, int patternStart) {
        boolean stringFinished = string.length <= stringStart;
        boolean patternFinished = pattern.length == patternStart;
        boolean lastStringSymbol = stringStart + 1 == string.length;
        boolean lastPatternSymbol = patternStart + 1 == pattern.length;
        boolean extendedPattern = pattern.length > patternStart + 1 && pattern[patternStart + 1] == '*';
        if (lastPatternSymbol && lastStringSymbol &&
                (string[stringStart] == pattern[patternStart] || pattern[patternStart] == '.')) {
            return true;
        }
        boolean patternCanBeSkipped = patternCanBeSkipped(pattern, patternStart);
        boolean universalPattern = !patternFinished
                && pattern[patternStart] == '.'
                && extendedPattern;

        if (stringFinished && !patternCanBeSkipped) {
            return false;
        } else if (stringFinished) {
            return true;
        }

        if (patternFinished) {
            return false;
        }


        if (universalPattern || (extendedPattern && (string[stringStart] == pattern[patternStart] || pattern[patternStart] == '*'))) {
            return  //check next symbol with current pattern
                    isMatch(string, pattern, stringStart + 1, patternStart)
                            ||
                            //check next symbol with next part of pattern
                            isMatch(string, pattern, stringStart + 1, patternStart + 2)
                            ||
                            //check current symbol with next part of pattern
                            isMatch(string, pattern, stringStart, patternStart + 2);
        } else if (extendedPattern && pattern[patternStart] != string[stringStart]) {
            //check current symbol with next part of pattern
            return isMatch(string, pattern, stringStart, patternStart + 2);
        } else if (!extendedPattern && (pattern[patternStart] == string[stringStart] || pattern[patternStart] == '.')) {
            //check next symbol with next part of pattern
            return isMatch(string, pattern, stringStart + 1, patternStart + 1);
        } else {
            return false;
        }
    }

    private boolean patternCanBeSkipped(char[] pattern, int patternStart) {
        if ((pattern.length - patternStart) % 2 == 0) {
            for (int i = patternStart + 1; i < pattern.length; i = i + 2) {
                if (pattern[i] != '*') {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private String simplifyPattern(String p) {
        char[] pattern = p.toCharArray();
        boolean changed = false;
        int i = 0;
        for (; i < pattern.length - 3; i++) {
            if ((pattern[i] == pattern[i + 2] || pattern[i + 2] == '.')
                    && pattern[i + 1] == '*'
                    && pattern[i + 3] == '*') {
                changed = true;
                break;
            }
        }
        if (changed) {
            return simplifyPattern(p.substring(0, i) + p.substring(i + 2));
        }

        return p;
    }
}
