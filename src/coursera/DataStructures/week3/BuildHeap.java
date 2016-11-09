import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve(new InputStreamReader(System.in), System.out);
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        int size = data.length;
        for (int i = (int) Math.floor(size / 2); i >= 0; i--) {
            siftDown(size, i);
        }
    }

    private void siftDown(int size, int i) {
        int maxIndex = i;
        int left = leftChild(i);
        if (left < size && data[left] < data[maxIndex]) {
            maxIndex = left;
        }
        int right = rightChild(i);
        if (right < size && data[right] < data[maxIndex]) {
            maxIndex = right;
        }
        if (i != maxIndex) {
            swaps.add(new Swap(i, maxIndex));
            int tmp = data[i];
            data[i] = data[maxIndex];
            data[maxIndex] = tmp;
            siftDown(size, maxIndex);
        }
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return (2 * i) + 2;
    }

    public void solve(InputStreamReader input, PrintStream output) throws IOException {
        in = new FastScanner(input);
        out = new PrintWriter(new BufferedOutputStream(output));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner(InputStreamReader input) {
            reader = new BufferedReader(input);
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}