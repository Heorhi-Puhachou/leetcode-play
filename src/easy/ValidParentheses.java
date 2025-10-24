package easy;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        if (s.length() % 2 == 1) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (char character : chars) {
            if (character == '(' || character == '[' || character == '{') {
                stack.push(character);
            }

            if (character == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != '(') {
                    return false;
                }
            }

            if (character == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != '[') {
                    return false;
                }
            }

            if (character == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != '{') {
                    return false;
                }
            }

        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses test = new ValidParentheses();
        long startTime = System.nanoTime();
        System.out.println(test.isValid("()"));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
