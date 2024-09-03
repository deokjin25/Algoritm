package SWEA;

import java.io.*;
import java.util.*;

public class Solution_2117_김덕진 {
	static int T, N, M, map[][], maxHome;
	static List<int[]> homes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//맵 크기
			M = Integer.parseInt(st.nextToken());	//한 집당 낼 수 있는 금액
			
			homes = new ArrayList<int[]>();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) homes.add(new int[] {i,j});	//집 위치 기억 및 size로 전체 집 개수 파악
				}
			}
			
			maxHome = Integer.MIN_VALUE;
			for (int i = 1; i <= 21; i++) {
				service(i);		//반복문 돌면서 서비스 영역 키워보기
			}
			System.out.println("#" + tc + " " + maxHome);
		}
		
	}

	private static void service(int area) {
		int p = area*area + (area-1) * (area-1);	//서비스 운영 비용
		
		if(p > homes.size() * M) return;	//모든 집이 비용을 내도 서비스 운영비용이 손해라면 그냥 리턴
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {	//서비스 운영 중심점
				int innerHome = 0;	//서비스 거리 내에 있는 집들의 개수
				//서비스 중심과 집들의 위치 비교하면서 거리내에 있는지 파악
				for (int k = 0; k < homes.size(); k++) {
					int[] home = homes.get(k);
					int x = home[0];
					int y = home[1];
					
					int d = Math.abs(x - i) + Math.abs(y - j);	//서비스 중심점과 집 사이의 거리 -> 마름모
					if(d <= area-1) innerHome++;
				}
				
				if(p <= innerHome*M) {	//손해가 아니라면(차액이 0 이상이라면)
					maxHome = Math.max(maxHome, innerHome);
				}
				
			}
		}

	}

}
