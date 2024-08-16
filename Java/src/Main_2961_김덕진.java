import java.io.*;
import java.util.*;

public class Main_2961_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] flavor;
	static int min = 10000000;
	static boolean[] check;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		flavor = new int[N][2];		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int acidity = Integer.parseInt(st.nextToken());
			int acerbity = Integer.parseInt(st.nextToken());
			flavor[i] = new int[] {acidity, acerbity};
		}
		check = new boolean[N];	//재료 사용 여부 체크배열
		cook(0);
		System.out.println(min);
		
	}

	private static void cook(int idx) {
		if(idx == N) {	//모든 재료 조합을 살폈을 때
			
			int a = 1;	//신맛
			int b = 0;	//쓴맛
			int cnt = 0;	//재료 개수 카운트
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					a *= flavor[i][0];
					b += flavor[i][1];
					cnt++;
				}
			}
			
			if(cnt==0) return; //공집합인 경우 pass
			
			min = Math.min(min, Math.abs(a-b));

			return;	//최소값 갱신하고 종료
		}

		check[idx] = true;	//idx 번째 재료 선택
		cook(idx+1);
		check[idx] = false; //idx 번째 재료 미선택
		cook(idx+1);

		
	}
}
