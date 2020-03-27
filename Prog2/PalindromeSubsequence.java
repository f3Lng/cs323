//Lydia Feng
//2301711
//lydia.feng@emory.edu
//CS323 prog2

//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
//A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - LYDIA FENG

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   //length of string
        String s = sc.next();   //input string
        pal(s, N);
    }
    
    public static void pal(String s, int N) {
        String s2 = new StringBuilder(s).reverse().toString();   //compare to reverse string
        int[][] len = new int[N+1][N+1];    //build table
        len[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (s.charAt(i-1) == s2.charAt(j-1)) {
                    len[i][j] = len[i-1][j-1]+ 1;   //DP fill in table
                } else {
                    len[i][j] = Math.max(len[i-1][j], len[i][j-1]);
                }
            }
        }      
        System.out.println(len[N][N]);
        
        String pal = "";    //palindrome string
        int p = N;
        int q = N;
        while (p > 0 && q > 0) {    //backtrack
            if (s.charAt(p-1) == s2.charAt(q-1)) {
                pal += s.charAt(p-1);
                p--;
                q--;
            } else if (len[p-1][q] >= len[p][q-1]) {    //move to greater number or if equal, to left to get leftmost
                p--;
            } else {
                q--;
            }
        }
        System.out.println(pal);
    }  
}