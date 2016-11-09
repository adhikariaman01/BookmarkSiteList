import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve(new InputStreamReader(System.in), System.out);
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    class Thread {

        private int id;
        public long nextFreeTime;

        public Thread(int id, long nextFreeTime) {
            this.id = id;
            this.nextFreeTime = nextFreeTime;
        }

    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        Thread[] workers = new Thread[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            workers[i] = new Thread(i, 0);
        }

        int size = workers.length;

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            assignedWorker[i] = workers[0].id;
            startTime[i] = workers[0].nextFreeTime;
            workers[0].nextFreeTime += duration;
            siftDown(workers, size, 0);
        }
    }

    private void siftDown(Thread[] workers, int size, int i) {
        int maxIndex = i;
        int left = leftChild(i);
        if (left < size && workers[left].nextFreeTime <= workers[maxIndex].nextFreeTime) {
            if (workers[left].nextFreeTime < workers[maxIndex].nextFreeTime) {
                maxIndex = left;
            } else if (workers[left].nextFreeTime == workers[maxIndex].nextFreeTime && workers[left].id < workers[maxIndex].id) {
                maxIndex = left;
            }
        }
        int right = rightChild(i);
        if (right < size && workers[right].nextFreeTime <= workers[maxIndex].nextFreeTime) {
            if (workers[right].nextFreeTime < workers[maxIndex].nextFreeTime) {
                maxIndex = right;
            } else if (workers[right].nextFreeTime == workers[maxIndex].nextFreeTime && workers[right].id < workers[maxIndex].id) {
                maxIndex = right;
            }
        }
        if (i != maxIndex) {
            Thread tmp = workers[i];
            workers[i] = workers[maxIndex];
            workers[maxIndex] = tmp;
            siftDown(workers, size, maxIndex);
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
        assignJobs();
        writeResponse();
        out.close();
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