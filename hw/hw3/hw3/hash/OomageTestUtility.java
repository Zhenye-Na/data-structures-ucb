package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] buckets = new int[M];
        for (Oomage mage : oomages) {
            int bucketNum = (mage.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;
        }

        return (findSmallest(buckets) > (oomages.size() / 50) && findLargest(buckets) < (oomages.size() / 2.5));

    }

    private static int findLargest(int[] arr) {
        int i;

        // Initialize maximum element
        int max = arr[0];

        // Traverse array elements from second and
        // compare every element with current max
        for (i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        return max;
    }

    private static int findSmallest(int[] arr) {
        int i;

        // Initialize maximum element
        int min = arr[0];

        // Traverse array elements from second and
        // compare every element with current max
        for (i = 1; i < arr.length; i++)
            if (arr[i] < min)
                min = arr[i];

        return min;
    }
}
