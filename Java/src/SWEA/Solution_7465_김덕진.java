package SWEA;

import java.io.*;
import java.util.*;

public class Solution_7465_김덕진 {
	static int T, N, M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean visit[];
	static int adj[][];
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//사람 수
			M = Integer.parseInt(st.nextToken());	//관계 수
			
			adj = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adj[from][to] = 1;
				adj[to][from] = 1;
				
			}
			
			int cnt = 0;
			visit = new boolean[N+1];	//사람은 1번부터
			for (int i = 1; i <= N; i++) {
				if(visit[i]) continue;
				cnt++;
				bfs(i);
			}
			
			System.out.println("#" + tc + " " + cnt);
			
		}
		
	}

	private static void bfs(int Start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(Start);
		visit[Start] = true;		//출발하는 곳은 방문 처리
		
		while(!q.isEmpty()) {
			int from = q.poll();
			for (int i = 1; i <= N; i++) {
				if(visit[i] || adj[from][i] == 0) continue;	//가려는 곳의 번호가 이미 방문한 곳이면 가지 않음
				visit[i] = true;	//방문 처리
				q.offer(i);		//가려는 곳 넣어주기
			}
			
		}

	}

}