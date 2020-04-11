package leetCode.challenge30Day.week1;

public class MaximumSubarray {
    public int maxSubArrayNSquaredRuntime(int[] nums) {
        int ans = -Integer.MAX_VALUE;
        int length = nums.length;

        int[] partialSums = new int[length + 1];

        for (int i = 1; i <= length; i++) {
            partialSums[i] = nums[i - 1] + partialSums[i - 1];
        }

        for (int j = length; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                ans = Math.max(partialSums[j] - partialSums[i], ans);
            }
        }

        return ans;
    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(currSum + nums[i], nums[i]);
            max = Math.max(currSum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] t1 = { -2,1,-3,4,-1,2,1,-5,4 };
        int[] t2 = { -2, 1, 3, 4, -12, 5, 1, -13, 2, 3, 4, 5 };

        MaximumSubarray s = new MaximumSubarray();
        System.out.println(s.maxSubArray(t1));
        System.out.println(s.maxSubArray(t2));

        System.out.println(s.maxSubArrayNSquaredRuntime(t1));
        System.out.println(s.maxSubArrayNSquaredRuntime(t2));
    }
}
