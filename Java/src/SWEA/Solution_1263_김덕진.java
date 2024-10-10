package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1263_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, arr[][];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			for (int i = 0; i < N*N; i++) {
				arr[i/N][i%N] = Integer.parseInt(st.nextToken());
				if(arr[i/N][i%N] == 0 && (i/N != i%N)) arr[i/N][i%N] = 10000;
			}
			
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
					}
				}
			}
			
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int tmp = 0;
				for (int j = 0; j < N; j++) {
					tmp += arr[i][j];
				}
				ans = tmp < ans ? tmp : ans;
			}
			System.out.println("#" + tc + " "  +ans);
			
		}
		
	}

}
