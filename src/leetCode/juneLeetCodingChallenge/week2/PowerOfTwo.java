package leetCode.juneLeetCodingChallenge.week2;

public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        long i = 1;
        while (i < n) {
            i *= 2;
        }

        return i == n;
    }

    public static void main(String[] args) {
        for (int i = 1073741825; i <= 1073741825; i++) {
            PowerOfTwo s = new PowerOfTwo();
            if (s.isPowerOfTwo(i))
                System.out.printf("%d\n", i);
        }
    }
}
