package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_Solution_22683 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, K;
	static String map[][];
	static boolean visit[][][];
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	
	static class rc {
		int x;
		int y;
		int k;	//나무를 베어낸 횟수
		int d;	//상0 좌1 하2 우3
		int m;	//조작 횟수
		
		public rc(int x, int y, int k, int d, int m) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.d = d;
			this.m = m;
		}

		@Override
		public String toString() {
			return "rc [x=" + x + ", y=" + y + ", k=" + k + ", d=" + d + ", m=" + m + "]";
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			rc start = null;
			
			map = new String[N][N];
			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = s[j];
					if(map[i][j].equals("X")) {
						start = new rc(i,j,0,0,0);
					}
				}
			}
			
			visit = new boolean[N][N][K+1];
			System.out.println("#" + tc + " " + bfs(start));
			
		}

	}


	private static int bfs(rc start) {
		Queue<rc> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.m, o2.m));
		q.offer(start);
		visit[start.x][start.y][0] = true;
		
		while(!q.isEmpty()) {
			rc cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int k = cur.k;
			int d = cur.d;
			int m = cur.m;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || ny<0 || nx >= N || ny >= N || visit[nx][ny][k]) continue;
				visit[nx][ny][k] = true;
				
				if(map[nx][ny].equals("Y")) return m+operationCount(d, i);
				
				if(map[nx][ny].equals("G")) {
					q.offer(new rc(nx,ny,k,i,m+operationCount(d, i)));
				}
				
				if(map[nx][ny].equals("T") && k < K) {
					q.offer(new rc(nx,ny,k+1,i,m+operationCount(d, i)));
				}
			}
		}
		
		return -1;
	}


	private static int operationCount(int d, int i) {
		int c;
		if(Math.abs(d-i) == 3) c = 1;
		else c = Math.abs(d-i);
		return c+1;	//전진 조작 +1
	}

}
