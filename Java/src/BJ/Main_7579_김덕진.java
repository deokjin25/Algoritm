package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7579_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, marr[], carr[];
	static int dp[][] = new int[101][10000001];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //앱의 개수
		M = Integer.parseInt(st.nextToken()); //확보해야 하는 메모리
		
		st = new StringTokenizer(br.readLine());
		marr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			marr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		carr = new int[M+1];
		for (int i = 1; i <= N; i++) {
			carr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= M; j++) {
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
		
		System.out.println(dp[N][M]);
		
		
	}

}
