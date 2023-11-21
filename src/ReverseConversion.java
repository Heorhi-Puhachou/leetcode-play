public class ReverseConversion {
    public static void main(String... args) {
        System.out.println((new ReverseConversion()).convert("PAYPALISHIRING", 3));
        //System.out.println((new ReverseConversion()).convert("A", 1));
        System.out.println((new ReverseConversion()).convert("AB", 1));
    }

    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * <p>
     * And then read line by line: "PAHNAPLSIIGYIR"
     * <p>
     * Write the code that will take a string and make this conversion given a number of rows:
     * <p>
     * string convert(string s, int numRows);
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "PAYPALISHIRING", numRows = 3
     * Output: "PAHNAPLSIIGYIR"
     * <p>
     * Example 2:
     * <p>
     * Input: s = "PAYPALISHIRING", numRows = 4
     * Output: "PINALSIGYAHRPI"
     * Explanation:
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * <p>
     * * 1     7    13   19
     * * 2   6 8  1214 1820
     * * 3 5   9 11 1517 2123
     * * 4     10   16   22
     * <p>
     * 01      09      17      25     +8     +8     +8
     * 02    0810    1618    24       +6  +2 +6  +2 +6
     * 03  07  11  15  19  23         +4  +4 +4  +4 +4
     * 0406    1214    2022           +2  +6 +2  +6 +2
     * 05      13      21             +8     +8
     * <p>
     * <p>
     * 01        11        21        31 +10 (+6 +(6-2)
     * 02      1012      2022      3032 +8 +2
     * 03    09  13    19  23    29     +6 +4
     * 04  08    14  18    24  28       +4 +6
     * 0507      1517      2527         +2 +8
     * 06        16        26           +10
     * <p>
     * 1-1
     * 7-2
     * 12-3
     * 17-4
     * 22-5
     * 27-6
     * 32-7
     * <p>
     * Example 3:
     * <p>
     * Input: s = "A", numRows = 1
     * Output: "A"
     */

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int magicNumber = numRows + numRows - 2;
        char[] result = new char[chars.length];
        int resultIndex = 0;
        for (int row = 0; row < numRows; row++) {
            boolean edge = false;
            int increase1 = magicNumber - row * 2;
            int increase2 = row * 2;
            if (increase1 == 0 || increase2 == 0) {
                edge = true;
            }
            int increaseSum = increase2 + increase1;
            int increaseIndex = row;
            while (increaseIndex < chars.length) {
                result[resultIndex++] = chars[increaseIndex];
                if (edge) {
                    increaseIndex += increaseSum;
                } else {
                    increaseIndex = increaseIndex + increase1;
                    if (increaseIndex < chars.length) {
                        result[resultIndex++] = chars[increaseIndex];
                        increaseIndex += increase2;
                    }
                }
            }
        }
        return String.valueOf(result);
    }
}
