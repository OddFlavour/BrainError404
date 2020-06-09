package codeGoogle.year2017.qualification.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// https://code.google.com/codejam/contest/3264486/dashboard#s=p0&a=1
public class A {

    public static void main(String[] args) throws Exception {
        String file = "src/year2017/qualification/a/" + "A-small-practice.in";
        BufferedReader br = new BufferedReader(new FileReader(new File(file)));

        int T = Integer.parseInt(br.readLine());

        for (int C = 0; C < T; C++) {
            String[] tokens = br.readLine().split(" ");

            String S = bin(tokens[0]);
            int K = Integer.parseInt(tokens[1]);

            int count = 0;
            for (int i = 0; i < S.length() - (K - 1); i++) {
                if (S.charAt(i) == '0') {
                    S = flipCakes(i, S, K);
                    count++;
                }
            }

            System.out.printf("Case #%d: ", C + 1);
            if (S.contains("0"))
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(count);
        }
    }

    private static String bin(String str) {
        String ret = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+') {
                ret += "1";
            } else {
                ret += "0";
            }
        }

        return ret;
    }

    private static String flipCakes(int index, String str, int K) {
        String ret = str.substring(0, index);
        for (int i = index; i < index + K; i++) {
            int temp = (int) str.charAt(i);
            ret += String.valueOf((temp + 1) % 2);
        }
        ret += str.substring(index + K);

        return ret;
    }
}
