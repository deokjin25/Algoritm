package BJ;

import java.io.*;
import java.util.*;

public class Main_17070_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static boolean wall[][];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		wall = new boolean[N+1][N];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) wall[i][j] = true;
			}
		}
		
		//가로는 왼쪽칸에 가로 파이프  or 왼쪽위에 대각선 파이프가 있어야 하고
		//세로는 위쪽칸에 파이프 or 왼쪽위에 대각선 파이프가 있어야 하고
		//대각선은 왼쪽에 가로 파이프 or 위쪽에 세로 파이프 or 왼쪽 위에 파이프가 있어야 함.
		//한 점에서의 경우의 수는 그 전까지 칸에 기록되어 있다.
		//파이프의 끝을 기준으로 잡고 현재칸에 비교하는 칸에서 파이프를 끌어와서 파이프의 끝을 둔다고 생각하면 편함
		//3차원에 가로, 세로, 대각선 가능한 경우 저장, 갱신 할거임
		//[0]: 가로끝, [1]: 세로끝, [2]: 대각선 끝
		long[][][] dp = new long[N+1][N][3];
		dp[1][1][0] = 1;	//처음 파이프 끝(가로)
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < N; j++) {	//0열은 볼 필요 없음(왼쪽으로 안 돌림)
				if(!wall[i][j]) {	//현재 칸에 벽이 없으면 가로 or 세로 파이프 가능
					dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
					dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				}
				
				if(!wall[i][j] && !wall[i-1][j] && !wall[i][j-1]) {		//대각선 파이프는 끝을 기준으로 현재칸 & 위쪽 칸 & 왼쪽 칸 다 벽이 없어야 함
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];					
				}
			}
		}
		
		//모든 가능한 경우 합산
		System.out.println(dp[N][N-1][0] + dp[N][N-1][1] + dp[N][N-1][2]);
		
	}

}
