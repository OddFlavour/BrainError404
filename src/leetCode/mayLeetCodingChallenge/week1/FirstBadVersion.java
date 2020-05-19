package leetCode.mayLeetCodingChallenge.week1;

public class FirstBadVersion {

    private boolean isBadVersion(int index) {
        return index >= 1702766719;
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n;

        while (left < right) {
            int mid = (int) (((long) left + right) / 2);
            System.out.println(mid);

            if (!isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        FirstBadVersion s = new FirstBadVersion();
        System.out.println(s.firstBadVersion(2126753390));
    }
}
