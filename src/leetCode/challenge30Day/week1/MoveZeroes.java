package leetCode.challenge30Day.week1;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int zeroIndex = 0;
        int nonzeroIndex = 0;

        while (zeroIndex < length && nonzeroIndex < length) {
            if (nums[zeroIndex] == 0) {
                if (zeroIndex > nonzeroIndex) {
                    nonzeroIndex = zeroIndex;
                }
                while (nonzeroIndex < length - 1 && nums[nonzeroIndex] == 0) {
                    nonzeroIndex++;
                }

                swap(nums, zeroIndex, nonzeroIndex);
            }
            zeroIndex++;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] t1 = { 0,1,0,3,12 };
        int[] t2 = { 0, 0, 0, 0, 0 };
        int[] t3 = { 0 };
        int[] t4 = { };
        int[] t5 = { 1, 2, 3, 4, 0, 0 };
        int[] t6 = { 0, 1, 3, 0, 1, 0 };

        MoveZeroes s = new MoveZeroes();
        s.moveZeroes(t1);
        s.moveZeroes(t2);
        s.moveZeroes(t3);
        s.moveZeroes(t4);
        s.moveZeroes(t5);
        s.moveZeroes(t6);
    }
}
