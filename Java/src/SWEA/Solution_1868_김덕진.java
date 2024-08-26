package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1868_김덕진 {
	static int T, N, min;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[] dx = {1,-1,0,0,1,1,-1,-1};
	static int[] dy = {0,0,1,-1,1,-1,1,-1};
	
	static boolean visit[][];
	static char map[][];
	static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			q = new LinkedList<>();
			visit = new boolean[N][N];
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String string = br.readLine();
				for (int j = 0; j < string.length(); j++) {
					map[i][j] = string.charAt(j);
					if(map[i][j] == '0') q.offer(new int[] {i,j});
				}
			}
			
			while(!q.isEmpty()) {
				int[] xy = q.poll();
				int x = xy[0];
				int y = xy[1];
				int value = xy[2];
				map[x][y] = (char) value;
				visit[x][y] = true;
				if(value == 0) {
					for (int k = 0; k < 8; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*' || visit[nx][ny]) continue;
						int cnt = 0;
						for (int i = 0; i < 8; i++) {
							int nr = nx+dx[i];
							int nc = ny+dy[i];
							if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
							if(map[nr][nc] == '*') cnt++;
						}
						q.offer(new int[]{nx,ny,cnt});
					}
				}
			}
			

			
			min = Integer.MAX_VALUE;
			bfs(0); 
			System.out.println("#"+tc+" "+min);
		}
		
	}

	private static void bfs(int idx) {
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			int value = xy[2];
			map[x][y] = (char) value;
			visit[x][y] = true;
			if(value == 0) {
				for (int k = 0; k < 8; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*' || visit[nx][ny]) continue;
					int cnt = 0;
					for (int i = 0; i < 8; i++) {
						int nr = nx+dx[i];
						int nc = ny+dy[i];
						if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
						if(map[nr][nc] == '*') cnt++;
					}
					q.offer(new int[]{nx,ny,cnt});
				}
			}
		}
		
		int dot = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == '*' || visit[i][j]) continue;
				dot++;
				int cnt = 0;
				for (int k = 0; k < 8; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					if (map[nx][ny] == '*') cnt++;
				}
				
				q.offer(new int[] {i,j,cnt});
				bfs(idx+1);
			}
		}
		
		if(dot==0) min = Math.min(min, idx) ;

	}


}
