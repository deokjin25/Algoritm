package SWEA;

import java.io.*;
import java.util.*;

public class Solution_2805_김덕진 {
	/*
	 * 마름모 범위, 맨해튼 거리
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,T;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			int profit = 0;
			int d = N/2;	//맨해튼 거리 범위이자 중심점의 x,y
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String string = st.nextToken();
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(string.charAt(j)+"");
					if(Math.abs(i-d) + Math.abs(j-d) <= d) profit += map[i][j];
				}
			}
			
			System.out.println("#" +tc+" "+profit);
			
			
		}

	}

}
