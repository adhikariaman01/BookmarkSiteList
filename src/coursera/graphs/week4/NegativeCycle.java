import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    final static long INF = Integer.MAX_VALUE;

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here

        // initialization
        long[] dist = new long[adj.length];
        for (int i = 1; i < adj.length; i++) {
            dist[i] = INF;
        }
        dist[0] = 0;

        // repeat adj.length-1 times
        for (int i = 0; i < adj.length - 1; i++) {
            // iterate through every vertex
            for (int u = 0; u < adj.length; u++) {
                ArrayList<Integer> u_adj = adj[u];
                // relax every edge
                for (Integer v : u_adj) {
                    // here we assume the vertex indexes in the elements of adj and cost are in sync
                    int v_index = adj[u].indexOf(v);

                    relax(u, v, v_index, dist, cost);
                }
            }
        }

        // do one more iteration through every vertex
        for (int u = 0; u < adj.length; u++) {
            ArrayList<Integer> u_adj = adj[u];
            // check if any edge can be relaxed
            for (Integer v : u_adj) {
                // here we assume the vertex indexes in the elements of adj and cost are in sync
                int v_index = adj[u].indexOf(v);

                if (dist[v] > dist[u] + cost[u].get(v_index)) {
                    // if any edge can be relaxed,
                    // it means there is a negative cycle in a graph
                    return 1;
                }
            }
        }

        return 0;
    }

    private static void relax(int u, Integer v, int v_index, long[] dist, ArrayList<Integer>[] cost) {
        if (dist[v] > dist[u] + cost[u].get(v_index)) {
            dist[v] = dist[u] + cost[u].get(v_index);
        }
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
        System.out.println(negativeCycle(adj, cost));
    }
}