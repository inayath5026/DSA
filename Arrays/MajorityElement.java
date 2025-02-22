package Arrays;
import java.util.Arrays;

class MajorityElement {

    public static int Brute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            if (count > nums.length / 2) {
                return nums[i];
            }

        }
        return 0;
    }

    public static int Better(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static int Optimal(int[] nums) {
        int max = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (max == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    max = nums[i];
                    count = 1;
                }
            }
        }

        return max;
    }

    public static void main(String args[]) {
        int nums[] = { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println(Brute(nums));
    }
}

/*
 * 
 * The Key Idea (Majority Wins the Election) 
 * 
 * Think of this like an election where each number in the array is a candidate.
 * 
 * - We are looking for the strongest candidate (majority element).
 * - If two different candidates meet, they cancel each other out.
 * - Since the majority element appears more than half the time, it will always be
 *   the last one standing.
 * 
 * 
 */