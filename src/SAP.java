import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class SAP {

    private final Digraph Graph;

    public SAP(Digraph G) {
        this.Graph = new Digraph(G);
    }

    public int length(int v, int w) {
        checkArg(v, w);
        if (v == w)
            return 0;
        boolean marked[] = new boolean[Graph.V()];
        Stack<Integer> vertices = new Stack<Integer>();
        dfs(v, marked, vertices);
        Digraph reverseD = new Digraph(Graph);
        reverseD = reverseD.reverse();
        BreadthFirstDirectedPaths BFS;
        int pathLength = (int) Double.POSITIVE_INFINITY;
        for(int i : vertices) {
            BFS = new BreadthFirstDirectedPaths(reverseD, i);
            if (BFS.hasPathTo(w)) {
                if (BFS.distTo(w) + BFS.distTo(v) < pathLength ) {
                    pathLength = BFS.distTo(w) + BFS.distTo(v);
                }
            }
        }
        if (pathLength != (int) Double.POSITIVE_INFINITY) {
            return pathLength;
        } else {
            return -1;
        }
    }

    public int ancestor(int v, int w) {
        checkArg(v, w);
        if (v < 0 || w < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        if (v == w)
            return w;
        boolean marked[] = new boolean[Graph.V()];
        Stack<Integer> vertices = new Stack<Integer>();
        dfs(v, marked, vertices);
        Digraph reverseD = new Digraph(Graph);
        reverseD = reverseD.reverse();
        BreadthFirstDirectedPaths BFS;
        int anc = -1;
        int pathLength = (int) Double.POSITIVE_INFINITY;
        for(int i : vertices) {
            BFS = new BreadthFirstDirectedPaths(reverseD, i);
            if (BFS.hasPathTo(w)) {
                if (BFS.distTo(w) + BFS.distTo(v) < pathLength ) {
                    pathLength = BFS.distTo(w) + BFS.distTo(v);
                    anc = i;
                }
            }
        }
        return anc;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        checkArg(v, w);
        boolean marked[] = new boolean[Graph.V()];
        Stack<Integer> vertices = new Stack<Integer>();
        Digraph reverseD = new Digraph(Graph);
        reverseD = reverseD.reverse();
        int pathLength = (int) Double.POSITIVE_INFINITY;
        BreadthFirstDirectedPaths BFS;
        for (int i : v) {
            dfs(i, marked, vertices);
            for(int j : vertices) {
                BFS = new BreadthFirstDirectedPaths(reverseD, j);
                for (int k : w) {
                    if (i == k) {
                        return 0;
                    }
                    if (BFS.hasPathTo(k)) {
                        if (BFS.distTo(k) + BFS.distTo(i) < pathLength ) {
                            pathLength = BFS.distTo(i) + BFS.distTo(k);
                        }
                    }
                }
            }
            marked = new boolean[Graph.V()];
            vertices = new Stack<Integer>();
        }
        if (pathLength != (int) Double.POSITIVE_INFINITY) {
            return pathLength;
        } else {
            return -1;
        }
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        checkArg(v,w);
        boolean marked[] = new boolean[Graph.V()];
        Stack<Integer> vertices = new Stack<Integer>();
        Digraph reverseD = new Digraph(Graph);
        reverseD = reverseD.reverse();
        int pathLength = (int) Double.POSITIVE_INFINITY;
        int anc = -1;
        BreadthFirstDirectedPaths BFS;
        for (int i : v) {
            dfs(i, marked, vertices);
            for(int j : vertices) {
                BFS = new BreadthFirstDirectedPaths(reverseD, j);
                for (int k : w) {
                    if (i == k) {
                        return k;
                    }
                    if (BFS.hasPathTo(k)) {
                        if (BFS.distTo(k) + BFS.distTo(i) < pathLength ) {
                            pathLength = BFS.distTo(i) + BFS.distTo(k);
                            anc = j;
                        }
                    }
                }
            }
            marked = new boolean[Graph.V()];
            vertices = new Stack<Integer>();
        }
        return anc;
    }

    private void dfs(int a, boolean[] marked, Stack<Integer> vertices) {
        vertices.push(a);
        marked[a] = true;
        for (int i : Graph.adj(a))  {
            if (!marked[i]) {
                vertices.push(i);
                dfs(i,marked,vertices);
            }
        }
    }

    private void checkArg(int v, int w) {
        if (v < 0 || w < 0 || w > this.Graph.V() - 1 ||  v > this.Graph.V() - 1) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private void checkArg(Iterable<Integer> v, Iterable<Integer> w) {
        try {
            if (v == null || w == null) {
            throw new java.lang.IllegalArgumentException();
        }
            for (int i : v) {
                if (i < 0 || i > this.Graph.V() - 1) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
            for (int i : w) {
                if (i < 0 || i > this.Graph.V() - 1) {
                    throw new java.lang.IllegalArgumentException();
                }
            } }
        catch (NullPointerException e) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int length = sap.length(2, 6);
        int anc = sap.ancestor(2, 6);
//        Stack<Integer> v = new Stack<Integer>();
//        v.push(8);
//        v.push(3);
//        Stack<Integer> w = new Stack<Integer>();
//        w.push(5);
//        w.push(9);
//        int length2 = sap.length(v,w);
//        int anc2 = sap.ancestor(v, w);
        StdOut.print("AAA");
    }
}
