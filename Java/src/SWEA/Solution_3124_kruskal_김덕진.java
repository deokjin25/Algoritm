package SWEA;

import java.io.*;
import java.util.*;

public class Solution_3124_kruskal_김덕진 {
	static int T, V, E, arr[][], parents[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			arr = new int[E][3];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
				arr[i][2] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr,(o1, o2) -> Integer.compare(o1[2], o2[2]));
			
			parents = new int[V+1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}
			
			long weightSum = 0;
			int cnt = 0;
			for (int i = 0; i < E; i++) {
				int from = arr[i][0];
				int to = arr[i][1];
				int weight = arr[i][2];
				
				if(union(from,to)) {
					weightSum += weight;
					cnt++;
				}
				if(cnt == V-1) break;
			}
			
			System.out.println("#" + tc + " " + weightSum);
			
		}
				
	}

	private static int findSet(int x) {
		if(x == parents[x]) {
			return x;
		}else {
			return parents[x] = findSet(parents[x]);
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) {
			return false;
		}else {
			parents[bRoot] = aRoot;
			return true;
		}
				
	}

}
