package BJ;

import java.io.*;

/*
 * 재귀 한 번 들어갈 때마다 괄호 열고 빠져 나오면 닫고
 * 재귀 안에서 0/1 조건 충족 되면 더 들어갈 필요 없음
 * 최소 사이즈는 한칸일때 / 2*2 배열 일 때. << 아님
 * !압축 가능하면 그냥 바로 해야지 4분할 때리고 하면 안됨.
 */
public class Main_1992_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String map[];
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		
		map = new String[N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}
		
		split(N, 0, 0);
		System.out.println(sb);
	}

	private static void split(int n, int x, int y) {
		int cnt = 0;
		for (int i = x; i < x+n; i++) {
			for (int j = y; j < y+n; j++) {
				cnt += Integer.parseInt(map[i].charAt(j)+"");
			}
		}
		
		if(cnt == n*n) {
			sb.append("1");
		}else if(cnt == 0) {
			sb.append("0");
		}else {
			sb.append("(");
			split(n/2, x, y);	//1사분면
			split(n/2, x, y+n/2);	//2사분면
			split(n/2, x+n/2, y);	//3사분면
			split(n/2, x+n/2, y+n/2);	//4사분면
			sb.append(")");
		}
	}


}
