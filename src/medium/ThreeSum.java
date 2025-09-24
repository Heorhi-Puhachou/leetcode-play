package medium;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        HashSet<List<Integer>> triples = new HashSet<>();
        Set<Integer> uniqueNumbers = new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();
        boolean hasThreeZeros = false;

        //optimization

        for (int number : nums) {

            if (uniqueNumbers.contains(number)) {
                if (number == 0 && duplicates.contains(number)) {
                    hasThreeZeros = true;
                } else {
                    duplicates.add(number);
                }
            } else {
                uniqueNumbers.add(number);
            }

        }

        duplicates.remove(0);

        if (hasThreeZeros) {
            triples.add(Arrays.asList(0, 0, 0));
        }

        for (int duplicate : duplicates) {
            if (uniqueNumbers.contains(duplicate * -2)) {
                if (duplicate > 0) {
                    triples.add(Arrays.asList(-2 * duplicate, duplicate, duplicate));
                } else {
                    triples.add(Arrays.asList(duplicate, duplicate, -2 * duplicate));
                }
            }
        }

        int[] optimizedArray = uniqueNumbers.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();

        for (int baseNumIndex = 0; baseNumIndex < optimizedArray.length; baseNumIndex++) {
            int left = baseNumIndex + 1;
            int right = optimizedArray.length - 1;
            while (left < right) {
                int sum = optimizedArray[baseNumIndex] + optimizedArray[left] + optimizedArray[right];
                if (sum == 0) {
                    List<Integer> option = Arrays.asList(optimizedArray[baseNumIndex], optimizedArray[left], optimizedArray[right]);
                    triples.add(option);
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(triples);

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