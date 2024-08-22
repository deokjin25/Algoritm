package BJ;

import java.util.*;

public class Main_BJ_2630 {
	static int[][] map;
	static int N,blue,white;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		split(0,0,N);
		System.out.println(white);
		System.out.println(blue);

	}
	private static void split(int x, int y, int n) {
		int tmp = 0;
		for (int i = x; i < x+n; i++) {
			for (int j = y; j < y+n; j++) {
				if(map[i][j] == 1) tmp++;
			}
		}
		
		if(tmp == n*n) {
			blue++;
			return;
		}else if(tmp == 0) {
			white++;
			return;
		}else {
			split(x, y, n/2);	//1사분면
			split(x, y+n/2, n/2); //2사분면
			split(x+n/2, y, n/2); //3사분면
			split(x+n/2, y+n/2, n/2); //4사분면
		}
		
	}

}
