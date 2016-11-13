import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static long prime = 1000000007;
    private static long multiplier = 263;
    private static long hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;

        return hash;
    }

    private static List<Integer> getOccurrences(Data input) {
        List<Integer> occurrences = new ArrayList<>();

        // compute the hash of the pattern
        long pHash = hashFunc(input.pattern);

        // precompute hashes of all the possible substrings
        // of length input.pattern.length()
        long[] H = precomputeHashes(input);

        // compare the hashes
        int textLength = input.text.length();
        int patternLength = input.pattern.length();
        for (int i = 0; i <= textLength - patternLength; i++) {
            if (pHash != H[i]) {
                continue;
            }
            if (areEqual(input.text.substring(i, i + patternLength), input.pattern)) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }

    private static boolean areEqual(String substring, String pattern) {
        int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++) {
            if (substring.charAt(i) != pattern.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static long[] precomputeHashes(Data input) {
        int textLength = input.text.length();
        int patternLength = input.pattern.length();

        int hashLength = textLength - (patternLength - 1);
        long[] H = new long[hashLength];

        String s = input.text.substring(textLength - patternLength);
        H[textLength - patternLength] = hashFunc(s);

        long y = 1;
        for (int i = 1; i <= patternLength; i++) {
            y = (y * multiplier) % prime;
        }

        for (int i = textLength - patternLength - 1; i >=0; i--) {
            long preHash = multiplier * H[i + 1] + input.text.charAt(i) - y * input.text.charAt(i + patternLength);
            // If preHash value is negative, add as many prime values as needed to make it positive.
            // Adding prime values does not change the result of mod prime operation.
            while (preHash < 0) {
                preHash += prime;
            }
            H[i] = preHash % prime;
        }

        return H;
    }

    private static List<Integer> getOccurrencesNaive(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
            boolean equal = true;
            for (int j = 0; j < m; ++j) {
                if (s.charAt(j) != t.charAt(i + j)) {
                    equal = false;
                    break;
                }
            }
            if (equal)
                occurrences.add(i);
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
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