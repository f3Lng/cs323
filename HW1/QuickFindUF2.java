// You need to read and think about this code, as part of writ1.

// This class QuickFindUF2 is based on QuickFindUF from our textbook.
// In particular, we still have the following "quick find" feature:
//
//    * For each item p, id[p] is its leader (component identifier), so
//      we can compute find(p)==id[p] in O(1) time.
//
// However, we added the following new features:
//
//    * We add a size[] array. For each item p, the size of its
//      component is size[id[p]].  Method size(p) returns this.
//
//    * We add a next[] array.  For each component, we keep its items,
//      including the leader, in a circular linked list.  For each
//      item p, the next item in its list is next[p].
//
//    * Whenever we union two distinct components, we traverse the
//      list of the smaller component.  For each item k in that list,
//      we modify id[k] to equal the leader of the larger component.
//      Then we link the two circular lists into one.
//
// I removed the javadoc comments and the textbook dependencies, so
// this file (with "tinyUF.txt") is a standalone Java program.

import java.util.Scanner;
import java.io.File;

public class QuickFindUF2
{
    private int[] id;    // id[i] = component identifier of i (subset leader)
    private int[] size;  // size[id[i]] = size of the component
    private int[] next;  // next[i] = next item in same component (circular)
    private int count;   // number of components

    public QuickFindUF2(int n) { // setup n components of size one
        id = new int[n];
        size = new int[n];
        next = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
            next[i] = i;        // self-loop
        }
    }

    // O(1) time methods
    public int count() { return count; }
    public int find(int p) { return id[p]; }
    public boolean connected(int p, int q) { return id[p]==id[q]; }
    public int size(int p) { return size[id[p]]; }

    // This union method takes O(min(size(p), size(q))) time.
    // REMINDER: in QuickFindUF, union took O(n) time instead.
    public void union(int p, int q) {
        p = id[p];              // replace p by its leader
        q = id[q];              // replace q by its leader
        if (p == q) return;     // nothing to do?

        // Swap p and q if necessary, so that size[p] <= size[q]
        if (size[p] > size[q]) {
            int tmp = p;
            p = q;
            q = tmp;
        }

        // Traverse the circular list of p's component.
        // For every k in the list, we set id[k] = q.
        id[p] = q;
        for (int k=next[p]; k!=p; k=next[k])
            id[k] = q;
        // Swap links, merging the two circular lists into one
        int tmp = next[p];
        next[p] = next[q];
        next[q] = tmp;
        // Update the component count, and the new component size
        count--;
        size[q] += size[p];
    }

    // Like the textbook test driver, but reading from a named file.
    // We may get an exception if the named file is not found.
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(new File("tinyUF.txt"));
        int n = in.nextInt();
        QuickFindUF2 uf = new QuickFindUF2(n);
        while (in.hasNextInt()) {
            int p = in.nextInt(), q = in.nextInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
        System.out.println("component of 0 has size " + uf.size(0));
    }
}