package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubstringWithConcatenationOfAllWords {
    public static void main(String... args) {
        String s = "aaa";
        String[] words = {"a", "a"};

        //String s = "goodgoodbestword";
        //String[] words = {"word", "good", "best", "good"};

        //String s = "wordgoodgoodgoodbestword";
        //String[] words = {"word", "good", "best", "good"};

        //String s = "ababaab";
        //String[] words = {"ab", "ba", "ba"};

        System.out.println((new SubstringWithConcatenationOfAllWords()).findSubstring(s, words));
    }

    /**
     * You are given a string s and an array of strings words. All the strings of words are of the same length.
     * <p>
     * A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
     * <p>
     * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
     * <p>
     * Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "barfoothefoobarman", words = ["foo","bar"]
     * Output: [0,9]
     * Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
     * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
     * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
     * The output order does not matter. Returning [9,0] is fine too.
     * <p>
     * Example 2:
     * <p>
     * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
     * Output: []
     * Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
     * There is no substring of length 16 in s that is equal to the concatenation of any permutation of words.
     * We return an empty array.
     * <p>
     * Example 3:
     * <p>
     * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * Output: [6,9,12]
     * Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
     * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which is a permutation of words.
     * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which is a permutation of words.
     * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which is a permutation of words.
     */

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        String firstWord = words[0];
        int wordLength = firstWord.length();
        boolean sameSymbol = s.replaceAll(s.substring(0, 1), "").isEmpty();
        boolean sameWords = true;
        int substringLength = words.length * wordLength;

        if (substringLength > s.length()) {
            return result;
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!words[i].equals(words[i + 1])) {
                sameWords = false;
                break;
            }
        }

        char[] sChars = s.toCharArray();

        if (wordLength == 1) {
            if (sameSymbol && sameWords) {
                // original string like "xxxxxxx", and words like "x", "x", ..., "x"
                if (s.substring(0, 1).equals(firstWord)) {
                    for (int i = 0; i < s.length() - substringLength + 1; i++) {
                        result.add(i);
                    }
                }
                // original string like "xxxxxxx", and words like "y", "y", ..., "y"
                else {
                    return result;
                }
            }
            // original string like "xyzxxzyyyxxx", and words like "x", "y", ..., "z"
            else {
                boolean previousSuccess = false;
                for (int i = 0; i <= s.length() - substringLength; i++) {
                    String toCheck = s.substring(i, i + substringLength);
                    if (previousSuccess) {
                        if (sChars[i + substringLength - 2] == sChars[i + substringLength - 1]) {
                            result.add(i);
                        } else {
                            previousSuccess = false;
                        }
                    }
                    if (!previousSuccess && checkSubstringForMonoWord(toCheck, words)) {
                        result.add(i);
                        previousSuccess = true;
                    } else {
                        previousSuccess = false;
                    }
                }
            }
        }
        // original string like "xyzxxzyyyxxx", and words like "xyz", "xxz", ..., "yyy"
        else {
            for (int i = 0; i <= s.length() - substringLength; i++) {
                String toCheck = s.substring(i, i + substringLength);
                if (checkSubstring(toCheck, words, wordLength)) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private boolean checkSubstring(String subString, String[] words, int wordLength) {
        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
        for (int index = 0; index < subString.length() / wordLength; index++) {
            String possibleWord = subString.substring(index * wordLength, (index + 1) * wordLength);
            int possibleIndex = wordsList.indexOf(possibleWord);
            if (possibleIndex > -1) {
                // some kind of remove, but not remove
                wordsList.set(possibleIndex, "");
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkSubstringForMonoWord(String subString, String[] words) {
        for (String word : words) {
            subString = subString.replaceFirst(word, "");
        }
        return subString.isEmpty();
    }
}
