package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_2146 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean visit[][];
	static Queue<int[]> sea;
	static PriorityQueue<Integer> pq;
			
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][N];
		sea = new ArrayDeque<>();
		int landIdx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0 || visit[i][j]) continue;
				bfs(i,j,landIdx++);
			}
		}
		
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0) {
					bridgeBfs(i,j, map[i][j]);
				}
			}
		}
		
		System.out.println(pq.poll());
	}

	private static void bridgeBfs(int i, int j, int idx) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {i, j, 0});
		
		boolean[][] visited = new boolean[N][N];
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			int d = xy[2];
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(map[nx][ny] == 0) {
					q.offer(new int[] {nx,ny,d+1});
				}else if(map[nx][ny] == idx){
					q.offer(new int[] {nx,ny,0});
				}else {
					pq.offer(d);
					return;
				}
			}
			
			
		}
		
		
	}

	private static void bfs(int i, int j, int idx) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {i, j});
		
		visit[i][j] = true;
		map[i][j] = idx;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] || map[nx][ny] == 0) continue;
				
				visit[nx][ny] = true;
				map[nx][ny] = idx;
				q.offer(new int[] {nx, ny});
			}
		}
		
	}

}
