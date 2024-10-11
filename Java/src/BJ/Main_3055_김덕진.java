package BJ;

import java.io.*;
import java.util.*;

public class Main_3055_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, Dx, Dy, sx, sy;
	static String map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		//큐에 들어가는 값 { x좌표, y좌표, 시간, 물(0)/고슴도치(1) 여부 } 
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				
				if("D".equals(map[i][j])) {
					Dx = i; Dy = j;
				}else if("S".equals(map[i][j])) {
					sx = i; sy = j;
				}else if("*".equals(map[i][j])) {
					q.offer(new int[] {i,j,0,0});
				}
			}
		}
		
		//고슴도치는 마지막에 넣어주기
		q.offer(new int[] {sx, sy, 0, 1});
		int ans = survive(q);
		System.out.println(ans == 0 ? "KAKTUS" : ans);
		
	}
	
	//물을 먼저 q에 넣어줬기 때문에 물 먼저 퍼지고 고슴도치가 이동.
	private static int survive(Queue<int[]> q) {
		
		boolean visitw[][] = new boolean[R][C];
		boolean visits[][] = new boolean[R][C];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int t = cur[2];
			int what = cur[3];
			
			if(what == 1 && x == Dx && y == Dy) return t;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
				
				//물은 .인 곳만 이동 가능
				if(what == 0 && map[nx][ny].equals(".") && !visitw[nx][ny]) {
					visitw[nx][ny] = true;
					q.offer(new int[] {nx,ny,t+1,what});
				}
				
				//고슴도치는 물이 방문한곳이 아니면서 . 혹은 D인 곳만 방문
				if(what == 1 && !visits[nx][ny]  && !visitw[nx][ny]
						&& (map[nx][ny].equals(".") || map[nx][ny].equals("D"))) {
					visits[nx][ny] = true;
					q.offer(new int[] {nx, ny, t+1, what});
				}
				
			}
			
		}
		
		return 0;
	}

}
