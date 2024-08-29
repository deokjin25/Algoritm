package BJ;

import java.io.*;
import java.util.*;

public class Main_1922_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, parents[], arr[][];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		
		int weightSum = 0;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			int from = arr[i][0];
			int to = arr[i][1];
			int weight = arr[i][2];
			
			if(union(from,to)) {
				cnt++;
				weightSum += weight;
			}
			if(cnt == N-1) break;
			
		}
		
		System.out.println(weightSum);
		
	}

	private static int findSet(int x) {
		if(parents[x] == x) {
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
			parents[aRoot] = bRoot;
			return true;
		}
		
	}

}
