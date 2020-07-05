package leetCode.juneLeetCodingChallenge.week3;

public class HIndexII {
    public int hIndex(int[] citations) {
        if (citations.length == 0) return 0;

        int ans = 0;
        int n = citations.length;

        // Want to be greedy so start from the highest possible hIndex
        int curr = n - 1;
        for (int value = citations[n - 1]; value >= 0; value--) {
            int missing = value - (n - curr);

            int index = curr - missing;
            if (index >= 0 && index < n && citations[index] >= value) {
                ans = value;
                break;
            }
        }

        return ans;
    }

    public int hIndexLogN(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            // This does a final check for non-zero solutions, in the case of a zero solution, it'll go through the
            // while loop one more time, and therefore return '0'
            if (left == right && n - left <= citations[left])
                return n - left;

            int mid = (left + right) / 2;

            if (n - mid > citations[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[][] tests = {
//                {0, 1, 3, 5, 6},
//                {0, 1, 2, 3, 4},
//                {0, 0, 0, 0, 0},
//                {1, 1, 1, 1, 1},
//                {4, 4, 4, 4},
//                {},
                {0},
                {1},
                {2},
                {100},
                {0, 1, 2, 4, 4, 4}
        };

        int[] answers = {
                3,
                2,
                0,
                1,
                4,
                0,
                0,
                1,
                1,
                1,
                3
        };

        for (int i = 0; i < tests.length; i++) {
            HIndexII s = new HIndexII();
            System.out.println(
                    s.hIndexLogN(tests[i])
                            == answers[i]
            );
        }
    }
}
