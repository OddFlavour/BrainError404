package leetCode.mayLeetCodingChallenge.week2;

public class SingleElementInASortedArray {
    public int singleNonDuplicateBigN(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            // XOR cancels out other XOR
            ans ^= num;
        }

        return ans;
    }

    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            mid -= mid % 2;

            if (nums[mid + 1] == nums[mid]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        int[][] tests = {
                {1, 1, 2, 3, 3, 4, 4, 8, 8},
                {3, 3, 7, 7, 10, 11, 11},
                {1, 1, 2},
                {1}
        };

        int[] customTest = new int[699999999];
        int num = 0;
        for (int i = 0; i < customTest.length - 1; i+=2) {
            customTest[i] = customTest[i + 1] = num++;
        }

        customTest[customTest.length - 1] = num;

        SingleElementInASortedArray s = new SingleElementInASortedArray();
        long start1 = System.currentTimeMillis();
        int a = s.singleNonDuplicate(customTest);
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        int b = s.singleNonDuplicateBigN(customTest);
        long end2 = System.currentTimeMillis();

        System.out.printf("A took %dms, answered: %d\n", end1 - start1, a);
        System.out.printf("B took %dms, answered: %d\n", end2 - start2, b);

//        for (int i = 0; i < tests.length; i++) {
//            SingleElementInASortedArray s = new SingleElementInASortedArray();
//            System.out.println(s.singleNonDuplicate(tests[i]));
//        }
    }

    /*
    [1,1,2,3,3,4,4,8,8]

    [1,1,2,2,3,4,4]
    [1,1,2,3,3,4,4]

    [1,2,2]
     */
}
