package leetCode.challenge30Day.week1;

import java.util.HashMap;
import java.util.Map;

public class CountingElements {
    public int countElements(int[] arr) {
        Map<Integer, Integer> hashmap = new HashMap<>();

        int length = arr.length;
        for (int i = 0 ; i < length; i++) {
            Integer count = hashmap.get(arr[i]);
            if (count == null) {
                count = 0;
            }
            hashmap.put(arr[i], count + 1);
        }

        int ans = 0;

        for (Integer key : hashmap.keySet()) {
            // countA cannot be null
            Integer countA = hashmap.get(key);
            Integer countB = hashmap.get(key + 1);

            if (countB != null) {
                ans += countA;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] t1 = { 1,2,3 };
        int[] t2 = { 1,1,3,3,5,5,7,7 };
        int[] t3 = { 1,3,2,3,5,0 };
        int[] t4 = { 1,1,2,2 };
        int[] t5 = { };
        int[] t6 = { 1,2,3,4,5,6 };
        int[] t7 = { 1, 1, 2 };

        CountingElements s = new CountingElements();
        System.out.println(s.countElements(t1) + "2");
        System.out.println(s.countElements(t2) + "0");
        System.out.println(s.countElements(t3) + "3");
        System.out.println(s.countElements(t4) + "2");
        System.out.println(s.countElements(t5) + "0");
        System.out.println(s.countElements(t6) + "5");
        System.out.println(s.countElements(t7) + "2");
    }
}
