package BJ;

import java.util.*;

public class Main_BJ_10844 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//행: N번째 계단수(1번부터 관리), 열:0~9까지의 숫자, 행 번호바다 특정 열에서 몇개가 가능한지 메모이제이션
		//특정 계단의 경우의 수는 1번부터 9번 열까지 갱신된 숫자들 합산하면 됨(0으로 시작하는 수는 없다.)
		long[][] dp = new long[N+1][10];
		
		//한자리 수 계단 숫자들 1로 초기화(0은 2자리 계단을 위해 1로 미리 만들어둠)
		for (int i = 0; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		long x = 1000000000;
		//두자리 수 계단부터 시작
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				//0번은 현 시점(i)에서 쓰지 않고 다음 행을 위한 값임(의미상 2번째 숫자부터는 0이 들어올 수 있으므로)
				if(j == 0) dp[i][0] = dp[i-1][1]%x;
				//9일 땐 자기보다 낮은 8의 값만 가져갈 수 있다.
				else if(j == 9) dp[i][9] = dp[i-1][8]%x;
				//나머지(1~8) 수들은 자기보다 작은 값(j-1) 혹은 높은 값(j+1)을 가져감(i-1 에서 가져옴);
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%x;
			}
		}
		
		//첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
		long ans = 0;
		for (int i = 1; i <= 9; i++) {
			ans += dp[N][i];
			ans %= x;
		}
		System.out.println(ans);
		
	}

}
