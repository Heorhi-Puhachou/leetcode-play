import java.util.HashMap;
import java.util.Map;

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
        char[] charInput = s.toCharArray();

        // find quantity of unique elements
        Map<Integer, Integer> elementLastIndex = new HashMap<>();
        for (int element : charInput) {
            // use HashMap in this way because it will be reused later
            elementLastIndex.put(element, null);
        }
        int uniqueElementsCount = elementLastIndex.size();

        // stop here if charInput contains only unique elements
        if (charInput.length == uniqueElementsCount) {
            return uniqueElementsCount;
        }

        // clear HashMap before use in more traditional way
        elementLastIndex.clear();

        int maxResult = 0;
        int currentResult = 0;

        for (int i = 0; i < charInput.length; i++) {
            int element = charInput[i];
            if (elementLastIndex.containsKey(element)) {
                if (currentResult > maxResult) {
                    maxResult = currentResult;
                }
                //start search again without repeated element (from next one)
                i = elementLastIndex.get(element);
                elementLastIndex.clear();
                currentResult = 0;
            } else {
                // stop if we found all unique elements
                if (uniqueElementsCount == ++currentResult) {
                    return currentResult;
                }
                elementLastIndex.put(element, i);
            }
        }
        return (currentResult > maxResult) ? currentResult : maxResult;
    }
}