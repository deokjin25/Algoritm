package BJ;

import java.io.*;
import java.util.*;

public class Main_3055_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, Dx, Dy, sx, sy, wx, wy;
	static String map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		for (int i = 0; i < R; i++) {
//			st = new StringTokenizer(br.readLine());
			map[i] = br.readLine().split("");
			for (int j = 0; j < C; j++) {
//				map[i][j] = st.nextToken();
				
				if("D".equals(map[i][j])) {
					Dx = i; Dy = j;
				}else if("S".equals(map[i][j])) {
					sx = i; sy = j;
				}else if("*".equals(map[i][j])) {
					wx = i; wy = j;
				}
			}
		}
		
		int ans = survive();
		System.out.println(ans == 0 ? "KAKTUS" : ans);
		
	}

	private static int survive() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {wx, wy, 0, 0});
		q.offer(new int[] {sx, sy, 0, 1});
		
//		boolean visitw[][] = new boolean[R][C];
//		visitw[wx][wy] = true;
		boolean visits[][] = new boolean[R][C];
		visits[sx][sy] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int t = cur[2];
			int what = cur[3];
			
			if(x == Dx && y == Dy) return t;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
				
				if(what == 0 && map[nx][ny].equals(".")) {
					map[nx][ny] = "*";
					q.offer(new int[] {nx,ny,t+1,what});
				}
				
				if(what == 1 && !visits[nx][ny] 
						&& (map[nx][ny].equals(".") || map[nx][ny].equals("D"))) {
					visits[sx][sy] = true;
					q.offer(new int[] {nx, ny, t+1, what});
				}
				
			}
			
		}
		
		return 0;
	}

}
