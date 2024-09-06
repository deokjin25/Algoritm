package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_12865 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//물건 개수
		int K = Integer.parseInt(st.nextToken());	//버틸 수 있는 무게
		
		int[][] arr = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			int weight = arr[i][0];
			int value = arr[i][1];
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i][j-1];
				if(j >= weight) {
					dp[i][j] = Math.max(value, Math.max(dp[i-1][j-weight]+value  , dp[i-1][j]));
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j]);
				}
//				dp[i][j] = Math.max(dp[i][j-1], Math.max(dp[i-1][j-weight]+value  , dp[i-1][j]));
			}
		}
//		for (int i = 0; i < N+1; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		System.out.println(dp[N][K]);
		
	}

}
