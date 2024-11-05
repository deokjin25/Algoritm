package BJ;

import java.io.*;
import java.util.*;

public class Main_17472_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], parents[];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean visit[][];
	static Queue<int[]> sea;
	static PriorityQueue<int[]> adj;
			
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][M];
		sea = new ArrayDeque<>();
		int landIdx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 || visit[i][j]) continue;
				bfs(i,j,landIdx++);
			}
		}

		
		adj = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] > 0) {
					bridgeBfs(i,j, map[i][j]);
				}
			}
		}
		
		//5개의 섬이 찍혔을 때, landIdx: 6, 2부터 관리 
		parents = new int[landIdx];
		for (int i = 2; i < landIdx; i++) {
			parents[i] = i;
		}
		
		int ans = 0;
		int cnt = 0;
		while(!adj.isEmpty()) {
			int[] edge = adj.poll();
			int from = edge[0];
			int to = edge[1];
			int length = edge[2];
			
			if(union(from,to)) {
				ans += length;
				cnt++;
				if(cnt == landIdx - 3) break;
			}
		}
		
		cnt = 0;
		for (int i = 2; i < parents.length; i++) {
			if(parents[i] == i) cnt++;
		}
		
		System.out.println(cnt == 1 ? ans : -1);
	}

	private static boolean union(int from, int to) {
		int a = findSet(from);
		int b = findSet(to);
		
		if(a != b) {
			parents[b] = a;
			return true;
		}
		
		
		return false;
	}

	private static int findSet(int from) {
		if(from == parents[from]) {
			return from;
		}else {
			return parents[from] = findSet(parents[from]);
		}
	}

	private static void bridgeBfs(int i, int j, int idx) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {i, j});
		
		boolean[][] visited = new boolean[N][M];
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(map[nx][ny] == idx) {
					q.offer(new int[] {nx,ny});
				}else if(map[nx][ny] == 0){
					dfs(nx,ny,k, idx, 0);
				}
			}
			
		}
		
	}

	private static void dfs(int nx, int ny, int k, int idx, int len) {
		if(nx<0 || ny<0 || nx>=N || ny>=M) return;
		
		else if(map[nx][ny] == 0) {
			dfs(nx+dx[k], ny+dy[k], k, idx, len+1);
		}else if(map[nx][ny] != idx){
			if(len >= 2) adj.offer(new int[] {idx, map[nx][ny], len});
			return;
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
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] || map[nx][ny] == 0) continue;
				
				visit[nx][ny] = true;
				map[nx][ny] = idx;
				q.offer(new int[] {nx, ny});
			}
		}
		
	}

}
