package hard;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {

    public static void main(String... args) {
        int[] a1 = {0, 1, 2};
        System.out.println((new FirstMissingPositive()).firstMissingPositive(a1));
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


    public int firstMissingPositive(int[] nums) {
        Set<Integer> input = new HashSet<Integer>();
        for (Integer num : nums) {
            input.add(num);
        }

        int oldSize = input.size();

        for (int i = 1; i < nums.length + 1; i++) {
            input.add(i);
            if (input.size() > oldSize) {
                return i;
            }
        }
        return input.size() + 1;
    }
}
