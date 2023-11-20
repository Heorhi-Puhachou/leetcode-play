public class StringToIntegerAtoi {

    public static void main(String[] args) {
        //System.out.println((new StringToIntegerAtoi()).isNumber('0'));
        //System.out.println((new StringToIntegerAtoi()).isNumber('9'));
        //System.out.println((new StringToIntegerAtoi()).myAtoi("-000000000000001"));
        //System.out.println((new StringToIntegerAtoi()).myAtoi("010"));
        //System.out.println((new StringToIntegerAtoi()).myAtoi("+-110"));
        //System.out.println((new StringToIntegerAtoi()).myAtoi("   +0 123"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("2147483646"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("-91283472332"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("2147483648"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("-2147483647"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("-13+8"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("  +  413"));
        System.out.println((new StringToIntegerAtoi()).myAtoi("    -88827   5655  U"));
    }

    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     * <p>
     * The algorithm for myAtoi(string s) is as follows:
     * <p>
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     * <p>
     * Note:
     * <p>
     * Only the space character ' ' is considered a whitespace character.
     * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "42"
     * Output: 42
     * Explanation: The underlined characters are what is read in, the caret is the current reader position.
     * Step 1: "42" (no characters read because there is no leading whitespace)
     * ^
     * Step 2: "42" (no characters read because there is neither a '-' nor '+')
     * ^
     * Step 3: "42" ("42" is read in)
     * ^
     * The parsed integer is 42.
     * Since 42 is in the range [-231, 231 - 1], the final result is 42.
     * <p>
     * Example 2:
     * <p>
     * Input: s = "   -42"
     * Output: -42
     * Explanation:
     * Step 1: "   -42" (leading whitespace is read and ignored)
     * ^
     * Step 2: "   -42" ('-' is read, so the result should be negative)
     * ^
     * Step 3: "   -42" ("42" is read in)
     * ^
     * The parsed integer is -42.
     * Since -42 is in the range [-231, 231 - 1], the final result is -42.
     * <p>
     * Example 3:
     * <p>
     * Input: s = "4193 with words"
     * Output: 4193
     * Explanation:
     * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
     * ^
     * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
     * ^
     * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
     * ^
     * The parsed integer is 4193.
     * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
     * <p>
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= s.length <= 200
     * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
     */


    public int myAtoi(String s) {
        boolean isNegative = false;
        boolean plusPresented = false;
        boolean previousZero = false;
        char[] num = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
        int numIndex = 0;

        char[] chars = s.toCharArray();

        int index = 0;
        while (index < chars.length) {
            char symbol = chars[index];
            if (isSpace(symbol)) {
                if (numIndex > 0) {
                    break;
                }
                if (plusPresented || isNegative || previousZero) {
                    return 0;
                }
                index++;
                continue;
            }
            if (previousZero && !isNumber(symbol)) {
                return 0;
            }
            if (isPlus(symbol)) {
                if (numIndex > 0) {
                    break;
                }
                if (plusPresented || isNegative) {
                    return 0;
                }
                plusPresented = true;
            }

            if (isZero(symbol) && numIndex == 0) {
                previousZero = true;
                index++;
                continue;
            }
            if (isLetter(symbol)) {
                break;
            }
            if (numIndex == 0 && isDot(symbol)) {
                return 0;
            }
            if (numIndex > 0 && !isNumber(symbol)) {
                break;
            }

            if (isMinus(symbol)) {
                if (numIndex > 0) {
                    break;
                }
                if (isNegative || plusPresented) {
                    return 0;
                }
                isNegative = true;

            }

            if (isNumber(symbol)) {
                previousZero = false;
                if (numIndex > 9 && !isNegative) {
                    return 2147483647;
                }
                if (numIndex > 9) {
                    return -2147483648;
                }
                num[numIndex] = symbol;
                numIndex++;
            }
            index++;
        }

        if (numIndex == 10 && outOfRange(num, isNegative)) {
            if (isNegative) {
                return -2147483648;
            } else {
                return 2147483647;
            }
        }


        int result = 0;
        for (int i = 0; i < numIndex; i++) {
            int arrayValue = num[i] - 48;
            int valuePow = (int) Math.pow(10, numIndex - 1 - i);
            int finalValue = arrayValue * valuePow;
            result = result + finalValue;
        }
        if (isNegative) {
            return result * -1;
        }
        return result;
    }

    public boolean isZero(char symbol) {
        return 48 == symbol;
    }

    public boolean isNumber(char symbol) {
        return 47 < symbol && symbol < 58;
    }

    public boolean isLetter(char symbol) {
        return 'a' <= symbol && symbol <= 'z';
    }

    public boolean isDot(char symbol) {
        return '.' == symbol;
    }

    public boolean isMinus(char symbol) {
        return symbol == '-';
    }

    public boolean isPlus(char symbol) {
        return symbol == '+';
    }

    public boolean isSpace(char symbol) {
        return symbol == ' ';
    }

    private boolean outOfRange(char[] input, boolean isNegative) {
        char toCompare[];
        char[] minInt = {'2', '1', '4', '7', '4', '8', '3', '6', '4', '8'};
        char[] maxInt = {'2', '1', '4', '7', '4', '8', '3', '6', '4', '7'};
        if (isNegative) {
            toCompare = minInt;
        } else {
            toCompare = maxInt;

        }
        if (input.length > toCompare.length) {
            return true;
        }
        if (input.length < toCompare.length) {
            return false;
        }
        for (int index = 0; index < 10; index++) {
            if (input[index] > toCompare[index]) {
                return true;
            }
            if (input[index] < toCompare[index]) {
                return false;
            }
        }
        return false;
    }
}