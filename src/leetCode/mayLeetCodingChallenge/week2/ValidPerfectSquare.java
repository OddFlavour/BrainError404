package leetCode.mayLeetCodingChallenge.week2;

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int i = 1;
        while (i <= num) {
            double result = (double) num / i;
            if (result == i) return true;

            // early return false
            if (result < i) return false;

            i++;
        }

        return false;
    }

    public static void main(String[] args) {

        for (int i = 0; i <= 100; i++) {
            ValidPerfectSquare s = new ValidPerfectSquare();
            System.out.println(i + ": " + s.isPerfectSquare(i));
        }
    }
}
