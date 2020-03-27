// This program is complete, except for the edgeType method.

// Do DFS in a Digraph, and classify its edges.
// (We assume no parallel edges or self-loops.)
//
// Usage:  java DFSEdges tinyDG.txt

import  edu.princeton.cs.algs4.Digraph;
import  edu.princeton.cs.algs4.StdOut;
import  edu.princeton.cs.algs4.In;

public class DFSEdges
{
    private int[] beg;          // step when dfs(v) begins (or -1 if v not yet marked)
    private int[] end;          // step when dfs(v) ends
    private int[] edgeTo;       // edgeTo[v] is parent that discovered v, or -1 at roots
    private int steps = 0;      // step counter

    public DFSEdges(Digraph G) {
        int V = G.V();
        beg = new int[V];
        end = new int[V];
        edgeTo = new int[V];
        for (int i=0; i<V; ++i) {
            beg[i] = -1;        // not marked
            edgeTo[i] = -1;     // no parent
        }
        // Full DFS traversal: mark every vertex
        for (int v=0; v<V; ++v)
            if (beg[v]==-1)     // not marked?
                dfs(G, v);      // v is a new root
        assert steps == 2*V;
    }

    private void dfs(Digraph G, int v) {
        beg[v] = steps++;
        for (int w: G.adj(v))
            if (beg[w] == -1) { // not marked?
                edgeTo[w] = v;
                dfs(G, w);
            }
        end[v] = steps++;
    }

    // Given edge (v,w), return a string identifying its type, one of
    // "tree", "back", "forward", and "cross".
    public String edgeType(int v, int w) {

        if ((beg[w] > beg[v]) && (edgeTo[w] != v) && (end[v] > end[w])) {
            return "forward";
        }

        if (edgeTo[w] == v) {
            return "tree";
        }

        if ((beg[v] > beg[w]) && (end[w] > end[v])) {
            return "back";
        }

        if ((beg[v] > beg[w]) && (end[w] < end[v])) {
            return "cross";
        }

        else {
            return "no edge type identified";
        }

    }

    // Test using Digraph named on command line.
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(new In(args[0]));
        DFSEdges DE = new DFSEdges(G);
        // Print the type of every edge in G
        for (int v=0; v < G.V(); ++v)
            for (int w: G.adj(v))
                StdOut.println(v + " " + w + " " + DE.edgeType(v,w));
    }
}
