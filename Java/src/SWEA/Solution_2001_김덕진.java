package SWEA;
import java.io.*;
import java.util.*;

public class Solution_2001_김덕진 {
	static int T, N, M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] arr = new int [15][15];
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());	//원본 배열 arr
				}
			}
			
			int[][] map = new int[N][N];	//누적합 배열
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i-1 < 0 && j-1 < 0) {
						map[i][j] = arr[i][j];
					}else if (j-1<0) {
						map[i][j] = map[i-1][j] + arr[i][j];
					}else if (i-1 < 0) {
						map[i][j] = map[i][j-1] + arr[i][j];
					}else {
						map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + arr[i][j];						
					}
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			
			int answer = 0;

			for (int i = M-1; i < N; i++) {
				for (int j = M-1; j < N; j++) {
					if(i-M < 0 && j-M < 0) {
						answer = Math.max(answer, map[i][j]);
					}else if(i-M < 0 ){
						answer = Math.max(answer, map[i][j] - map[i][j-M]);
					}else if(j-M < 0) {
						answer = Math.max(answer, map[i][j] - map[i-M][j]);
					}else {
						answer = Math.max(answer, map[i][j] - map[i-M][j] - map[i][j-M] + map[i-M][j-M]);
					}
					
				}
			}

			System.out.println("#" + tc+" "+answer);
		}
	}

}
