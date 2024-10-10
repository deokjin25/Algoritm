package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_11053 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, arr[], dp[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		//Math.abs(Arrays.binarySearch(dp, 1))-1
		for (int i = 0; i < N; i++) {
			if(Arrays.binarySearch(dp, arr[i]) >= 0) continue;
			int x = Math.abs(Arrays.binarySearch(dp, arr[i]))-1;
			dp[x] = arr[i];
		}
		
		int ans = 1;
		for (int i = 0; i < N; i++) {
			if(dp[i] != Integer.MAX_VALUE) {
				ans = i+1;
			}
		}
		
		System.out.println(ans);
	}
}
