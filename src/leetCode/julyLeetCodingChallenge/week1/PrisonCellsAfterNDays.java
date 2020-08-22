package leetCode.julyLeetCodingChallenge.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        List<String> memory = new ArrayList<>();
        Map<String, Integer> memoryMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String cellsToStr = toString(cells);

            if (memoryMap.containsKey(cellsToStr)) {
                int last = memoryMap.get(cellsToStr);
                int diff = i - last;
                int modulo = (N - i) % diff;

                return toArray(memory.get(modulo + last));
            } else {
                memory.add(cellsToStr);
                memoryMap.put(cellsToStr, i);
            }

            // Go through the day
            int[] newCells = new int[cells.length];

            for (int j = 1; j < cells.length - 1; j++) {
                if ((cells[j - 1] == 1 && cells[j + 1] == 1)
                        || (cells[j - 1] == 0 && cells[j + 1] == 0)
                ) {
                    newCells[j] = 1;
                }
            }

            cells = newCells;
        }

        return cells;
    }

    private String toString(int[] cells) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cells.length; i++) {
            sb.append(cells[i]);
        }

        return sb.toString();
    }

    private int[] toArray(String cellsToStr) {
        int[] ret = new int[cellsToStr.length()];

        for (int i = 0; i < cellsToStr.length(); i++) {
            ret[i] = cellsToStr.charAt(i) - '0';
        }

        return ret;
    }

    public static void main(String[] args) {
        int[][] testCells = {
                {0, 1, 0, 1, 1, 0, 0, 1},
                {1,0,0,1,0,0,1,0}
        };

        int[] testNs = {
                7,
                1000000000
        };

        for (int i = 0; i < testCells.length; i++) {
            PrisonCellsAfterNDays s = new PrisonCellsAfterNDays();
            int[] result = s.prisonAfterNDays(testCells[i], testNs[i]);

            for (int j = 0; j < result.length; j++) {
                System.out.printf("%d ", result[j]);
            }
            System.out.println();
        }
    }
}
