package BJ;

import java.io.*;
import java.util.*;

public class Main_16926_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,R;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//r, 각 테두리값만큼 움직이면 제자리
		for (int i = 0; i < R; i++) {
			//왼쪽 모서리(0,0) / 오른쪽 모서리(N-1, M-1)
			arr = roll(0,0,N-1,M-1, arr);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	
	private static int[][] roll(int x1, int y1, int x2, int y2, int[][] arr) {
		if(x2 - x1 <=0 || y2 - y1 <=0) return arr;

		int origin = arr[x1][y1];
//		System.out.println(origin);
		
		//위쪽
		for (int i = y1; i < y2; i++) {
			arr[x1][i] = arr[x1][i+1];
		}
		
		//오른쪽
		for (int i = x1; i < x2; i++) {
			arr[i][y2] = arr[i+1][y2];
		}
		
		//아래쪽
		for (int i = y2; i > y1; i--) {
			arr[x2][i] = arr[x2][i-1];
		}
		
		//왼쪽
		for (int i = x2; i > x1; i--) {
			arr[i][y1] = arr[i-1][y1];
		}
		
		
		arr[x1+1][y1] = origin;		
		
		
		roll(x1+1,y1+1,x2-1,y2-1, arr);
		return arr;
		
	}

}
