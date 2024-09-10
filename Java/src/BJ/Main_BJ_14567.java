package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_14567 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[], ans[];
	static List<Integer>[] adj;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		arr = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			arr[to]++;
		}
		
		ans = new int[N+1];
		topologicalSort();
		for (int i = 1; i <= N; i++) {
			System.out.printf(ans[i] + " ");
		}
		
	}

	private static void topologicalSort() {
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 1; i < N+1; i++) {
			if(arr[i] == 0) q.offer(new int[] {i, 1});
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int from = now[0];
			int value = now[1];
			ans[from] = value;
			for (int i = 0; i < adj[from].size(); i++) {
				int to = adj[from].get(i);
				if(--arr[to] == 0) {
					q.offer(new int[] {to, value+1});
				}
			}
			
			
			
		}
		
		
	}

}
