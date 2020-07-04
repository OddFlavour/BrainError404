package leetCode.juneLeetCodingChallenge.week2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// Read on Bellman-Ford algorithm
public class CheapestFlightsWithinKStops {
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
            for (int[] f : flights) {
                if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
                prices.get(f[0]).put(f[1], f[2]);
            }
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
            pq.add(new int[] {0, src, K + 1});
            while (!pq.isEmpty()) {
                int[] top = pq.remove();
                int price = top[0];
                int city = top[1];
                int stops = top[2];
                if (city == dst) return price;
                if (stops > 0) {
                    Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                    for (int a : adj.keySet()) {
                        pq.add(new int[] {price + adj.get(a), a, stops - 1});
                    }
                }
            }
            return -1;
        }
    }

    int ans = -1;
    int src, dst, K;

    final int _COST = 0;
    final int _K = 1;
    final int _PREV = 2;

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        this.src = src;
        this.dst = dst;
        this.K = K;

        rec(flights, src, 0, 0, new boolean[n]);

        return ans;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // [cost, k-count, prev]
        int[][] dijkstra = new int[n][3];
        boolean[] finished = new boolean[n];

        for (int i = 0; i < n; i++) {
            // dijkstra[i] = new int[]{-1, 0, -1}; will create a new array
            dijkstra[i][_COST] = -1;
            dijkstra[i][_K] = 0;
            dijkstra[i][_PREV] = -1;
        }
        dijkstra[src][_COST] = 0;

        while (true) {
            // Find the best node to process (based on cost)
            int curr = -1;
            for (int i = 0; i < n; i++) {
                if (!finished[i]) {
                    if (dijkstra[i][_COST] != -1) {
                        if (curr == -1 || dijkstra[curr][_COST] > dijkstra[i][_COST]) {
                            curr = i;
                        }
                    }
                }
            }

            // Verify node
            if (curr == -1) return -1;
            if (curr == dst) return dijkstra[curr][_COST];

            if (dijkstra[curr][_K] > K) {
                /*
                We must back track, since we've exceeded 'K'
                We back track by looking at other paths that can lead to the current position, i.e looking
                    at paths of the form [X, curr, C].
                We would prioritize these by the costs to get to X plus C (since we still want min-path), but
                    at the same time we must insure count to get to X is less than whatever count it took to get
                    to curr before
                In the case that there are no other paths, we set the current node as impossible to reach, to avoid
                    it getting processed later
                But how to avoid taking the same path again later? Can we just remove this path? Yes, because
                    that path would be used strictly to get from X to C. By then, we've already processed shortest
                    path to X, therefore the path X to C is useless now
                 */

                int prev;
                do {
                    // If at any point we reach 'src', then just move on
                    if (curr == src) break;

                    prev = curr;

                    // Set current position to be unreachable
                    dijkstra[curr][_COST] = -1;

                    for (int[] fl : flights) {
                        if (fl[1] == curr
                                && fl[0] != dijkstra[curr][_PREV]
                                && dijkstra[fl[0]][_K] < K) {
                            int newCost2 = dijkstra[fl[0]][_COST] + fl[2];
                            if (dijkstra[curr][_COST] == -1 || dijkstra[curr][_COST] > newCost2) {
                                if (dijkstra[curr][_COST] == -1) {
                                    // ONLY DESTROY AT THE NODE OF PROBLEM
                                    // First destroy the bad path which is [X, curr, C]
                                    for (int[] temp : flights) {
                                        if (temp[0] == dijkstra[curr][_PREV] && temp[1] == curr) {
                                            temp[0] = temp[1] = -1;
                                            break;
                                        }
                                    }
                                }
                                dijkstra[curr][_COST] = newCost2;
                                dijkstra[curr][_K] = dijkstra[fl[0]][_K] + 1;
                                dijkstra[curr][_PREV] = fl[0];
                            }
                        }
                    }

                    finished[curr] = false;
                    curr = dijkstra[curr][_PREV];
                } while (dijkstra[prev][_COST] == -1);
            } else {
                // Process paths out
                for (int[] f : flights) {
                    if (f[0] == curr) {
                        int next = f[1];

                        // Process each e
                        int newCost = dijkstra[curr][_COST] + f[2];
                        if (dijkstra[next][_COST] == -1 || dijkstra[next][_COST] > newCost) {
                            dijkstra[next][_COST] = newCost;
                            dijkstra[next][_K] = dijkstra[curr][_K] + 1;
                            dijkstra[next][_PREV] = curr;
                        }
                    }
                }

                finished[curr] = true;
            }
        }
    }

    private boolean rec(int[][] flights, int curr, int totalCost, int count, boolean[] seen) {
        if (curr == dst) {
            if (count <= K + 1) {
                if (ans == -1) {
                    ans = totalCost;
                } else {
                    ans = Math.min(ans, totalCost);
                }
            }
//            System.out.printf("%d, %d\n", count, totalCost);
            return true;
        }

        seen[curr] = true;

        boolean possible = false;
        for (int[] f : flights) {
            // If it is a flight out of 'curr'
            if (f[0] == curr && !seen[f[1]]) {
                // And taking this flight has a chance to get to 'dst'
                if (rec(flights, f[1], totalCost + f[2], count + 1, seen)) {
                    possible = true;
                }
            }
        }
        // If it is not possible, then we'll mark it as seen permanently
//        seen[curr] = !possible;
        seen[curr] = false;

        return possible;
    }

    public static void main(String[] args) {
        int[] testNs = {
                3,
                3,
                4,
                5,
                5,
                4,
                17,
                10,
                5,
                5,
                4
        };

        int[][][] testEdges = {
                {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}},
                {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}},
                {{0, 1, 100}, {1, 2, 100}, {0, 3, 500}, {3, 2, 100}},
                {{4, 1, 1}, {1, 2, 3}, {0, 3, 2}, {0, 4, 10}, {3, 1, 1}, {1, 4, 3}},
                {{1, 2, 10}, {2, 0, 7}, {1, 3, 8}, {4, 0, 10}, {3, 4, 2}, {4, 2, 10}, {0, 3, 3}, {3, 1, 6}, {2, 4, 5}},
                {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}},
                {{0, 12, 28}, {5, 6, 39}, {8, 6, 59}, {13, 15, 7}, {13, 12, 38}, {10, 12, 35}, {15, 3, 23}, {7, 11, 26}, {9, 4, 65}, {10, 2, 38}, {4, 7, 7}, {14, 15, 31}, {2, 12, 44}, {8, 10, 34}, {13, 6, 29}, {5, 14, 89}, {11, 16, 13}, {7, 3, 46}, {10, 15, 19}, {12, 4, 58}, {13, 16, 11}, {16, 4, 76}, {2, 0, 12}, {15, 0, 22}, {16, 12, 13}, {7, 1, 29}, {7, 14, 100}, {16, 1, 14}, {9, 6, 74}, {11, 1, 73}, {2, 11, 60}, {10, 11, 85}, {2, 5, 49}, {3, 4, 17}, {4, 9, 77}, {16, 3, 47}, {15, 6, 78}, {14, 1, 90}, {10, 5, 95}, {1, 11, 30}, {11, 0, 37}, {10, 4, 86}, {0, 8, 57}, {6, 14, 68}, {16, 8, 3}, {13, 0, 65}, {2, 13, 6}, {5, 13, 5}, {8, 11, 31}, {6, 10, 20}, {6, 2, 33}, {9, 1, 3}, {14, 9, 58}, {12, 3, 19}, {11, 2, 74}, {12, 14, 48}, {16, 11, 100}, {3, 12, 38}, {12, 13, 77}, {10, 9, 99}, {15, 13, 98}, {15, 12, 71}, {1, 4, 28}, {7, 0, 83}, {3, 5, 100}, {8, 9, 14}, {15, 11, 57}, {3, 6, 65}, {1, 3, 45}, {14, 7, 74}, {2, 10, 39}, {4, 8, 73}, {13, 5, 77}, {10, 0, 43}, {12, 9, 92}, {8, 2, 26}, {1, 7, 7}, {9, 12, 10}, {13, 11, 64}, {8, 13, 80}, {6, 12, 74}, {9, 7, 35}, {0, 15, 48}, {3, 7, 87}, {16, 9, 42}, {5, 16, 64}, {4, 5, 65}, {15, 14, 70}, {12, 0, 13}, {16, 14, 52}, {3, 10, 80}, {14, 11, 85}, {15, 2, 77}, {4, 11, 19}, {2, 7, 49}, {10, 7, 78}, {14, 6, 84}, {13, 7, 50}, {11, 6, 75}, {5, 10, 46}, {13, 8, 43}, {9, 10, 49}, {7, 12, 64}, {0, 10, 76}, {5, 9, 77}, {8, 3, 28}, {11, 9, 28}, {12, 16, 87}, {12, 6, 24}, {9, 15, 94}, {5, 7, 77}, {4, 10, 18}, {7, 2, 11}, {9, 5, 41}},
                {{3, 4, 4}, {2, 5, 6}, {4, 7, 10}, {9, 6, 5}, {7, 4, 4}, {6, 2, 10}, {6, 8, 6}, {7, 9, 4}, {1, 5, 4}, {1, 0, 4}, {9, 7, 3}, {7, 0, 5}, {6, 5, 8}, {1, 7, 6}, {4, 0, 9}, {5, 9, 1}, {8, 7, 3}, {1, 2, 6}, {4, 1, 5}, {5, 2, 4}, {1, 9, 1}, {7, 8, 10}, {0, 4, 2}, {7, 2, 8}},
                {{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}},
                {{0, 1, 1}, {0, 2, 10}, {1, 2, 1}, {2, 3, 1}, {3, 4, 1}},
                {{1, 0, 1}, {2, 0, 5}, {2, 1, 1}, {3, 2, 1}}
        };

        int[] testSrcs = {
                0,
                0,
                0,
                2,
                0,
                0,
                13,
                6,
                0,
                0,
                3
        };

        int[] testDsts = {
                2,
                2,
                2,
                1,
                4,
                3,
                4,
                0,
                2,
                4,
                0
        };

        int[] testKs = {
                1,
                0,
                0,
                1,
                1,
                1,
                13,
                7,
                2,
                2,
                1
        };

        for (int i = 0; i < testNs.length; i++) {
            CheapestFlightsWithinKStops s = new CheapestFlightsWithinKStops();
            System.out.println(
                    s.findCheapestPrice(testNs[i], testEdges[i], testSrcs[i], testDsts[i], testKs[i])
                            == s.findCheapestPrice2(testNs[i], testEdges[i], testSrcs[i], testDsts[i], testKs[i])
            );
        }
    }
}
