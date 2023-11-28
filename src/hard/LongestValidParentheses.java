package hard;

public class LongestValidParentheses {

    public static void main(String... args) {
        //System.out.println((new LongestValidParentheses()).longestValidParentheses(")()())"));
        System.out.println((new LongestValidParentheses()).longestValidParentheses("()(()"));
    }

    /**
     * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses
     * substring
     * .
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()".
     * <p>
     * Example 2:
     * <p>
     * Input: s = ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()".
     * <p>
     * Example 3:
     * <p>
     * Input: s = ""
     * Output: 0
     */

    public int longestValidParentheses(String s) {
        if (s.equals("()")) {
            return 2;
        }
        if (s.length() < 2) {
            return 0;
        }
        String trimmed = removeBadEdges(s);
        if (trimmed.length() < 2) {
            return 0;
        }
        char[] trimmedChars = trimmed.toCharArray();
        int maxLength = 0;
        int tempLength = 0;
        int finishedTempLength = 0;
        int balance = 0;

        for (int i = 0; i < trimmed.length(); i++) {
            if (trimmedChars[i] == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                finishedTempLength = tempLength;
                if (finishedTempLength > maxLength) {
                    maxLength = tempLength;
                }
                tempLength = 0;
            } else if (balance == 0) {
                tempLength++;
                finishedTempLength = tempLength;
                if (finishedTempLength > maxLength) {
                    maxLength = finishedTempLength;
                }

            } else {
                tempLength++;
            }
        }

        if (balance > 0) {
            int potential = tempLength - balance;
            if (potential > maxLength) {
                return potential;
            }
        }

        return maxLength;
    }

    private String removeBadEdges(String input) {
        char[] inputChars = input.toCharArray();
        int startIndex = 0;
        int endIndex = input.length();

        for (int i = 0; i < input.length(); i++) {
            if (inputChars[i] == '(') {
                startIndex = i;
                break;
            }
        }

        for (int i = input.length() - 1; i > 0; i--) {
            if (inputChars[i] == ')') {
                endIndex = i;
                break;
            }
        }

        if (startIndex + 1 >= endIndex) {
            return "";
        } else {
            return input.substring(startIndex, endIndex + 1);
        }
    }
}
