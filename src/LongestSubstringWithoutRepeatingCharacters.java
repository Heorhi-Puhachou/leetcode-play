import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
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
        char [] input = s.toCharArray();
        int maxResult = 0;
        int currentResult = 0;
        Set<String> uniqueElements = new HashSet<>();
        Map<String, Integer> elementLastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String element = ""+input[i];
            int oldSize = uniqueElements.size();
            uniqueElements.add(element);
            int newSize = uniqueElements.size();
            if (newSize > oldSize) {
                currentResult++;
                elementLastIndex.put(element, i);
            } else {
                if (currentResult > maxResult) {
                    maxResult = currentResult;
                }
                int newStart = elementLastIndex.get(element);
                elementLastIndex.put(element, i);
                i=newStart+1;
                uniqueElements = new HashSet<>();
                uniqueElements.add(""+input[i]);
                currentResult = 1;
            }

            if (i == s.length() - 1) {
                if (currentResult > maxResult) {
                    maxResult = currentResult;
                }
            }
        }
        return maxResult;
    }
}