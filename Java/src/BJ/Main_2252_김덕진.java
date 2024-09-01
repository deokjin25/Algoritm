package BJ;

import java.io.*;
import java.util.*;
/*
 * 앞에 서 있는 애가 나와야(출력되어야) 뒤에 서 있는 애가 나올 수 있다.(위상정렬)
 */
public class Main_2252_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, degree[];
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		degree = new int[N+1]; //인덱스별 진입차수 설정 배열(1번 부터 관리)
		
		graph = new ArrayList[N+1];	//간선 정보 저장 배열
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			degree[to]++;
		}
		
		topology_sort();
		System.out.println(sb);
	}

	private static void topology_sort() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if(degree[i] == 0) {	//처음부터 간선이 없는 정점들 모두 큐에 삽입
				sb.append(i + " ");
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int x = q.poll();	//간선이 없는(없어진) 정점
			for (int i = 0; i < graph[x].size(); i++) {	//x와 이어져 있는 간선들 모두 -1
				int vertex = graph[x].get(i);	//x와 이어진 정점
				degree[vertex]--;
				if(degree[vertex] == 0) {	//간선 개수 줄이다가 0이 되었으면
					sb.append(vertex + " ");
					q.offer(vertex);	//q에 삽입
				}
			}
		}
		
	}
}
