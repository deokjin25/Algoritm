package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, ans, map[][];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};	
	static boolean visit[][][];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str.charAt(j)+"");
			}			
		}
		
		visit = new boolean[N][M][2];
		System.out.println(bfs());
		
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		//x, y, 이동횟수, 벽을 부순 여부 
		q.offer(new int[] {0, 0, 1, 0});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int d = now[2];
			int w = now[3];
			
			if(x == N-1 && y == M-1) {
				return d;
			}
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny][w]) continue;
				
				if(map[nx][ny] == 0) {
					visit[nx][ny][w] = true;
					q.offer(new int[] {nx, ny, d+1, w});
				}
				
				if(map[nx][ny] == 1 && w == 0 && !visit[nx][ny][1]) {
					visit[nx][ny][1] = true;
					q.offer(new int[] {nx, ny, d+1, 1});
				}
				
			}
			
		}
		
		
		
		return -1;
	}
}
