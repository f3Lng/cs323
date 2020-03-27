// Lydia Feng
// 2301711
// lydia.feng@emory.edu
// CS323 prog2

// THIS CODE IS MY OWN WORK, IT WAS WRITTING WITHOUT CONSULTING
// A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - LYDIA FENG


import java.io.*;
import java.util.*;
import java.lang.*;

public class InverseBWT {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int l = sc.nextInt();
        StringBuilder s = new StringBuilder(N);
        while (sc.hasNext()) {
            s.append(sc.next());
        }        
        inverse(s, l, N);
    }
    
    
    public static void inverse(StringBuilder string, int i, int N) {
        String s = string.toString();
        char L[] = s.toCharArray();
        int[] F = new int[N];
        int count[] = new int[127];

        for (int j = 0; j < N; j++) {
            count[(int)L[j]]++;
        }
        for (int j = 1; j <= 126; j++) {
            count[j] = count[j] + count[j-1];
        }
        
        
        for (int j = N-1; j >= 0; j--) {
            F[count[((int)L[j])]-1] = j;
            count[(int)L[j]]--;        
        }

        int x = N;

        if (x > 40) {
            for (int k = 0; k < N/40; k++) {
                for (int j = 0; j < 40; j++) {
                    System.out.print(s.charAt(F[i]));
                    i = F[i];
                    x--;
                }
                System.out.println();
            }
        }
        if (x <= 40) {
            for (int m = 0; m < x; m++) {
                               System.out.print(s.charAt(F[i]));
                    i = F[i]; 
            }
        }
        
    }
}