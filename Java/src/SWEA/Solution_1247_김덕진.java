package SWEA;

import java.util.Scanner;

public class Solution_1247_김덕진 {
	static int T,N, min, d;
	static int[][] coordinate;
	static boolean check[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			coordinate = new int[N+2][2];
			for (int i = 0; i < N+2; i++) {
				coordinate[i][0] = sc.nextInt();
				coordinate[i][1] = sc.nextInt();
			}
			
			min = Integer.MAX_VALUE;
			check = new boolean[N+2];
			perm(0,coordinate[0][0],coordinate[0][1],0);
			System.out.println("#"+tc+" "+min);
			
		}
	}

	private static void perm(int idx, int x1, int y1, int distanceSum) {
		if(idx == N) {	//모든 고객 동선 정했을 때
			
			//집으로 가는 거리까지 더해주기
			int x2 = coordinate[1][0];
			int y2 = coordinate[1][1];
			
			int distance = Math.abs(x1-x2) + Math.abs(y1- y2);

			distanceSum += distance;
			
			min = Math.min(distanceSum, min);
			return;
		}
		
			
		//고객이 coordinate 배열에 2부터 N개 들어가 있음 
		for (int i = 2; i < 2+N; i++) {
			if(check[i]) continue;	//사용했던(방문했던) 숫자(고객)이면 pass
			int x2 = coordinate[i][0];
			int y2 = coordinate[i][1];
			
			int distance = Math.abs(x1-x2) + Math.abs(y1- y2);
			
			// 계산되어 있는 min 값보다 크면 더이상 재귀 들어가볼 필요가 없음
			if(distanceSum + distance >= min) continue;
			
			check[i] = true;
			perm(idx+1, x2, y2, distanceSum+distance);
			check[i] = false;

		}
		
	}

}
