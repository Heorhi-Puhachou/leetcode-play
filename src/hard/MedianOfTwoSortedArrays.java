package hard;

public class MedianOfTwoSortedArrays {

    public static void main(String... args) {
        int[] a1 = {1, 3};
        int[] a2 = {2};
        System.out.println((new MedianOfTwoSortedArrays()).findMedianSortedArrays(a1, a2));
    }

    /**
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * <p>
     * The overall run time complexity should be O(log (m+n)).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums1 = [1,3], nums2 = [2]
     * Output: 2.00000
     * Explanation: merged array = [1,2,3] and median is 2.
     * <p>
     * Example 2:
     * <p>
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 2.50000
     * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int resultLength = nums1.length + nums2.length;
        int[] resultArray = new int[resultLength];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < resultLength; i++) {
            if ((secondIndex > nums2.length - 1) || ((firstIndex < nums1.length) && (nums1[firstIndex] < nums2[secondIndex]))) {
                resultArray[i] = nums1[firstIndex];
                firstIndex++;
            } else {
                resultArray[i] = nums2[secondIndex];
                secondIndex++;
            }
        }

        if (resultLength % 2 == 0) {
            return ((double) (resultArray[resultLength / 2 - 1] + resultArray[resultLength / 2])) / 2;
        } else {
            return resultArray[resultLength / 2];
        }
    }
}
