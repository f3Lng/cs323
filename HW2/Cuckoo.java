// THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
// A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Lydia Feng

import java.util.Random;

public class Cuckoo
{
    // Random number generator, source of random edges.
    static Random rng = new Random();

    // Do a single experiment of size n, and return
    // the number of edges needed.
    public static int experiment(int N)
    {
        UF uf = new UF(N);	// keep track of components
        int M = 0;		// number of edges added

        while (uf.count() > 0) {
            int i = rng.nextInt(N);	    // random i, 0 <= i < N
            int j = rng.nextInt(N);	    // random j, 0 <= j < N
	    // Now we add the edge (i, j).
	    // Note we count i==j as an edge.
	    // We also count repeated edges.
	    uf.union(i, j);
            ++M;

            //check if a component has more edges than vertices
            if ((uf.edges(i) > uf.size(i)) || (uf.edges(j) > uf.size(j))) {
           		break;
        }
        }
        return M;
    }

    // Do T experiments of size N, and return their average.
    public static double average(int N, int T) {
	double sum = 0;
	for (int t=0; t<T; ++t)
	    sum += experiment(N);
	return sum/T;
    }

    // Parse N, T, and S from the command line, if provided.
    // Compute the average, and report results.
    public static void main(String[] args) {
	int N = 1000, T = 500;	// defaults
	if (args.length > 0)	// parse N
	    N = Integer.parseInt(args[0]);
	if (args.length > 1)	// parse T
	    T = Integer.parseInt(args[1]);
	if (args.length > 2)	// use S as seed for rng
	    rng = new Random(Long.parseLong(args[2]));
	System.out.printf("T=%d experiments on N=%d vertices%n", T, N);
	double ave = average(N, T);
	System.out.printf("On average, needed %f edges%n", ave);
	// We expect this ratio near 0.5, for large N.
	double ratio = ave / N;
	System.out.printf("ratio is %f%n", ratio);
    }

    // Mini (inner) union-find class, good enough for our purposes.
    // This does quick-union with half-path-compression.
    static class UF {
	private int[] id;
	private int count;
	private int numEdges[];
	private int numComp[];

	public UF(int n) { // constructor
	    count = n;
	    id = new int[n];
	    for (int i=0; i<n; i++) id[i] = i;

	    //zero edges to begin with
	    numEdges = new int[n];
		for (int i = 0; i < n; i++) {
			numEdges[i] = 0;
		}

		//one component to begin with
		numComp = new int[n];
		for (int i = 0; i < n; i++) {
			numComp[i] = 1;
		}
	}
	public int count() { return count; } // number of components
	public int find(int p) {
	    while (p!=id[p])
		p=id[p]=id[id[p]]; // half-compression
	    return p;
	}
	public boolean connected(int p, int q) { return find(p)==find(q); }
	public void union(int p, int q) {
	    int fp = find(p), fq = find(q);
	     numEdges[fq]++;	//add an edge when union is called (even if its a self-loop)
	    if (fp==fq) return;
	    id[fp] = fq;
	    count--;
	    numEdges[fq] += numEdges[fp];	//combine number of edges
	    numComp[fq] += numComp[fp];		//combine number of vertices
	}

	public int size(int p) {
		int temp = find(p);		//get parent
		return numComp[temp];
	}

	public int edges(int p) {
		int temp = find(p);		//get parent
		return numEdges[temp];
	}
	// Two suggested methods to add:
	// public int size(int p) // number of vertices in component of p
	// public int edges(int p) // number of edges in component of p
    }
}

