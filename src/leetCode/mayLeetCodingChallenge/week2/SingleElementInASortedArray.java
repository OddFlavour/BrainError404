package leetCode.mayLeetCodingChallenge.week2;

public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            // XOR cancels out other XOR
            ans ^= num;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] tests = {
                {1, 1, 2, 3, 3, 4, 4, 8, 8},
                {3, 3, 7, 7, 10, 11, 11},
                {1, 1, 2},
                {1}
        };

        for (int i = 0; i < tests.length; i++) {
            SingleElementInASortedArray s = new SingleElementInASortedArray();
            System.out.println(s.singleNonDuplicate(tests[i]));
        }
    }
}
