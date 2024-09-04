package BJ;

import java.io.*;
import java.util.*;
//우석이 코드 참고
public class Main_1149_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, price[][], dp[][], answer;
	static boolean choice[] = new boolean[3];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		price = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			price[i][0] = Integer.parseInt(st.nextToken());
			price[i][1] = Integer.parseInt(st.nextToken());
			price[i][2] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N][3];
		for (int i = 0; i < 3; i++) {
			dp[0][i] = price[0][i];
		}
		
		//[i][n]: i번 째에 n번 색깔 칠한 결과만 저장
		//i번째에 n번 색깔을 칠하려면 i-1번째에 n을 제외한 색깔을 칠한 경우중 최소값을 가져옴 
		for (int i = 1; i < N; i++) {
			dp[i][0] = price[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = price[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = price[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		answer = Integer.MAX_VALUE;
		//마지막 3가지 경우중 최소값을 출력
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, dp[N-1][i]);
		}
		System.out.println(answer);		
	}

	
	
	

}
