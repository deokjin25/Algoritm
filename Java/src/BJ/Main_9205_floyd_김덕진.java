package BJ;

import java.io.*;
import java.util.*;

public class Main_9205_floyd_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t, n, location[][], dist[][];
	
	public static void main(String[] args) throws Exception {
		t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			n = Integer.parseInt(br.readLine());	//편의점 개수
			int total = n+2;
			location = new int[total][2];
			
			st = new StringTokenizer(br.readLine());
			location[0][0] = Integer.parseInt(st.nextToken());	//집 좌표 x
			location[0][1] = Integer.parseInt(st.nextToken());	//집 좌표 y
			
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				location[i][0] = Integer.parseInt(st.nextToken());	//편의점 좌표 x
				location[i][1] = Integer.parseInt(st.nextToken());	//편의점 좌표 y
			}
			
			st = new StringTokenizer(br.readLine());
			location[n+1][0] = Integer.parseInt(st.nextToken());	//페스티벌 좌표 x
			location[n+1][1] = Integer.parseInt(st.nextToken());	//페스티벌 좌표 y
			
			//거리 배열 적당히 큰 값으로 초기화
			int INF = 10000;
			dist = new int[total][total];
			for (int i = 0; i < n+2; i++) {
				Arrays.fill(dist[i], INF);
			}
			
			for (int i = 0; i < total; i++) {
				for (int j = i+1; j < total; j++) {
					if(isRange(location[i], location[j])) {
						dist[i][j] = 1;
						dist[j][i] = 1;
					}
				}
			}
			
			//플로이드 워셜(집에서 페스티벌장소로 가는 최단경로 추출)
			for (int k = 0; k < total; k++) {
				for (int i = 0; i < total; i++) {
					for (int j = 0; j < total; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			
			//집[0]에서 페스티벌[n+1]의 값이 갱신 되었으면 happy
			if(dist[0][n+1] != INF) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
			
		}
		
		
	}

	private static boolean isRange(int[] x, int[] y) {
		return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]) <= 1000;
	}
}
