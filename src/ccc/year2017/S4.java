package ccc.year2017;

import ccc.tools.FolderReader;
import ccc.tools.InReader;
import ccc.tools.OutWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S4 {
    static final String source = "src/ccc/year2017/testData/senior_data" + "/s4";

    class DisjointSet {
        int parent;
        int rank;

        public DisjointSet(int self) {
            this.parent = self;
            this.rank = 0;
        }
    }

    class Pipe implements Comparable<Pipe> {
        int end1, end2;
        int cost;
        int original;

        public Pipe(int end1, int end2, int cost, boolean original) {
            this.end1 = end1;
            this.end2 = end2;
            this.cost = cost;
            this.original = original ? 1 : 0;
        }

        @Override
        public int compareTo(Pipe that) {
            if (this.cost != that.cost) return this.cost - that.cost;
            else {
                /*
                THIS IS VERY IMPORTANT, THIS IS SO WE EVALUATE ORIGINAL PIPES FIRST, SINCE IT'LL MINIMIZE AMOUNT OF DAYS USED

                Negating the result, because of order logic
                 */
                return -(this.original - that.original);
            }
        }
    }

    private int find(DisjointSet[] sets, int i) {
        if (sets[i].parent != i) {
            sets[i].parent = find(sets, sets[i].parent);
        }

        return sets[i].parent;
    }

    private void union(DisjointSet[] sets, int x, int y) {
        int a = find(sets, x);
        int b = find(sets, y);

        if (sets[a].rank > sets[b].rank) {
            sets[b].parent = a;
        } else if (sets[a].rank < sets[b].rank) {
            sets[a].parent = b;
        } else {
            sets[b].parent = a;
            sets[a].rank++;
        }
    }

    public void solution(File f) throws IOException {
//        InReader in = new InReader();
        InReader in = new InReader(f.getAbsolutePath());
        OutWriter out = new OutWriter(source, f);

        int[] line1 = in.readListOfInt();
        int N = line1[0], M = line1[1], D = line1[2];

        List<Pipe> pipes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int[] lineX = in.readListOfInt();

            pipes.add(new Pipe(lineX[0], lineX[1], lineX[2], i < N - 1));
        }

        // Create MST through Kruskal's Algorithm
        // TODO: better to use a priority queue, to cap worst case to O(nlogn)
        Collections.sort(pipes);

        DisjointSet[] sets = new DisjointSet[N + 1];
        for (int i = 0; i <= N; i++) {
            sets[i] = new DisjointSet(i);
        }

        int ans = 0;
        int ind = 0;
        int count = 0;
        while (count < N - 1) {
            Pipe p = pipes.get(ind++);

            if (find(sets, p.end1) != find(sets, p.end2)) {
                count++;

                // There's no difference in speed here, because with path compression, the find operations in union will be instant
                // Writing it like this is more intuitive, because we're working with 'p.end1' and 'p.end2' currently, so merge their sets.
                union(sets, p.end1, p.end2);

                if (p.original == 0) {
                    ans++;
                }
            }
        }

        // How to apply 'D'? The MST we formed is already the best, we would just apply 'D' onto the most costly path
        // But another constraint is to finish as fast as possible, so we could perhaps apply 'D' on an unused pre-constructed path
        // Making that path now usable, and therefore reduce the day count by 1

        // Intuition, try with every unused original edge with enhancer. Noting that adding one edge will require us to remove an edge.
        // The edge we remove will intuitively be the most costly one (and must have cost equal/larger than this original edge we're using).
        //      Why must this edge be equal/larger? Because if we removed a edge that costed less, then we won't be utilizing the enhancer
        //      to its max potential, and therefore not meeting the first requirement: min cost.
        // If that edge happens to be an unoriginal edge, then we can reduce the day by 1

        // By the above reasoning, if we sort the edges primarily by cost, and secondarily by original/not original
        // Then we can conclude that the only edge we'll be replacing will be the last edge added to the MST
        // Why is this? By prioritizing based on original/not original, when choosing between the edges that have the same
        // cost, we will always use the edge that's original to reduce the day count, if we couldn't use that original edge,
        // then that means the endpoints are already in the same disjoint set. Therefore, that original edge will never
        // be used (since after enhancing, we'd need to replace a smaller edge that's in the MST)

        // Reconstruct the MST, because we'll need to undo the last addition
        sets = new DisjointSet[N + 1];
        for (int i = 0; i <= N; i++) {
            sets[i] = new DisjointSet(i);
        }

        // Make sure the last edge added is not an original, if it was, then the current 'ans' is the best we can do
        if (pipes.get(ind - 1).original == 0) {
            for (Pipe p : pipes) {
                if (find(sets, p.end1) != find(sets, p.end2)) {
                    // Omitting the last edge added
                    if (count > 1) {
                        union(sets, p.end1, p.end2);
                        count--;
                    } else if (p.original == 1 && p.cost <= D) {
                        ans--;
                        break;
                    }
                }
            }
        }

//        System.out.println(ans);
        out.write(ans + "\n");
        out.close();
    }

    public static void main(String[] args) throws IOException {
        for (File f : FolderReader.getFilesInDir(source)) {
            if (f.getName().endsWith(".in")) {
                S4 s = new S4();
                s.solution(f);
                System.out.println("Done");
            }
        }

//        S4 s = new S4();
//        s.solution(null);
    }
}
