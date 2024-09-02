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
		
		visit = new boolean[V+1];
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
		
		for (int i = 0; i < adj[start].size(); i++) {	//시작 정점과 연결된 간선 정보 삽입
			arr[adj[start].get(i)[0]] = adj[start].get(i)[1];
			pq.offer(adj[start].get(i));
		}
		visit[start] = true;
		
		while(!pq.isEmpty()) {
			int[] Edge = pq.poll();
			int Vertex = Edge[0];
			int weight = arr[Vertex];
			
			if(visit[Vertex]) continue;
			
			visit[Vertex] = true;
			for (int i = 0; i < adj[Vertex].size(); i++) {
				int[] nextEdge = adj[Vertex].get(i);
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
