package SWEA;
import java.util.*;
import java.io.*;
public class Solution_3421_김덕진 {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, T;
	static int[] arr;
	static boolean[] check;
	static int[][] marr;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			check = new boolean[N];
			
			//모든 숫자 배열 N(부분 집합이 잘 출력 되는지 확인용)
//			for (int i = 1; i <= N; i++) {
//				arr[i-1] = i;
//			}
			
			marr = new int[M][2];
			//조건 배열 M
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				marr[i] = new int[]{a,b};
			}
			
			cnt = 0;
			select(0);
			System.out.println("#" + tc + " " +cnt);
		}
	}

	private static void select(int idx) {
		if(idx == N) {
			//M배열 돌면서 a,b가 같이 있는지(check 배열에 둘다 true로 찍혀 있는지) 확인
			for (int i = 0; i < M; i++) {
				if (check[marr[i][0]] && check[marr[i][1]]) {
					return;
				}
			}
			//부분집합이 잘 들어와 있는지 확인하는 코드
//			for (int i = 0; i < N; i++) {				
//				System.out.printf((check[i])? arr[i]+" ":" ");
//			}
//			System.out.println();
			cnt++;	//위에서 return 안 당하고 내려왔으면 조건에 위배 되는 조합이 아니므로 카운트 + 1
			return;
		}
		
		check[idx] = true;
		select(idx+1);
		check[idx] = false;
		select(idx+1);
		
		
		
		
	}
}
