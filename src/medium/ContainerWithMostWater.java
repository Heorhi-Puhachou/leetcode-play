package medium;

public class ContainerWithMostWater {

    public static void main(String... args) {
        //int[] a1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] a1 = {1, 3, 2, 5, 25, 24, 5};
        System.out.println((new ContainerWithMostWater()).maxArea(a1));
    }

    /**
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     * <p>
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * <p>
     * Return the maximum amount of water a container can store.
     * <p>
     * Notice that you may not slant the container.
     */

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxHeight = Math.min(height[left], height[right]);
        int maxAmount = maxHeight * (right - left);

        while (left != right) {
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
            int tempHeight = Math.min(height[left], height[right]);
            if (maxHeight < tempHeight) {
                maxHeight = tempHeight;
                maxAmount = Math.max((right - left) * tempHeight, maxAmount);
            }
        }
        return maxAmount;
    }
}
