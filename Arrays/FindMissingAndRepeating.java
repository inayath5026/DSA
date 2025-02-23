package Arrays;

import java.util.HashSet;

public class FindMissingAndRepeating {

    public static void Brute(int[][] grid) {
        int n = grid.length;
        int total = n * n;
        int[] count = new int[total + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count[grid[i][j]]++;
            }
        }

        int repeat = -1, missing = -1;
        for (int i = 1; i <= total; i++) {
            if (count[i] == 2) {
                repeat = i;
            } else if (count[i] == 0) {
                missing = i;
            }
            if (repeat != -1 && missing != -1) {
                break;
            }
        }

        System.out.println("Repeating: " + repeat + ", Missing: " + missing);
    }

    public static void Optimal(int[][] grid) {
        int n = grid.length;
        int total = n * n;

        long S = (total * (total + 1)) / 2;
        long S2 = (total * (total + 1) * (2 * total + 1)) / 6;

        long sum = 0, sumSq = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
                sumSq += (long) grid[i][j] * grid[i][j];
            }
        }

        long diff = S - sum; // X - Y
        long diffSq = S2 - sumSq; // X² - Y²

        long sumXY = diffSq / diff; // (X + Y)

        int missing = (int) ((diff + sumXY) / 2);
        int repeating = (int) (sumXY - missing);

        System.out.println("Repeating: " + repeating + ", Missing: " + missing);
    }

    public static void usingHashset(int[][] grid) {
        int n = grid.length;
        int total = n * n;

        // Step 1: Calculate expected sum
        int expectedSum = (total * (total + 1)) / 2;
        int sumOfGrid = 0;
        int repeating = -1;

        HashSet<Integer> set = new HashSet<>();

        // Step 2: Traverse the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i][j];

                // Step 3: Check if number is repeated
                if (set.contains(num)) {
                    repeating = num;
                } else {
                    set.add(num);
                }

                sumOfGrid += num;
            }
        }

        // Step 4: Find missing number
        int missing = expectedSum - (sumOfGrid - repeating);

        System.out.println("Repeating: " + repeating + ", Missing: " + missing);
    }

    public static void main(String[] args) {
        int grid[][] = { { 9, 1, 7 }, { 8, 9, 2 }, { 3, 4, 6 } };
        usingHashset(grid);
    }
    
}