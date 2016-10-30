

import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
        int[] v = new int[adj.length];
        explore(adj, v, x);

        return v[x] * v[y];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}


