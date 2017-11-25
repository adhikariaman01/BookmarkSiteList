import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] adj_r) {
        //write your code here

        // length of the adj array
        int n = adj.length;

        // vertices visited during dfs()
        int v[] = new int[n];
        // vertices visited during explore()
        int used[] = new int[n];
        // postorder numbers go here
        ArrayList<Integer> order = new ArrayList<>(n);

        // while we have unexplored vertices on the original graph...
        for (int i = 0; i < n; i++) {
            if (used[i] == 0) {
                // ... continue doing DFS
                dfs(adj, used, order, i);
            }
        }

        int counter = 0;
        // now we have the order where the vertex
        // with the largest postorder number sits
        // at the top of the ArrayList.
        // let's begin exploring the reversed graph
        for (int i : order) {
            if (v[i] == 0) {
                counter++;
                // ... continue doing DFS
                explore(adj_r, v, i);
                
            }
        }

        return counter;
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

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
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

        // when all adjacent vertices are explored,
        // insert the current vertex to the top of the output,
        // so we can begin doing the second exploration
        // from the vertex at position 0,
        // which was completed last,
        // and thus has the greatest postorder number
        order.add(0, s);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adj_r = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        // building a reversed graph along with the original one
        for (int i = 0; i < n; i++) {
            adj_r[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            // building a reversed graph along with the original one
            adj_r[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, adj_r));
    }
}
