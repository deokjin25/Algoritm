import java.io.*;
import java.util.*;

public class DijkstraTest {
	
	static class Node{
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		
		
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
			
			System.out.println(getMinDistance(adjList, start, end));
		}
	}
	
	static int getMinDistance(Node[] adjList, int start, int end) {
		final int V = adjList.length;
		int[] minDistance = new int[V]; //시작 정점에서 각 정점으로의 최소거리
		boolean[] visited = new boolean[V];	//방문 정점 관리
		Arrays.fill(minDistance, Integer.MAX_VALUE);	//거리 최대치 초기화
		minDistance[start] = 0;
		
		for (int i = 0; i < V; i++) {
			//step1: 미방문 정점 중 시작 정점에서 가장 가까운 정점(stopOver) 선택
			int min = Integer.MAX_VALUE;
			int stopOver = -1;
			for (int j = 0; j < V; j++) {
				if(!visited[j] && minDistance[j] < min) {
					min = minDistance[j];
					stopOver = j;
				}
			}
			
//			if(stopOver == end) break;
			if(stopOver == -1) break;
			visited[stopOver] = true;
			
			//step2: 선택된 정점(stopOver)을 경유해서 인접한 미방문 정점으로의 최소비용을 갱신할 수 있는지 체크
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] && minDistance[temp.vertex] > min + temp.weight) {
					minDistance[temp.vertex] = min + temp.weight;
				}
			}
		}
		
		System.out.println(Arrays.toString(minDistance));
		return minDistance[end] != Integer.MAX_VALUE ? minDistance[end] : -1;
	}
	
	

}
