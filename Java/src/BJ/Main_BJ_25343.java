package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_25343 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, arr[][], dp[][];
    
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1);
		}
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < i+1; k++) {
					for (int m = 0; m < j+1; m++) {
						if(arr[k][m]<arr[i][j]) {
							dp[i][j] = Math.max(dp[i][j], dp[k][m]+1);
						}
					}
				}
			}
		}
        
        int ans = 0;
        for (int[] d : dp) {
			for (int p : d) {
				if(p > ans) ans = p;
			}
		}
        
        System.out.println(ans);
        
        
    }

}
