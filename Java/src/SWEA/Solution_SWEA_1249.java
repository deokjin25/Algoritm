package SWEA;

import java.io.*;
import java.util.*;

public class Solution_SWEA_1249 {
	static int T, N, map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				char [] ch = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
				}
			}
			
			System.out.println("#" + tc + " " + getMinDistance(0,0,N-1,N-1));
		}
		

		
		
	}

	private static int getMinDistance(int sr, int sc, int er, int ec) {
		
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sr][sc] = 0;
		pq.offer(new int[] {sr, sc, minTime[sr][sc]});
		
		while(!pq.isEmpty()) {
			int[] stopOver = pq.poll();
			int x = stopOver[0];
			int y = stopOver[1];
			int time = stopOver[2];

			if(x==er && y==ec) return time;
			
			if(visited[x][y]) continue;
			visited[x][y] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0  || nx >= N  || ny >= N
						|| minTime[nx][ny] <= time+map[nx][ny]) continue;
				minTime[nx][ny] = time + map[nx][ny];
				pq.offer(new int[] {nx, ny, minTime[nx][ny]});
				
			}
			
		}
		
		return -1;
	}

}
