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
        int y = Math.min(height[0], height[height.length - 1]);
        int x = height.length - 1;
        int maxY = y;
        int maxAmount = y * x;
        int startRight = height.length - 1;

        for (int i = 0; i < startRight; i++) {
            if (height[i] > maxY) {
                for (int j = startRight; j > i; j--) {
                    if (height[j] > maxY) {
                        int tempY = Math.min(height[i], height[j]);
                        int tempX = j - i;
                        int tempAmount = tempX * tempY;
                        if (tempAmount > maxAmount) {
                            startRight = j;
                            maxAmount = tempAmount;
                            maxY = tempY;
                        }
                        if (height[j] >= height[i]) {
                            break;
                        }
                    }
                }
            }
        }
        return maxAmount;
    }
}
