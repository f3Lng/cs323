// This is a standalone version of Quick.java from our textbook, see:
// https://algs4.cs.princeton.edu/23quicksort/Quick.java.html

// In particular the recursive "sort" method here is modified to
// make only one recursive call.

public class Quick
{
    // top-level sort, expected time O(n lg n)
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort subarray a[lo..hi]  (inclusive)
    private static void sort(Comparable[] a, int lo, int hi) {
        // ORIGINAL (double recursion):
        // if (hi <= lo) return;
        // int j = partition(a, lo, hi);
        // sort(a, lo, j-1);    // recurse on left
        // sort(a, j+1, hi);    // recurse on right
        // NEW (single recursion):
        while (lo < hi) {
            int j = partition(a, lo, hi);
            sort(a, lo, j-1);   // recurse on left
            lo = j+1;           // iterate on right
        }
    }

    // partition subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi],
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];   // pivot value
        while (true) {
            while (less(a[++i], v)) { if (i == hi) break; }
            while (less(v, a[--j])) { if (j == lo) break; }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);         // move v to a[j]
        return j;
    }

    // private helper functions

    // comparison: is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // randomly shuffle array
    private static void shuffle(Object[] a) {
        int n = a.length;
        for (int i=0; i<n-1; ++i) {
            // pick random index j, i <= j < n
            int j = i + (int)((n-i)*Math.random());
            exch(a, i, j);
        }
    }

    // simple test driver (can sort command-line arguments)
    public static void main(String[] args) {
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        if (args.length > 0)
            a = args;
        Quick.sort(a);
        for (String s: a)
            System.out.println(s);
    }
}
