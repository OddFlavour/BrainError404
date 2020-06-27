package leetCode.juneLeetCodingChallenge.week4;

public class FindTheDuplicateNumber {
    // Tortoise and hare problem
    public int findDuplicate(int[] nums) {
        int i1 = 0, i2 = 0;

        do {
            i1 = nums[i1];
            i2 = nums[nums[i2]];
        } while (i1 != i2);

        i2 = 0;

        while (i1 != i2) {
            i1 = nums[i1];
            i2 = nums[i2];
        }

        // Return i1 here because the duplicate is found
        return i1;
    }

    public static void main(String[] args) {
        int[][] testNums = {
                {1, 3, 4, 2, 2},
                {3, 1, 3, 4, 2},
                {2, 2, 2, 2, 2}
        };

        for (int i = 0; i < testNums.length; i++) {
            FindTheDuplicateNumber s = new FindTheDuplicateNumber();
            System.out.println(s.findDuplicate(testNums[i]));
        }
    }
}
