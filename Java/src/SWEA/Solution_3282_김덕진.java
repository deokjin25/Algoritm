package SWEA;

import java.io.*;
import java.util.*;

/*
 * 행: 물건의 개수, 열: 가방의 부피(1단위)
 * dp의 현재(dp[i][j]) 판단 기준(아래의 경우 중 최대 값)
 * 1. (현재 물건을 담을 수 없을 때) 같은 부피 내 이전 선택 값
 * 2. (현재 물건이 담을 수 있는 부피가 되었을 때)
 * 3. 현재 판단중인 물건을 선택하지 않고 이전에 선택했던 최대값
 * 4. 현재 판단중인 물건도 선택하고 이전에 선택했던 물건도 모두 선택하는 경우(현재 물건 가치값 + 현재 열(부피)에서 현재 물건의 부피만큼 뺐을 때 최선의 가치 최대값)
 */

public class Solution_3282_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, K, V, C, dp[][];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //물건의 개수
			K = Integer.parseInt(st.nextToken()); //가방의 부피
			
			dp = new int[N+1][K+1];	//범위 조건 분기를 피하기 위한 0열, 0행 더미 공백 칸 생성
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				V = Integer.parseInt(st.nextToken());	//물건 한개의 부피
				C = Integer.parseInt(st.nextToken());	//물건 한개의 가치
				
				for (int j = 1; j <= K; j++) {
					if(j < V) {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
					}else {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-V] + C);
					}
				}
			}
			
			//물건과 부피에 따라 변하는 배열 확인 검수 코드 
			for (int i = 0; i < N+1; i++) {
				System.out.println(Arrays.toString(dp[i]));
			}
			
			System.out.println("#"+tc+" "+dp[N][K]);
		}
		
		
	}

}
