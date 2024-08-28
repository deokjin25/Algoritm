package BJ;

import java.io.*;
import java.util.*;
/*
 * 한 정점에서 시작해서 4 간선을 지나서도 정점이 있는지 확인하는 문제
 */
public class Main_13023_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Integer>[] adj;
	static boolean visit[], flag;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//사람 수
		M = Integer.parseInt(st.nextToken());	//사람 관계 수
		
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
		}
		
		visit = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			abcde(i, 0);
			if(flag) break;		//목적 달성 했으면 다른 거 볼 필요 없음
		}
		
		System.out.println(flag ? 1 : 0);
	}

	private static void abcde(int from, int depth) {
		if(depth == 4) {	//a -> b -> c -> d -> e 관계 달성
			flag = true;
			return;
		}
		
		visit[from] = true;
		
		for (int node : adj[from]) {
			if(visit[node]) continue;
			abcde(node, depth+1);
			if(flag) return;	//마지막 까지 갔으면 더이상 볼 필요 없음.
		}
		
		visit[from] = false;	//다른 노드에서 접근했을 땐 가능할 수도 있으니까 원복 처리
		
	}


	
	
	

}
