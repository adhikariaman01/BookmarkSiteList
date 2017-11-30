import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class Bipartite {

    private static int bipartite(ArrayList<Integer>[] adj) {
        int[] dist = new int[adj.length];
        boolean[] color = new boolean[adj.length];
        for (int i = 0; i < adj.length; i++) dist[i] = Integer.MAX_VALUE;
        dist[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (! queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                if (dist[v] == Integer.MAX_VALUE) {
                    queue.offer(v);
                    dist[v] = dist[u] + 1;
                    color[v] = ! color[u];
                }
                // V is visited and same color w/ u, no bipartite.
                else if (color[v] == color[u]) return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
        scanner.close();
    }
}
