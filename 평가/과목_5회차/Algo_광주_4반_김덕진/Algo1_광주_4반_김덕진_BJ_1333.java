import java.util.Scanner;

public class Algo1_광주_4반_김덕진 {
	
	static int N, L, D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		D = sc.nextInt();
		
		System.out.println(call());
		
		
	}

	private static int call() {
		// 연결될 수 있는 모든 구간과 시도하는 시간이 구간 내에 있는지 확인
		// 안내멘트 끝날 때 연결요청하면 성공이므로  | 안내멘트 끝나는 구간 <= 연결 요청 시간
		// 안내멘트 시작할 때 연결요청하면 실패이므로 | 연결 요청 시간 < 안내멘트 시작 시간
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(j*L + 5*(j-1) <= i*D && i*D < j*L + j*5) {
					return i*D;
				}
				
			}
		}
		return N*(L+5);
	}

}
