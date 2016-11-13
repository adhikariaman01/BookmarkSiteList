import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
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

    private static double clustering(int[] x, int[] y, int k) {
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

        // each satisfied if() condition inside while() loop
        // decreases the number of clusters by one,
        // so we're going to make only so many loops before
        // we return with the result
        int iteration = numberOfVertices - k;

        double result = 0.;
        while (iteration >= 0) {
            // get the next shortest edge
            Edge e = pq.poll();

            if (set[e.v1] == 0 && set[e.v2] == 0) {
                // uniting two not yet visited vertices into a cluster
                iteration--;
                result = e.length;

                set[e.v1] = set[e.v2] = newSetIndex++;
            } else if (set[e.v1] * set[e.v2] == 0) {
                // adding a not yet visited vortex to a cluster
                iteration--;
                result = e.length;

                set[e.v1] = set[e.v2] = set[e.v1] + set[e.v2];
            } else if (set[e.v1] != set[e.v2]) {
                // uniting two clusters
                iteration--;
                result = e.length;

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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}