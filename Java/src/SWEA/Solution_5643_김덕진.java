package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5643_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, M, arr[], know[][];
	static List<Integer>[] adj;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			adj = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>(); 
			}
			
			arr = new int[N+1];
			know = new int[N+1][2];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				arr[to] ++;
				adj[from].add(to);
				
				know[from][1]++;	//[0]: 온 애들(나보다 작은 애들), [1]: 내가 보내준 애들(나보다 큰 애들)
				if(know[from][0] + 1 > know[to][0]) {
					
				}
				know[to][0] = Math.max(know[to][0], know[from][0] + 1);
			}
			
			bfs();
			for (int i = 1; i <= N; i++) {
				System.out.println(Arrays.toString(know[i]));				
			}
		}
		
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if(arr[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int from = q.poll();
			System.out.println(from);
			for (int to : adj[from]) {
				
				if(--arr[to] == 0) {
					q.offer(to);					
				}
			}
			
			
		}
		
		
	}

}
