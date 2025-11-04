package medium;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int numsLength = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numsLength - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = numsLength - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (nums[left - 1] == nums[left] && left < right) left++;
                    while (nums[right + 1] == nums[right] && left < right) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum test = new ThreeSum();
        long startTime = System.nanoTime();
        System.out.println(test.threeSum(new int[]{
                3, 0, 0, -2, -1, 1, 2
        }));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}