package leetCode.challenge30Day.week1;

import java.util.HashMap;
import java.util.Map;

public class HappyNumber {
    Map<Integer, Integer> hashmap = new HashMap<>();

    public boolean isHappy(int n) {
        return runHappyCheck(n);
    }

    public boolean runHappyCheck(int n) {
        if (n == 1) return true;

        int temp = getSumOfSquaredDigits(n);
        if (hashmap.get(temp) != null) {
            return false;
        } else {
            hashmap.put(temp, 1);
        }
        return runHappyCheck(temp);
    }

    public int getSumOfSquaredDigits(int n) {
        int sum = 0;

        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        for (int i = 0; i <= 10000; i++) {
            HappyNumber s = new HappyNumber();
            System.out.println(i + ": " + s.isHappy(i));
        }

//        System.out.println(s.isHappy(1000));
    }
}
