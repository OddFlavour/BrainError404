package leetCode.juneLeetCodingChallenge.week4;

import java.util.*;

public class ReconstructItinerary {
    final String START = "JFK";

    int flightsCount;

    class Flight implements Comparable<Flight> {
        String from, to;
        boolean used;

        public Flight(String from, String to) {
            this.from = from;
            this.to = to;
            this.used = false;
        }

        @Override
        public int compareTo(Flight that) {
            return this.to.compareTo(that.to);
        }
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> ans = new Stack<>();

        flightsCount = tickets.size() + 1;

        // Construct adjacency list (the flight list will be in order)
        Map<String, List<Flight>> flights = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!flights.containsKey(from)) flights.put(from, new ArrayList<>());

            flights.get(from).add(new Flight(from, to));
        }

        for (String key : flights.keySet()) {
            Collections.sort(flights.get(key));
        }

        // DFS
        dfs(flights, (Stack<String>) ans, START);

        return ans;
    }

    private boolean dfs(Map<String, List<Flight>> flights, Stack<String> ans, String curr) {
        ans.push(curr);

        if (flights.containsKey(curr)) {
            for (Flight f : flights.get(curr)) {
                if (!f.used) {
                    f.used = true;

                    if (dfs(flights, ans, f.to)) {
                        return true;
                    }

                    f.used = false;
                }
            }
        }

        if (ans.size() == flightsCount) {
            return true;
        } else {
            ans.pop();
            return false;
        }
    }

    public static void main(String[] args) {
        String[][][] tests = {
                {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}},
                {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}},
                {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}}
        };

        for (int i = 0; i < tests.length; i++) {
            List<List<String>> test = new ArrayList<>();
            for (int j = 0; j < tests[i].length; j++) {
                test.add(Arrays.asList(tests[i][j]));
            }

            ReconstructItinerary s = new ReconstructItinerary();
            System.out.println(s.findItinerary(test));
        }
    }
}
