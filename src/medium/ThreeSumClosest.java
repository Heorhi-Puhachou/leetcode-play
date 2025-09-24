package medium;

public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];
        int minDiff = result - target < 0 ? target - result : result - target;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int currentSum = nums[i] + nums[j] + nums[k];
                    int currentDiff = currentSum - target < 0 ? target - currentSum : currentSum - target;
                    if (currentDiff < minDiff) {
                        minDiff = currentDiff;
                        result = currentSum;
                    }
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        ThreeSumClosest test = new ThreeSumClosest();
        long startTime = System.nanoTime();
        System.out.println(test.threeSumClosest(new int[]{
                -1, 2, 1, -4
        }, -4));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}