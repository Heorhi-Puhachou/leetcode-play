package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
represent. Return the answer in any order.

A mapping of digits to letters just like on the telephone buttons.
*/
public class LetterCombinationsOfAPhoneNumber {

    static Map<Integer, List<String>> digitToLetters = Map.of(
            2, Arrays.asList("a", "b", "c"),
            3, Arrays.asList("d", "e", "f"),
            4, Arrays.asList("g", "h", "i"),
            5, Arrays.asList("j", "k", "l"),
            6, Arrays.asList("m", "n", "o"),
            7, Arrays.asList("p", "q", "r", "s"),
            8, Arrays.asList("t", "u", "v"),
            9, Arrays.asList("w", "x", "y", "z")
    );

    private class Node {
        Node(Node parent, String symbol) {
            this.parent = parent;
            this.symbol = symbol;
        }

        Node parent;
        String symbol;
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }

        int[] intDigits = new int[digits.length()];
        for (int index = 0; index < digits.length(); index++) {
            String stringDigit = digits.substring(index, index + 1);
            intDigits[index] = Integer.parseInt(stringDigit);
        }

        List<Node> previousLevel = new ArrayList<>();
        List<Node> currentLevel = new ArrayList<>();

        for (String symbol : digitToLetters.get(intDigits[intDigits.length - 1])) {
            Node newNode = new Node(null, symbol);
            currentLevel.add(newNode);
        }

        for (int index = digits.length() - 2; index >= 0; index--) {
            previousLevel.clear();
            previousLevel.addAll(currentLevel);
            currentLevel.clear();

            int digit = intDigits[index];
            List<String> symbols = digitToLetters.get(digit);
            for (Node previousNode : previousLevel) {
                for (String symbol : symbols) {
                    Node newNode = new Node(previousNode, symbol);
                    currentLevel.add(newNode);
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Node topNode : currentLevel) {
            Node currentNode = topNode;
            while (currentNode != null) {
                stringBuilder.append(currentNode.symbol);
                currentNode = currentNode.parent;
            }
            result.add(stringBuilder.toString());
            //'clear' stringBuilder
            stringBuilder.setLength(0);
        }

        return result;
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();
        long startTime = System.nanoTime();
        System.out.println(test.letterCombinations("23"));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}