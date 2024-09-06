package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1249_fluid_김덕진 {
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

			System.out.println("#" + tc + " " + getMin());
		}
		
	}

	private static int getMin() {
		//[0]: x, [1]: y, [2]: 비용
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		pq.offer(new int[] {0,0,map[0][0]});
		
		//비용배열 생성
		int[][] costArr = new int[N][N];
		for (int i = 0; i < N; i++) {	//큰 값으로 초기화
			Arrays.fill(costArr[i], Integer.MAX_VALUE);
		}
		costArr[0][0] = 0;	//시작위치 0
		
		//방문배열 생성
		boolean[][] visit = new boolean[N][N];
		visit[0][0] = true;
		
		while(!pq.isEmpty()) {
			int[] xy = pq.poll();
			int x = xy[0];
			int y = xy[1];
			int cost = xy[2];
			
			if(x == N-1 && y == N-1) return cost;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] 
						|| cost+map[nx][ny] >= costArr[nx][ny]) continue;	//이미 갱신된 값보다 크거나 같으면 볼 필요 없음
				costArr[nx][ny] = cost+map[nx][ny];
				pq.offer(new int[] {nx,ny,cost+map[nx][ny]});
			}
		}
		
		
		
		return -1;
	}


}
