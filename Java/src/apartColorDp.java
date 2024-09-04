import java.util.Scanner;
/*
 * 아파트를 각 층별로 파란색 또는 노란색 페인트로 칠하되 다음과 같은 규칙으로 칠하려고 한다. 
 * 노란색은 인접한 두 층에 연속하여 사용할 수 있다.
 * 파란색은 인접한 두 층에 연속하여 사용할 수 없다.
 */
public class apartColorDp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println(dp(x));
	}

	private static int dp(int x) {
		if(x == 1) return 2;
		if(x == 2) return 3;
		return dp(x-1) + dp(x-2);
		/*
		 * x번째 일때 경우의 수는 직전 경우에서의 제일 위에 있는 블럭에 노란색이 올라오는 경우 + 파란색이 올라오는 경우
		 * 결국 노란색이 올라오는 경우 + 파란색이 올라오는 경우를 계산한 게 dp(n)의 값.
		 * -> 직전 값(노란색은 밑에 뭐가 있든 올라올 수 있음) +  직전에 있던 노란색개수(파란색은 노란색 위에만 올 수 있음)
		 * 직전 값은 dp(n-1)에 있고 직전에 있던 노란색의 개수는 어디에 있을까 -> 전전 값에서 노란색을 전부 올리는 경우가 곧 직전의 노란색 경우의 수 dp(n-2)
		 * --> (x-1)번 째 값. + (x-1번째의 노란색 개수) => dp(n-1) + dp(n-2)
		 */
	}

}
