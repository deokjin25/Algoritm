package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_2887 {
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] planet = new int[N][4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			planet[i][0] = Integer.parseInt(st.nextToken());
			planet[i][1] = Integer.parseInt(st.nextToken());
			planet[i][2] = Integer.parseInt(st.nextToken());
			planet[i][3] = i;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		for (int i = 0; i < 3; i++) {
			final int dim = i;
			Arrays.sort(planet, (o1, o2) -> Integer.compare(o1[dim], o2[dim]));
			for (int j = 0; j < N-1; j++) {
				int turnnel = Math.abs(planet[j][dim] - planet[j+1][dim]);
				pq.offer(new int[] {planet[j][3], planet[j+1][3], turnnel});
			}
		}
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		int ans = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0];
			int to = curr[1];
			int cost = curr[2];
			
			if(union(from, to)) {
				ans += cost;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(ans);
	}
	private static boolean union(int from, int to) {
		int a = findSet(from);
		int b = findSet(to);
		
		if(a != b) {
			parents[b] = a;
			return true;
		}
		
		return false;
	}
	private static int findSet(int x) {
		if(parents[x] == x) return x;
		else return parents[x] = findSet(parents[x]);
	}

}
