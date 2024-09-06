package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5215_DP_김덕진 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//물건 개수
			int K = Integer.parseInt(st.nextToken());	//버틸 수 있는 무게
			
			int[][] arr = new int[N+1][2];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			//dp의 행(재료 번호) 열(칼로리)
			//특정 행 열 번호에서 같은 칼로리(같은 열)인데 위쪽(이전 재료)에서 갱신된 값과(이 경우 지금 재료 선택x)
			//현재 칼로리에서 이전 선택(i-1)중 지금 재료의 칼로리만큼 뺐을 때 갱신된 값(j-cal)을 비교(이 경우 지금 재료 선택)
			//지금있는 칼로리(j)상에서는 현재 재료를 선택할 수가 없을 땐 (j < cal)
			//이전 선택에서 갱신된 값(i-1)과 전 칼로리(j-1)에서 갱신된 값을 비교 
			int[][] dp = new int[N+1][K+1];
			for (int i = 1; i <= N; i++) {
				int score = arr[i][0];
				int cal = arr[i][1];
				for (int j = 1; j <= K; j++) {
					if(j >= cal) {
						dp[i][j] = Math.max(score, Math.max(dp[i-1][j-cal]+score  , dp[i-1][j]));
					}else {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
			System.out.println("#" + tc + " " + dp[N][K]);
		}
		
		
	}

}
