import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {
    static class Edge {
        public final int v1;
        public final int v2;
        public final double length;

        public Edge(int v1, int v2, int[] x, int[] y) {
            this.v1 = v1;
            this.v2 = v2;

            int deltaX = x[v1] - x[v2];
            int deltaY = y[v1] - y[v2];
            this.length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }
    }

    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.length > o2.length) {
                return 1;
            } else if (o1.length < o2.length) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here

        int numberOfVertices = x.length;

        // make a set of vertices.
        // the set will be initialized with zeroes.
        // if a vertex belongs to a set 0, it means
        // it belongs to a separate set.
        // if a vertex belongs to a set n, it means
        // it belongs to a resulting MST.
        int[] set = new int[numberOfVertices];

        // the index of a next set
        int newSetIndex = 1;

        // make a queue of edges.
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = i + 1; j < numberOfVertices; j++) {
                Edge e = new Edge(i, j, x, y);
                pq.add(e);
            }
        }

        while (!pq.isEmpty()) {
            // get the next shortest edge
            Edge e = pq.poll();

            // if at least one of the vertices
            // is not on an MST, then add the length
            // of the found edge to the result,
            // and mark both vertices as belonging to the MST

            if (set[e.v1] == 0 && set[e.v2] == 0) {
                // this is the case when both vertices are not
                // on an MST; adding them to a new subtree
                result += e.length;

                set[e.v1] = set[e.v2] = newSetIndex++;
            } else if (set[e.v1] * set[e.v2] == 0) {
                // this is the case when one of the vertices
                // is not on an MST; adding it to an existing subtree
                result += e.length;

                set[e.v1] = set[e.v2] = set[e.v1] + set[e.v2];
            } else if (set[e.v1] != set[e.v2]) {
                // this is the case when both vertices are on an MST,
                // but belong to the different subtrees;
                // joining both subtrees into a subtree with the set index
                // being the smallest of the two indexes
                result += e.length;

                int indexV1 = set[e.v1];
                int indexV2 = set[e.v2];
                int setIndex = indexV1 > indexV2 ? indexV2 : indexV1;

                for (int i = 0; i < numberOfVertices; i++) {
                    if (set[i] == indexV1 || set[i] == indexV2) {
                        set[i] = setIndex;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}