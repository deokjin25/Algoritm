package BJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1932 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, arr[][], dp[][];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			tmp++;
			for (int j = 0; j < tmp; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][N];
		dp[0][0] = arr[0][0];
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < N; j++) {
				if(j==0) {
					dp[i+1][j] = dp[i][j] + arr[i+1][j];
					continue;
				}
				dp[i+1][j] = Math.max(dp[i][j-1] + arr[i+1][j], dp[i][j] + arr[i+1][j]);
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[N-1][i]);
		}
		System.out.println(ans);
	}

}
