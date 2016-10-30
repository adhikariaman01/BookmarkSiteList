import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here

        // length of the adj array
        int n = adj.length;

        // while we have unexplored vertices...
        for (int i = 0; i < n; i++) {
            if (used[i] == 0) {
                // ... continue doing DFS
                dfs(adj, used, order, i);
            }
        }

        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        //write your code here

        // mark the current vertex as visited
        used[s] = 1;

        // get the list of edges connected to the current vertex
        ArrayList<Integer> edges = adj[s];

        for (int e : edges) {
            if (used[e] != 1) {
                // if the current vertex is NOT visited,
                // begin exploring
                dfs(adj, used, order, e);
            }
        }

        // when all adjacent vertices are visited,
        // insert the current vertex to the top of the output
        order.add(0, s);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}