package BJ;
import java.io.*;
import java.util.*;
/*
 * 파이프는 1, 교차해서 못가는 곳은 x?
 */

public class Main_3190_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C, cnt, max = Integer.MIN_VALUE;
	static char map[][];
	static boolean check[][];
	static int[] dx = {-1,0,1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String string = st.nextToken();
			for (int j = 0; j < C; j++) {
				map[i][j] = string.charAt(j);
			}
		}
		
		check = new boolean[R][C];
		search(0,0);
		
		System.out.println(max);
	}

	private static void search(int x, int y) {
		
		
		
		
	}
	
	
}
