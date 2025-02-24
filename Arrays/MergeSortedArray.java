package Arrays;

import java.util.Arrays;

public class MergeSortedArray {

    public static void Brute(int[] nums1, int m, int[] nums2, int n) {
        int merged[] = new int[m + n];

        // Copy elements from nums1
        for (int i = 0; i < m; i++) {
            merged[i] = nums1[i];
        }

        // Copy elements from nums2
        for (int i = 0; i < n; i++) {
            merged[m + i] = nums2[i];
        }

        // Sort the merged array
        Arrays.sort(merged);

        // Copy back to nums1
        for (int i = 0; i < m + n; i++) {
            nums1[i] = merged[i];
        }
    }

    public static void Better(int[] nums1, int m, int[] nums2, int n) {
        int merged[] = new int[m + n];

        int left = 0, right = 0, index = 0;

        while (left < m && right < n) {
            if (nums1[left] <= nums2[right]) {
                merged[index++] = nums1[left++];
            } else {
                merged[index++] = nums2[right++];
            }
        }

        while (left < m) {
            merged[index++] = nums1[left++];
        }

        while (right < n) {
            merged[index++] = nums2[right++];
        }

        // Copy back to nums1
        for (int i = 0; i < m + n; i++) {
            nums1[i] = merged[i];
        }
    }

    public static void Optimal(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        // Merge from the back
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        // Copy remaining elements from nums2
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }

    public static void Optimal2(int[] nums1, int m, int[] nums2, int n) {
        // Step 1: Copy nums2 into nums1 at the end
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }

        // Step 2: Start with an initial gap
        int gap = (m + n) / 2;
        
        while (gap > 0) {
            int i = 0, j = gap;
            
            // Step 3: Compare and swap if needed
            while (j < (m + n)) {
                if (nums1[i] > nums1[j]) {
                    // Swap nums1[i] and nums1[j]
                    int temp = nums1[i];
                    nums1[i] = nums1[j];
                    nums1[j] = temp;
                }
                i++;
                j++;
            }

            // Step 4: Reduce gap
            if (gap == 1) {
                break; // Stop when gap is 1
            }
            gap = (gap + 1) / 2; // Reduce the gap (Ceil value)
        }
    }

    public static void main(String[] args) {
        int nums1[] = { 1, 2, 3, 0, 0, 0 };
        int nums2[] = { 2, 5, 6 };
        int m = 3;
        int n = 3;

        Optimal2(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}