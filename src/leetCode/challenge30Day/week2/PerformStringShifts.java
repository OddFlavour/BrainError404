package leetCode.challenge30Day.week2;

public class PerformStringShifts {
    public String stringShift(String s, int[][] shift) {
        int finalShift = 0;
        for (int i = 0; i < shift.length; i++) {
            int[] currShift = shift[i];
            if (currShift[0] == 0) {
                finalShift -= currShift[1];
            } else {
                finalShift += currShift[1];
            }
        }

        boolean shiftRight = finalShift > 0;
        finalShift = Math.abs(finalShift) % s.length();

        String part1 = "", part2 = "";
        if (shiftRight) {
            part1 = s.substring(0, s.length() - finalShift);
            part2 = s.substring(s.length() - finalShift);
        } else {
            part1 = s.substring(0, finalShift);
            part2 = s.substring(finalShift);
        }

        return part2 + part1;
    }

    public static void main(String[] args) {
        String t1Str = "abcdefg";
        int[][] t1 = {{1,1}, {1,1}, {0,2}, {1,3}};

        String t2Str=  "abc";
        int[][] t2 = {{0,1}, {1,2}};

        PerformStringShifts s = new PerformStringShifts();
        System.out.println(s.stringShift(t1Str, t1));
        System.out.println(s.stringShift(t2Str, t2));
    }
}
