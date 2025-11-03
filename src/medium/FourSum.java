package medium;

import java.util.*;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        int[] optimizedNums = getOptimizedNumsArray(nums);
        int numsLength = optimizedNums.length;
        for (int i = 0; i < numsLength - 3; i++) {
            if (i > 0 && optimizedNums[i - 1] == optimizedNums[i]) continue;
            for (int j = i + 1; j < numsLength - 2; j++) {
                if (j > i + 1 && optimizedNums[j - 1] == optimizedNums[j]) continue;
                int left = j + 1;
                int right = numsLength - 1;

                while (left < right) {
                    // 294967296 - 2000000000 can be problem because 214748364 is limit for int
                    long sum = (long) optimizedNums[i] + optimizedNums[j] + optimizedNums[left] + optimizedNums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(optimizedNums[i], optimizedNums[j], optimizedNums[left], optimizedNums[right]));
                        left++;
                        right--;
                        while (optimizedNums[left - 1] == optimizedNums[left] && left < right) left++;
                        while (optimizedNums[right + 1] == optimizedNums[right] && left < right) right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }

                }
            }
        }

        return result;
    }

    private static int[] getOptimizedNumsArray(int[] nums) {
        Map<Integer, Integer> repeats = new HashMap<>();
        List<Integer> optimizedNumsList = new ArrayList<>();

        for (int num : nums) {
            if (repeats.containsKey(num)) {
                int repeat = repeats.get(num);
                if (repeat < 4) {
                    optimizedNumsList.add(num);
                }
                repeats.put(num, ++repeat);
            } else {
                repeats.put(num, 1);
                optimizedNumsList.add(num);
            }
        }

        return optimizedNumsList.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
    }

    public static void main(String[] args) {
        FourSum test = new FourSum();
        long startTime = System.nanoTime();
        //System.out.println(test.fourSum(new int[]{-1000000000, -1000000000, 1000000000, -1000000000, -1000000000}, 294967296));
        //System.out.println(test.fourSum(new int[]{-5, 5, 5, 0, 2, 3, -2, -3, 0, 0}, 10));
        //System.out.println(test.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(test.fourSum(new int[]{-9, 4, 0, -3, 6, 3, -3, -9, -7, 1, 0, -7, -8, 7, 1}, -9));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
