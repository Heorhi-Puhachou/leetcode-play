import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    /**
     * Given a string s, find the length of the longest
     * substring
     * without repeating characters.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * <p>
     * Example 2:
     * <p>
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * <p>
     * Example 3:
     * <p>
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * <p>
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= s.length <= 5 * 104
     * s consists of English letters, digits, symbols and spaces.
     */

    public static int lengthOfLongestSubstring(String s) {
        char[] input = s.toCharArray();
        int maxResult = 0;
        int currentResult = 0;

        Map<Integer, Integer> elementLastIndex = new HashMap<>();
        for (int element : input) {
            elementLastIndex.put(element, element);
        }
        int uniqueElementsCount = elementLastIndex.size();
        elementLastIndex.clear();

        for (int i = 0; i < input.length; i++) {
            int element = input[i];
            if (elementLastIndex.containsKey(element)) {
                if (currentResult > maxResult) {
                    if (uniqueElementsCount == currentResult) {
                        return currentResult;
                    }
                    maxResult = currentResult;
                }
                i = elementLastIndex.get(element);
                elementLastIndex.clear();
                currentResult = 0;
            } else {
                currentResult++;
                elementLastIndex.put(element, i);
            }
        }
        return Math.max(currentResult, maxResult);
    }
}