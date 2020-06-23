package leetCode.juneLeetCodingChallenge.week1;

public class ReverseString {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;

        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;

            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        String[] tests = {
                "Helloman",
                "",
                "a",
                "ab"
        };

        for (int i = 0; i < tests.length; i++) {
            ReverseString s = new ReverseString();

            char[] test = tests[i].toCharArray();
            s.reverseString(test);

            for (char c : test) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
