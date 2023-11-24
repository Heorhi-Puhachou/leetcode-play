package hard;

import java.util.*;

public class MinimumWindowSubstring {
    public static void main(String... args) {
        System.out.println((new MinimumWindowSubstring()).minWindow("ADOBECODEBANC", "ABC"));
        //System.out.println((new MinimumWindowSubstring()).minWindow("ab", "A"));
    }

    /**
     * Given two strings s and t of lengths m and n respectively, return the minimum window
     * substring
     * of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
     * <p>
     * The testcases will be generated such that the answer is unique.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     * <p>
     * Example 2:
     * <p>
     * Input: s = "a", t = "a"
     * Output: "a"
     * Explanation: The entire string s is the minimum window.
     * <p>
     * Example 3:
     * <p>
     * Input: s = "a", t = "aa"
     * Output: ""
     * Explanation: Both 'a's from t must be included in the window.
     * Since the largest window of s only has one 'a', return empty string.
     */

    static class Counter {
        private int needed;
        private int presented;


        Counter(int needed, int presented) {
            this.needed = needed;
            this.presented = presented;
        }

        public boolean hasEnough() {
            return needed <= presented;
        }

        public void increaseNeeded() {
            needed++;
        }

        public void increasePresented() {
            presented++;
        }

        public boolean increasePresentedAndGetEnough() {
            presented++;
            return needed <= presented;
        }

        public boolean decreasePresentedAndGetEnough() {
            presented--;
            return needed <= presented;
        }
    }

    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        if (s.equals(t) || s.startsWith(t)) {
            return t;
        } else if (s.length() == 1) {
            return "";
        }

        String minSub = "";
        int left = 0;
        Map<String, Counter> symbols = new HashMap<>();
        Set<String> initSymbols = new HashSet<>();
        boolean needGoRight = true;
        boolean leftMoveStarted = false;

        String symbolToSearch = "";

        for (char c : t.toCharArray()) {
            String symbol = String.valueOf(c);
            if (symbols.containsKey(symbol)) {
                symbols.get(symbol).increaseNeeded();
                initSymbols.add(symbol);

            } else {
                symbols.put(symbol, new Counter(1, 0));
                initSymbols.add(symbol);
                symbolToSearch = symbol;
            }
        }
        String firstSymbol = s.substring(0, 1);
        if (symbols.containsKey(firstSymbol)) {
            symbols.get(firstSymbol).increasePresented();
            if (symbols.get(firstSymbol).hasEnough()) {
                initSymbols.remove(firstSymbol);
            }
        }
        int rightEnd = s.length() - 1;

        for (int right = 0; right < rightEnd || !needGoRight; ) {
            if (needGoRight) {
                right++;
                String addedSymbol = s.substring(right, right + 1);
                if (leftMoveStarted && addedSymbol.equals(symbolToSearch)) {
                    symbols.get(symbolToSearch).increasePresented();
                    needGoRight = false;
                } else if (!leftMoveStarted && initSymbols.contains(addedSymbol)) {
                    if (symbols.get(addedSymbol).increasePresentedAndGetEnough()) {
                        initSymbols.remove(addedSymbol);
                    }

                    if (initSymbols.isEmpty()) {
                        needGoRight = false;
                        leftMoveStarted = true;
                    } else {
                        symbolToSearch = initSymbols.iterator().next();
                    }
                } else if (symbols.containsKey(addedSymbol)) {
                    symbols.get(addedSymbol).increasePresented();
                }
            } else {
                String tempResult = s.substring(left, right + 1);
                if (tempResult.length() < minSub.length() || minSub.isEmpty()) {
                    minSub = tempResult;
                }
                String previousLeft = s.substring(left, left + 1);
                left++;
                if (symbols.containsKey(previousLeft) && !symbols.get(previousLeft).decreasePresentedAndGetEnough()) {
                    symbolToSearch = previousLeft;
                    needGoRight = true;
                }
            }
        }
        return minSub;
    }
}
