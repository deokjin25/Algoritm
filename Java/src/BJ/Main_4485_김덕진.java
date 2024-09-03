package BJ;

import java.io.*;
import java.util.*;

public class Main_4485_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int map[][], N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean visit[][];
	
	public static void main(String[] args) throws Exception {
		int tc = 0;
		while(true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//입력 끝
			visit = new boolean[N][N];
			System.out.println("Problem " + tc + ": " + getMindistance(0,0,N-1,N-1));			
		}
	}

	private static int getMindistance(int sr, int sc, int er, int ec) {
		//[0]: i, [1]: j, [2]: map[i][j]
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		int[][] minMap = new int[N][N];		//0,0에서 특정 칸 까지의 최소비용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minMap[i][j] = Integer.MAX_VALUE;	//최대치로 초기화
			}
		}
		
		minMap[sr][sc] = map[sr][sc];	//시작값 세팅
		pq.offer(new int[] {sr,sc,map[sr][sc]});	//시작 위치 큐 삽입
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int r = now[0];
			int c = now[1];
			int cost = now[2];
			
			if(r == er && c == ec) return cost;		//목표지점 도착시 종료.
			if(visit[r][c]) continue;
			visit[r][c] = true;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr<0 || nc<0  || nr >= N  || nc >= N		//범위 밖이거나
						 || minMap[nr][nc] <= cost + map[nr][nc]) continue;	//현재값(시작부터 특정 칸 까지의 비용) + 앞으로 가져갈 값 >= 미리 계산된 값이면 pass
				minMap[nr][nc] = cost + map[nr][nc];	// 현재값+앞으로 가져갈 값이 이미 갱신된 값보다 작으니 재갱신(사실 우선순위 큐라 제일 먼저 들어오는게 제일 작음)
				pq.offer(new int[] {nr,nc,minMap[nr][nc]});	//앞으로 가져갈 위치랑, 그 때의 값(map[nr][nc]가 아니라 시작위치부터 칸 "까지의" 비용)
			}
			
		}
		
		return -1;
	}
}