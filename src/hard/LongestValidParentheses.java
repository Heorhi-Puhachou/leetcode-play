package hard;

import java.util.*;

public class LongestValidParentheses {

    public static void main(String... args) {
        //System.out.println((new LongestValidParentheses()).longestValidParentheses(")()())"));
        System.out.println((new LongestValidParentheses()).longestValidParentheses("(()()(())(("));
        //System.out.println((new LongestValidParentheses()).longestValidParentheses("()()))"));
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
        if (s.length() < 2) {
            return 0;
        }
        if (s.length() == 2) {
            if (s.equals("()")) {
                return 2;
            } else {
                return 0;
            }
        }
        char[] string = s.toCharArray();
        List<Integer> splitIndexes = new ArrayList<>();
        List<Integer> splitIndexesInverted = new ArrayList<>();
        int balanceCloseValue = 0;
        int balanceOpenValue = 0;

        for (int i = 0; i < string.length; i++) {
            if (string[i] == '(') {
                balanceCloseValue++;
            } else {
                balanceCloseValue--;
                if (balanceCloseValue < 0) {
                    splitIndexes.add(i);
                    balanceCloseValue = 0;
                }
            }

            int openIndex = string.length - i - 1;
            if (string[openIndex] == ')') {
                balanceOpenValue++;
            } else {
                balanceOpenValue--;
                if (balanceOpenValue < 0) {
                    splitIndexesInverted.add(openIndex);
                    balanceOpenValue = 0;
                }
            }


        }

        int[] resultSpit;
        if ((splitIndexes.size() + splitIndexesInverted.size()) == 0) {
            return s.length();
        } else if (splitIndexes.size() == 0) {
            resultSpit = listToInvertArray(splitIndexesInverted);
        } else if (splitIndexesInverted.size() == 0) {
            resultSpit = listToArray(splitIndexes);
        } else {
            resultSpit = mergeSortedArrays(splitIndexes, splitIndexesInverted);
        }

        int max = resultSpit[0];
        resultSpit[resultSpit.length - 1] = s.length();

        for (int i = 1; i < resultSpit.length; i++) {
            int temp = resultSpit[i] - resultSpit[i - 1] - 1;
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public int[] mergeSortedArrays(List<Integer> nums1, List<Integer> nums2inverted) {
        int resultLength = nums1.size() + nums2inverted.size();
        int[] resultArray = new int[resultLength + 1];
        int firstIndex = 0;
        int invertedIndex = nums2inverted.size() - 1;
        for (int i = 0; i < resultLength; i++) {
            if ((firstIndex < nums1.size()) && (nums1.get(firstIndex) < nums2inverted.get(invertedIndex))) {
                resultArray[i] = nums1.get(firstIndex);
                firstIndex++;
            } else {
                resultArray[i] = nums2inverted.get(invertedIndex);
                invertedIndex--;
            }
        }

        return resultArray;
    }

    private int[] listToArray(List<Integer> input) {
        int[] result = new int[input.size() + 1];
        for (int i = 0; i < input.size(); i++) {
            result[i] = input.get(i);
        }
        return result;
    }

    private int[] listToInvertArray(List<Integer> input) {
        int[] result = new int[input.size() + 1];
        for (int i = 0; i < input.size(); i++) {
            result[i] = input.get(input.size() - 1 - i);
        }
        return result;
    }
}
