import java.util.*;

public class Dijkstra {

    final static long INF = Integer.MAX_VALUE;

    static class FlightLeg {
        public FlightLeg(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }

        public int id;
        public long cost;
    }

    static class FlightLegComparator implements Comparator<FlightLeg> {
        @Override
        public int compare(FlightLeg o1, FlightLeg o2) {
            if (o1.cost > o2.cost) {
                return 1;
            } else if (o1.cost < o2.cost) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        // initialization
        long[] dist = new long[adj.length];
        PriorityQueue<FlightLeg> pq = new PriorityQueue<>(adj.length, new FlightLegComparator());

        for (int i = 0; i < adj.length; i++) {
            long distance = i == s ? 0 : INF;

            dist[i] = distance;
            pq.add(new FlightLeg(i, i == s ? 0 : INF));
        }

        while (!pq.isEmpty()) {
            // removing the nearest vertex
            FlightLeg fl = pq.poll();
            int u = fl.id;

            for (Integer v : adj[u]) {
                // here we assume the vertex indexes in the elements of adj and cost are in sync
                int v_index = adj[u].indexOf(v);

                if (dist[v] > dist[u] + cost[u].get(v_index)) {
                    dist[v] = dist[u] + cost[u].get(v_index);
                    pq.add(new FlightLeg(v, dist[v]));
                }
            }
        }

        return dist[t] == INF ? -1 : (int)dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}