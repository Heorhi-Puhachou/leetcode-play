package easy;

public class FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String haystack, String needle) {

        if (needle.isEmpty()) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }

        // StringUTF16 method
        // public static int indexOfLatin1Unsafe(byte[] src, int srcCount, byte[] tgt, int tgtCount, int fromIndex)
        char[] sourceChar = haystack.toCharArray();
        char[] targetChar = needle.toCharArray();
        int srcCount = sourceChar.length;
        int tgtCount = targetChar.length;
        char first = targetChar[0];
        int max = (srcCount - tgtCount);
        for (int i = 0; i <= max; i++) {
            // Look for first character.
            if (sourceChar[i] != first) {
                while (++i <= max && sourceChar[i] != first) ;
            }
            // Found first character, now look at the rest of v2
            if (i <= max) {
                int j = i + 1;
                int end = j + tgtCount - 1;
                for (int k = 1; j < end && sourceChar[j] == targetChar[k]; j++, k++);
                if (j == end) {
                    // Found whole string.
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String... args) {
        FindTheIndexOfTheFirstOccurrenceInAString test = new FindTheIndexOfTheFirstOccurrenceInAString();
        long startTime = System.nanoTime();
        System.out.println(test.strStr("88sadbutsad", "sad"));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
