package leetCode.mayLeetCodingChallenge.week2;

public class CheckIfItIsAStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int[] riseRun = {
                coordinates[0][0] - coordinates[1][0],
                coordinates[0][1] - coordinates[1][1]
        };

        for (int i = 1; i < coordinates.length; i++) {
            int[] riseRunCurr = {
                    coordinates[0][0] - coordinates[i][0],
                    coordinates[0][1] - coordinates[i][1]
            };

            double a = (double) riseRun[0] * riseRunCurr[1];
            double b = (double) riseRun[1] * riseRunCurr[0];

            if (a != b) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][][] tests = {
                {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {6, 7}, {3, 4}}, // true
                {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}}, // false
                {{1, 1}, {2, 2}}, // true
                {{1, 1}, {2, 2}, {-2, -2}}, //true
                {{1, 1}, {0, 2}, {2, 0}, {2, 2}}, // false
                {{0,0}, {0, 1}, {0, -1}}, // true
                {{0,0}, {1,0}, {-1,0}}, // true
                {{2,3}, {1,2}, {4,5}}, // true
                {{1,-8},{2,-3},{1,2}}, // false
                {{1,1},{2,2},{2,1},{3,2}}, // false
                {{0,0},{0,5},{5,0},{1337,0},{0,1337}}, // false
                {{2,-3},{-8,-3},{-8,6},{-4,8},{1,5},{4,-7},{1,7},{8,4}}
        };
        boolean[] answers = {
                true, false, true, true, false, true, true, true, false, false, false, false
        };

        for (int i = 0; i < tests.length; i++) {
            CheckIfItIsAStraightLine s = new CheckIfItIsAStraightLine();
            System.out.println(s.checkStraightLine(tests[i]) == answers[i]);
        }
    }
}
