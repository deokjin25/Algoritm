package SWEA;

import java.io.*;
import java.util.*;

public class Solution_3124_prim_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, V, E;
	static Long costSum;
	static List<int[]>[] adj;
	static boolean visit[];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[V+1];
			for (int i = 1; i <= V; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());	
				int to = Integer.parseInt(st.nextToken());	
				int cost = Integer.parseInt(st.nextToken());
				
				adj[from].add(new int[] {to,cost});
				adj[to].add(new int[] {from, cost});
			}
			
			costSum = (long) 0;
			visit = new boolean[V+1];	//정점 번호 1번부터 관리
			prim(1);
			System.out.println("#" + tc + " " + costSum);
			
		}
	}

	private static void prim(int start) {
		//cost 기준으로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		//start 정점에 연결된 간선들 모두 pq에 추가
		for (int i = 0; i < adj[start].size(); i++) {
			pq.offer(adj[start].get(i));
		}
		visit[start] = true;
		
		while(!pq.isEmpty()) {
			int[] Edge = pq.poll();	//최소 비용으로 선정된 정점 정보
			int vertex = Edge[0];
			int cost = Edge[1];
			
			if(visit[vertex]) continue;	//방문했던 정점(트리 구성요소)이라면 pass
			
			costSum += cost;
			visit[vertex] = true;
			for (int i = 0; i < adj[vertex].size(); i++) {	//최소로 선정된 정점에 연결된 간선 정보 추가 
				pq.add(adj[vertex].get(i));
			}
			
		}
	}
}
