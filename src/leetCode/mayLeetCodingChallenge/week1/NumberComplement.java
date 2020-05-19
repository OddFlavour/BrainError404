package leetCode.mayLeetCodingChallenge.week1;

public class NumberComplement {
    public int findComplement(int num) {
        long power = 1;
        while (power <= num) {
            power *= 2;
        }

        return (int) ((power - 1) - num);
    }

    public static void main(String[] args) {
        int[] tests = {5, 1, 2147483647};

        for (int test : tests) {
//        for (int test = 1; test < 10000; test++) {
            NumberComplement s = new NumberComplement();
            System.out.println(s.findComplement(test) + " " + test);
        }
    }
}
