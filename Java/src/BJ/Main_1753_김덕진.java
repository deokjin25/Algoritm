package BJ;

import java.io.*;
import java.util.*;

public class Main_1753_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, arr[];
	static boolean visit[];
	static List<int[]>[] adj;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	//정점 수
		E = Integer.parseInt(st.nextToken());	//간선 수
		
		int start = Integer.parseInt(br.readLine());	//시작 정점
		
		adj = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj[from].add(new int[] {to,weight});
		}
		
//		visit = new boolean[V+1];
		arr = new int[V+1];	//시작 정점과 연결된 정점까지의 비용 관리 배열
		for (int i = 1; i <= V; i++) {
			arr[i] = Integer.MAX_VALUE;	//최대 정수로 초기화
		}
		arr[start] = 0;		//본인은 0
		Dijkstra(start);
		for (int i = 1; i <= V; i++) {
			if(arr[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(arr[i]);
			}
		}
	}

	private static void Dijkstra(int start) {
		PriorityQueue<int[]> pq  = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
//		Queue<int[]> pq = new LinkedList<>();
		
		pq.offer(new int[]{start, 0}); // 시작 정점을 큐에 추가
//		visit[start] = true;
		
		while(!pq.isEmpty()) {
			int[] Edge = pq.poll();
			int Vertex = Edge[0];
			int weight = Edge[1];
			
			if (weight > arr[Vertex]) continue;	//이미 갱신처리 되어서 더 짧아진 거리인 경우 pass함
//			if(visit[Vertex]) continue;	//방문 처리하면 더 짧은 거리 만났을 때 갱신이 안됨
			
//			visit[Vertex] = true;
			for (int[] nextEdge : adj[Vertex]) {
				int nextVertex =  nextEdge[0];
				int nextWeight = nextEdge[1];
				
				if(arr[nextVertex] > nextWeight + weight) {
					arr[nextVertex] = nextWeight + weight;
					pq.offer(new int[] {nextVertex, arr[nextVertex]});
				}
			}
			
			
		}
		
	}

}
