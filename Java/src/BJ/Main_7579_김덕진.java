package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7579_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, marr[], carr[], dp[][];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //앱의 개수
		M = Integer.parseInt(st.nextToken()); //확보해야 하는 메모리
		
		st = new StringTokenizer(br.readLine());
		marr = new int[N+1];		//각 앱이 사용중인 메모리의 바이트 수
		for (int i = 1; i <= N; i++) {
			marr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		carr = new int[N+1];	//각 앱을 비활성화 했을 경우의 비용
		for (int i = 1; i <= N; i++) {
			carr[i] = Integer.parseInt(st.nextToken());
		}
		
		//행: 앱(Ai) 열: 비용
		//dp[i][j]: j까지의 비용을 지불 했을 때 취할 수 있는 메모리 최대 값
		//Max j: 100*100(최대 100개의 앱, 최대 100의 비용이므로, 모든 앱의 비용이 100일 수 있다.)
		dp = new int[N+1][N*100+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= N*100; j++) {
				if(j < carr[i]) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-carr[i]] + marr[i]);
				}
			}
			
		}
		
		//가장 작은 비용부터 살펴보면서 목표 메모리에 도달하면 출력 및 종료
		for (int i = 0; i <= N*100; i++) {
			if(dp[N][i] >= M) {
				System.out.println(i);
				break;
			}
		}
		
		
	}

}
