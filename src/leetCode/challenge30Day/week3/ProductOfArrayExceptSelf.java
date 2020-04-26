package leetCode.challenge30Day.week3;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        rec(0, 1, nums);

        return nums;
    }

    public int rec(int i, int leftProduct, int[] nums) {
        if (i == nums.length) return 1;

        int temp = nums[i];
        int rightProduct = rec(i + 1, leftProduct * temp, nums);

        nums[i] = leftProduct * rightProduct;

        return temp * rightProduct;
    }

    public static void main(String[] args) {
        int[] t1 = {1,2,3,4};

        ProductOfArrayExceptSelf s = new ProductOfArrayExceptSelf();
        printArray(s.productExceptSelf(t1));
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
