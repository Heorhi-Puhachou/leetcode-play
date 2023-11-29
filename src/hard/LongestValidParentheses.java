package hard;

import java.util.*;

public class LongestValidParentheses {

    public static void main(String... args) {
        //System.out.println((new LongestValidParentheses()).longestValidParentheses(")()())"));
        System.out.println((new LongestValidParentheses()).longestValidParentheses("(()()("));
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
        int originalLength = s.length();
        char[] string = s.toCharArray();
        List<Integer> splitIndexes = new ArrayList<>(originalLength);
        List<Integer> splitIndexesInverted = new ArrayList<>(originalLength);
        int balanceCloseValue = 0;
        int balanceOpenValue = 0;

        for (int openIndex = 0, closeIndex = originalLength - 1;
             openIndex < originalLength;
             openIndex++, closeIndex--) {

            // find bad '('
            if (string[openIndex] == '(') {
                balanceCloseValue++;
            } else {
                balanceCloseValue--;
                if (balanceCloseValue < 0) {
                    splitIndexes.add(openIndex);
                    balanceCloseValue = 0;
                }
            }

            // find bad ')'
            if (string[closeIndex] == ')') {
                balanceOpenValue++;
            } else {
                balanceOpenValue--;
                if (balanceOpenValue < 0) {
                    splitIndexesInverted.add(closeIndex);
                    balanceOpenValue = 0;
                }
            }
        }

        if (splitIndexes.isEmpty() && splitIndexesInverted.isEmpty()) {
            return originalLength;
        }

        int[] resultSplit;

        if (splitIndexes.isEmpty()) {
            resultSplit = listToReversedArray(splitIndexesInverted);
        } else if (splitIndexesInverted.isEmpty()) {
            resultSplit = listToArray(splitIndexes);
        } else {
            resultSplit = mergeSortedAscAndDescArrays(splitIndexes, splitIndexesInverted);
        }
        int max = resultSplit[0];
        resultSplit[resultSplit.length - 1] = originalLength;
        for (int i = 1; i < resultSplit.length; i++) {
            int temp = resultSplit[i] - resultSplit[i - 1] - 1;
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public int[] mergeSortedAscAndDescArrays(List<Integer> nums1, List<Integer> nums2inverted) {
        int resultLength = nums1.size() + nums2inverted.size();
        // +1 for last range element
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
        // +1 for last range element
        int[] result = new int[input.size() + 1];
        for (int i = 0; i < input.size(); i++) {
            result[i] = input.get(i);
        }
        return result;
    }

    private int[] listToReversedArray(List<Integer> input) {
        int[] result = listToArray(input);
        int half = (input.size()) / 2;
        // swap values
        // a = a ^ b;  // a = 1111 (15)
        // b = a ^ b;  // b = 1010 (5)
        // a = a ^ b;  // a = 0101 (10)
        for (int first = 0, last = input.size() - 1;
             first < half;
             first++, last--) {
            result[first] = result[last] ^ result[first];
            result[last] = result[first] ^ result[last];
            result[first] = result[first] ^ result[last];
        }
        return result;
    }
}
