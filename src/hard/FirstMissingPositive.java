package hard;

public class FirstMissingPositive {

    public static void main(String... args) {
        //int[] a1 = {99, 94, 96, 11, 92, 5, 91, 89, 57, 85, 66, 63, 84, 81, 79, 61, 74, 78, 77, 30, 64, 13, 58, 18, 70, 69, 51, 12, 32, 34, 9, 43, 39, 8, 1, 38, 49, 27, 21, 45, 47, 44, 53, 52, 48, 19, 50, 59, 3, 40, 31, 82, 23, 56, 37, 41, 16, 28, 22, 33, 65, 42, 54, 20, 29, 25, 10, 26, 4, 60, 67, 83, 62, 71, 24, 35, 72, 55, 75, 0, 2, 46, 15, 80, 6, 36, 14, 73, 76, 86, 88, 7, 17, 87, 68, 90, 95, 93, 97, 98};
        //int[] a1 = {7, 8, 9, 11, 12};
        //int[] a1 = {1};
        //int[] a1 = {1, 2, 0};
        //int[] a1 = {2, 1};
        //int[] a1 = {-1, 4, 2, 1, 9, 10};
        //int[] a1 = {11, 1, 6, 11, 5, 5, -6, 9, -3, 9, 5, 4, 2, -8, 16, -6, -4, 2, 3};
        //int[] a1 = {3, 4, -1, 1};
        int[] a1 = {1, 1};
        System.out.println((new FirstMissingPositive()).firstMissingPositiveMemory(a1));
    }

    /**
     * Given an unsorted integer array nums, return the smallest missing positive integer.
     * <p>
     * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,0]
     * Output: 3
     * Explanation: The numbers in the range [1,2] are all in the array.
     * <p>
     * Example 2:
     * <p>
     * Input: nums = [3,4,-1,1]
     * Output: 2
     * Explanation: 1 is in the array but 2 is missing.
     * <p>
     * Example 3:
     * <p>
     * Input: nums = [7,8,9,11,12]
     * Output: 1
     * Explanation: The smallest positive integer 1 is missing.
     */


    public int firstMissingPositiveFast(int[] nums) {
        boolean[] presented = new boolean[nums.length];

        for (int num : nums) {
            if (num <= nums.length && num > 0) {
                presented[num - 1] = true;
            }
        }

        for (int i = 0; i < presented.length; i++) {
            if (!presented[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositiveMemory(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int newIndex = nums[i] - 1;
            if (nums[i] == i + 1) {
                continue;
            } else if (nums[i] > nums.length
                    || (newIndex >= 0 && newIndex < nums.length && nums[newIndex] == newIndex + 1)
                    || (nums[i] <= 0)) {
                nums[i] = 0;
            } else if (newIndex < nums.length
                    && newIndex >= 0
                    && nums[i] != nums[newIndex]) {
                //swap values
                //a = a ^ b;  // a = 1111 (15)
                //b = a ^ b;  // b = 1010 (5)
                //a = a ^ b;  // a = 0101 (10)
                nums[i] = nums[i] ^ nums[newIndex];
                nums[newIndex] = nums[i] ^ nums[newIndex];
                nums[i] = nums[i] ^ nums[newIndex];
                if (nums[i] != 0) {
                    i--;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}
