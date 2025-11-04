package easy;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int prev = nums[0];
        int insertPos = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != prev) {
                nums[insertPos] = nums[i];
                insertPos++;
                prev = nums[i];
            }
        }
        return insertPos;
    }

    public static void main(String... args) {
        RemoveDuplicatesFromSortedArray test = new RemoveDuplicatesFromSortedArray();
        int[] array = new int[]{1, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 7};
        long startTime = System.nanoTime();
        System.out.println(test.removeDuplicates(array) + "\n\n");
        for (int number : array) {
            System.out.println(number);
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }


}
