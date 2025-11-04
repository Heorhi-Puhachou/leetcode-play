package medium;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        process("", n, n, result);

        return result;

    }

    private void process(String input, int open, int close, List<String> result) {
        if (open == 0 && close == 0) {
            result.add(input);
            return;
        }
        if (open > 0) {
            process(input + "(", open - 1, close, result);
        }
        if (close > open) {
            process(input + ")", open, close - 1, result);
        }
    }

    public static void main(String... args) {
        GenerateParentheses test = new GenerateParentheses();

        long startTime = System.nanoTime();
        System.out.println(test.generateParenthesis(4));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
