package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_17471 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer>[] adj;
	static int N, parents[], p[], min;
	static boolean choice[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		p = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				adj[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		min = Integer.MAX_VALUE;
		choice = new boolean[N+1];
		select(1);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void select(int idx) {
		if(idx == N+1) {	//구역을 모두 나누었을 때(연결되어 있는지 확인 후 인구수 갱신)
			makeSet();
			
			//143(true는 true끼리)
			//256(false는 false끼리)
			
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < adj[i].size(); j++) {
					//둘 다 true거나
					if(choice[i] && choice[adj[i].get(j)]) {
						union(i, adj[i].get(j));
					//둘 다 false면
					}else if(!choice[i] && !choice[adj[i].get(j)]) {
						union(i, adj[i].get(j));
					}
				}
			}
			
			int cnt = 0;
			int t = 0;
			int f = 0;
			for (int i = 1; i <= N; i++) {
				if(parents[i] == i) cnt++;
				if(choice[i]) t += p[i];
				if(!choice[i]) f += p[i];
			}
			
			if(cnt == 2) {
				min = Math.min(min, Math.abs(t - f));
			}

			return;
		}
		
		
		choice[idx] = true;
		select(idx+1);
		choice[idx] = false;
		select(idx+1);
	}

	private static void union(int i, int j) {
		int a = findSet(i);
		int b = findSet(j);
		
		if(a != b) {
			parents[b] = a;
		}
	}

	private static int findSet(int x) {
		if(x == parents[x]) {
			return x;
		}else {
			return parents[x] = findSet(parents[x]);
		}
	}

	private static void makeSet() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

}
