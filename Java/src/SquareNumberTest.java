import java.util.Scanner;

public class SquareNumberTest {
	
	static long callCnt1, callCnt2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		long y = sc.nextLong();
		
		System.out.println(exp2(x,y));
		System.out.println("O(logN) : "+callCnt2);
		System.out.println(exp1(x,y));
		System.out.println("O(N)  : "+callCnt1);

	}
	//곱셈의 나머지 정리 활용해서 변경할 것!!
	
	static long exp1(long x, long n) {	//O(n);
		callCnt1++;
		if(n==1) return x;
		return x*exp1(x, n-1);
	}
	
	static long exp2(long x, long n) {	//O(logN);
		callCnt2++;
		if(n==1) return x;
		long r = exp2(x,n/2);
		return n%2==0 ? r*r:r*r*x;
		
	}
	
	
}
