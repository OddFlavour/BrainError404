package leetCode.juneLeetCodingChallenge.week2;

public class SortColors {
    public void sortColors(int[] nums) {
        int bottom = 0, middle = 0, top = nums.length - 1;

        while (middle <= top) {
            switch (nums[middle]) {
                case 0:
                    swap(nums, bottom++, middle++);
                    break;
                case 1:
                    middle++;
                    break;
                case 2:
                    swap(nums, top--, middle);
                    break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {0, 1, 2, 0, 1, 2}
        };

        for (int i = 0; i < tests.length; i++) {
            int[] nums = tests[i];

            for (int n : nums) {
                System.out.printf("%d, ", n);
            }
            System.out.println();

            SortColors s = new SortColors();
            s.sortColors(nums);

            for (int n : nums) {
                System.out.printf("%d, ", n);
            }
            System.out.println();
        }
    }
}
