import java.util.Scanner;
/*
1cm 짜리 파란 막대와 1cm 짜리 노란 막대 그리고 2cm 짜리 빨간 막대가 있다.
이 막대들을 연결하여 길이가 ncm인 막대를 만드는 방법의 수를 f(n)이라 하자.
예를 들면 2cm 막대를 만드는 방법은
(파란 막대, 파란 막대),
(파란 막대, 노란 막대),
(노란 막대, 파란 막대),
(노란 막대, 노란 막대),
(빨간 막대)
5가지이므로 f(2) = 5이다. 
 */
public class 막대색칠 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println(dp(x));
	}

	private static int dp(int x) {
		if(x==0) return 1;
		if(x == 1) return 2;
		return dp(x-2) + dp(x-1)*2;
		
	}
}
