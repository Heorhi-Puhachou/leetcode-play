package easy;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        return insertPos;
    }

    public static void main(String... args) {
        RemoveElement test = new RemoveElement();
        int[] array = new int[]{3, 1, 1, 2, 2, 3, 3, 4, 5, 5, 3, 7};
        long startTime = System.nanoTime();
        System.out.println(test.removeElement(array, 3) + "\n\n");
        for (int number : array) {
            System.out.println(number);
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }


}
