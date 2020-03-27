//Lydia Feng
//lydia.feng@emory.edu
//2301711
//CS323 Prog 1
//
//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
//A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Lydia Feng
//
//Array Max, passed all tests

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class ArrayMax {

    static class Pair {
        final int v;    //value
        final int i;    //index
        
        Pair(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }
    
    public static void main(String[] args) throws IOException {

        //parser from provided code (Grigni)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());   //number of ints
        int N = Integer.parseInt(st.nextToken());   //number of assignments
        
        int[] a = new int[M];    //all zero
        
        PriorityQueue<Pair> pq = new PriorityQueue<Pair> (
            new Comparator<Pair>() {    //maxPQ
                public int compare(Pair a, Pair b) {
                    int comp = b.v - a.v;
                    if (comp == 0) {    //if values are same, max is the one with the smaller index
                        if (b.i < a.i ) {
                            comp = 1;
                        } else {
                            comp = -1;
                        }
                    }
                    return comp;
                }
            }
        );

       
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());    //index
            int v = Integer.parseInt(st.nextToken());   //value
            a[i] = v;
                        
            pq.add(new Pair(v, i));
            
            Pair max = pq.peek();

            while (a[max.i] != max.v) { //check if max is still stale (is the head of PQ a pair that's currently in array?)
                pq.poll();  //if stale, remove and check next max
                max = pq.peek();
            } 
            
            //if/when max is not stale, print index of max Pair
            System.out.println(max.i);
            
        }
    }
}