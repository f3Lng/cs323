//Lydia Feng
//lydia.feng@emory.edu
//2301711
//CS323 Prog 1
//
//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS
//LYDIA FENG
//
//Tall Trucks, passed all tests

import java.io.*;
import java.util.*;
import java.lang.*;


public class TallTrucks {
    
    static class DEdge {
        int v;  //other endpoint
        int h;  //height
        DEdge next;
        DEdge(int v, int h, DEdge n) {
            this.v = v;
            this.h = h;
            next = n;
        }
    }
    
    static class Pair {
        final int vht;  //vertex height
        final int vert; //vertex
        
        Pair(int vht, int vert) {
            this.vht = vht;
            this.vert = vert;
        }
    }
    


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   //numVertices
        int M = sc.nextInt();   //numEdges
        DEdge[] adj = new DEdge[N]; //N empty adjacency lists

        
        for (int i = 0; i < M; ++i) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            int h = sc.nextInt();
            adj[u] = new DEdge(v, h, adj[u]);
            adj[v] = new DEdge(u, h, adj[v]);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(
            new Comparator<Pair> () {
                public int compare(Pair a, Pair b) {
                    return b.vht - a.vht;   //maxPQ
                }
            }
        );

        
        int[] ht = new int[N];
        ht[0] = 1000;   //max height
        
        pq.add(new Pair(ht[0], 0));
        while (!pq.isEmpty()) {
            
            Pair max = pq.poll();   //relax edges in max height order
            if (max.vht == ht[max.vert]) {  //check if height has updated since this PQ entry
                while (adj[max.vert] != null) { //check entire adjacency list
                    
                    //relax method provided in class (Grigni)
                    if (Math.min(ht[max.vert], adj[(max.vert)].h) > ht[(adj[max.vert].v)]) {
                        ht[(adj[max.vert].v)] = (Math.min(ht[max.vert], adj[(max.vert)].h));
                        pq.add(new Pair(ht[(adj[max.vert].v)], adj[max.vert].v));
                    }
                    
                    adj[max.vert] = adj[max.vert].next; //check next vertex in adjacency list
                }
            }
        }


        //print max height for path to each vertex    
        for (int l = 1; l < N; l++) {
            System.out.print(ht[l] + " ");
        }   
    }
}