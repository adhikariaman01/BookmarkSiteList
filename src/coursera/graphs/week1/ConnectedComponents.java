

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        //write your code here
        return dfs(adj);
    }

    private static int dfs(ArrayList<Integer>[] adj) {
        // initialize component counter
        int cc = 0;

        // initialize array of 'visited' flags
        int[] v = new int[adj.length];

        // for each vertex in a graph...
        for (int i = 0; i < adj.length; i++) {
            // ... explore if it wasn't explored
            if (v[i] != 1) {
                explore(adj, v, i);

                // increment component counter,
                // because we just finished exploring
                // another connected component
                cc++;
            }
        }

        return cc;
    }

    private static void explore(ArrayList<Integer>[] adj, int[] v, int x) {
        // mark the current vertex as visited
        v[x] = 1;

        // get the list of edges connected to the current vertex
        ArrayList<Integer> edges = adj[x];

        // for every edge connected to the current vertex
        for (int e : edges) {
            // begin exploring starting from the next vertex
            if (v[e] != 1) {
                explore(adj, v, e);
            }
        }
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}