package neetcode.arraysandhashing;

/**
 * https://neetcode.io/problems/products-of-array-discluding-self
 */
public class ProductsOfArrayExceptSelf {
    static class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] leftProducts = new int[n + 1];
            int[] rightProducts = new int[n + 1];

            leftProducts[0] = rightProducts[n - 1] = 1;

            for (int i = 0; i < n; i++) {
                leftProducts[i + 1] = leftProducts[i] * nums[i];
            }

            for (int i = n - 1; i >= 0; i--) {
                rightProducts[i] = rightProducts[i + 1] * nums[i];
            }

            int[] ret = new int[n];

            for (int i = 0; i < n; i++) {
                ret[i] = leftProducts[i] * rightProducts[i + 1];
            }

            return ret;
        }
    }
}
