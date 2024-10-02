package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_25343 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, arr[][], s[], dp[], ans;
	static int[] dx = {1,0};
	static int[] dy = {0,1};
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][N];
		s = new int[N*N];
//		bfs();
		dfs(0,0,0);
		System.out.println(ans);
		
		
	}

//	private static void bfs() {
//		Queue<int[]> q = new ArrayDeque<>();
//		q.offer(new int[] {0,0,0});
//		visit[0][0] = true;
//		dp = new int[N*N];
//		Arrays.fill(dp, Integer.MAX_VALUE);
//		s = new int[N*N];
//		
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			int x = cur[0];
//			int y = cur[1];
//			int d = cur[2];
//			
//			s[d] = arr[x][y];
//			int p =(Math.abs(Arrays.binarySearch(dp, arr[x][y]))-1);
//			
//			
//			for (int k = 0; k < 4; k++) {
//				int nx = x + dx[k];
//				int ny = y + dy[k];
//				if(nx < 0 || ny<0 || nx>=N || ny>=N || visit[nx][ny]) continue;
//				visit[nx][ny] = true;
//				q.offer(new int[] {nx, ny, d+1});
//				
//			}
//		}
//	}

	private static void dfs(int x, int y, int depth) {
		s[depth] = arr[x][y];
		if(x == N-1 && y == N-1) {
			dp = new int[N*N];
			Arrays.fill(dp, Integer.MAX_VALUE);
			for (int i = 0; i < N*N; i++) {
				if(Arrays.binarySearch(dp, s[i]) >= 0) continue;
				int k =(Math.abs(Arrays.binarySearch(dp, s[i]))-1);
				dp[k] = s[i];
			}
			
			int tmp = 0;
			for (int i = 0; i < N*N; i++) {
				if(dp[i] != Integer.MAX_VALUE) tmp = i+1;	//0부터 값이 들어가 있으니 +1
			}	
						
			ans = Math.max(ans, tmp);
			return;
		}
		
		for (int k = 0; k < 2; k++) {
			int nx = x+dx[k];
			int ny = y+dy[k];
			if(nx < 0 || ny<0 || nx>=N || ny>=N || visit[nx][ny]) continue;
			visit[nx][ny] = true;
			dfs(nx,ny, depth+1);
			visit[nx][ny] = false;
		}
		
		
	}

}
