package leetCode.juneLeetCodingChallenge.week2;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return target > nums[nums.length - 1] ? nums.length : l;
    }

    public static void main(String[] args) {
        int[][] testNums = {
                {1, 3, 5, 6},
                {1, 3, 5, 6},
                {1, 3, 5, 6},
                {1, 3, 5, 6},
                {1, 3, 5, 7}
        };

        int[] testTargets = {
                5,
                2,
                7,
                0,
                6
        };

        for (int i = 0; i < testNums.length; i++) {
            SearchInsertPosition s = new SearchInsertPosition();
            System.out.println(s.searchInsert(testNums[i], testTargets[i]));
        }
    }
}
