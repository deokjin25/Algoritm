package BJ;

import java.util.*;
import java.io.*;

public class Main_BJ_1939 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] adj = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj[from].add(new int[] {from, to, weight});
			adj[to].add(new int[] {to, from, weight});
		}

		
		st = new StringTokenizer(br.readLine());
		int f1 = Integer.parseInt(st.nextToken());
		int f2 = Integer.parseInt(st.nextToken());
		
		
//		int[][] vtov = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(vtov[i], Integer.MIN_VALUE);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2[1], o1[1]));
		for (int[] cur : adj[f1]) {
			pq.add(cur);
		}
		
//		for (int[] cur : adj[f2]) {
//			pq.add(cur);
//		}
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0];
			int to = cur[1];
			int weight = cur[2];
			
			if(vtov[from][to] != Integer.MIN_VALUE) {
				
			}else {
				vtov[from][to] = weight;
				
			}
			
			
		}
		
		System.out.println(Math.max(vtov[f1][f2], vtov[f2][f1]));
	}
}
