import java.io.*;
import java.util.*;

public class SetRangeSum {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;

        VertexPair() {
        }

        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        root = findAndRoot.right;
        result.right = findAndRoot.left;
        if (result.right == null) {
            result.left = root;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    Vertex root = null;

    void insert(int x) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
    }

    void erase(int x) {
        // Implement erase yourself

        // search for the key x
        if (find(x)) {
            // if x is found, remove the root of the tree in two steps

            Vertex left = root.left;
            // step 1.1: remove the pointers to the root from the root subtrees
            if (left != null) {
                left.parent = null;
            }

            Vertex right = root.right;
            // step 1.2: remove the pointers to the root from the root subtrees
            if (right != null) {
                right.parent = null;
            }

            // step 2: merge the subtrees
            root = merge(left, right);
        }
    }

    boolean find(int x) {
        // Implement find yourself

        if (XisRoot(x)) {
            // maybe the node we're searching for
            // is already in the root of the tree
            return true;
        } else {
            // the node is not the root node,
            // so we're searching for it
            VertexPair findAndRoot = find(root, x);

            // update the root with the search result
            root = findAndRoot.right;

            // return the search result
            return (XisRoot(x));
        }
    }

    // a helper method to accompany find(int)
    boolean XisRoot(int x) {
        return (root != null && root.key == x);
    }

    long sum(int from, int to) {
        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;
        long ans = 0;
        // Complete the implementation of sum

        // if we did everything correctly (which we did, because
        // it's the Coursera code), the sum will be in the root
        // of the middle subtree
        if (middle != null) {
            ans = middle.sum;
        }

        // merge everything back, update root
        left = merge(left, middle);
        root = merge(left, right);

        return ans;
    }

    public static final int MODULO = 1000000001;

    void solve() throws IOException {
        int n = nextInt();
        int last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            switch (type) {
                case '+': {
                    int x = nextInt();
                    insert((x + last_sum_result) % MODULO);
                }
                break;
                case '-': {
                    int x = nextInt();
                    erase((x + last_sum_result) % MODULO);
                }
                break;
                case '?': {
                    int x = nextInt();
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                }
                break;
                case 's': {
                    int l = nextInt();
                    int r = nextInt();
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(res);
                    last_sum_result = (int) (res % MODULO);
                }
            }
        }
    }

    SetRangeSum() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SetRangeSum();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }
}