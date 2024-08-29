package BJ;

import java.io.*;
import java.util.*;

public class Main_15686_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int map[][], arr[], chickenNum, N, M, min, chickenArr[][];
	static List<int[]> ChickenList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		chickenNum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chickenNum++;	//입력으로 들어온 치킨집 총 개수
					ChickenList.add(new int[] {i,j});	//위치 기억해두기
					map[i][j] = 0;		//치킨 집 위치 설정은 나중에
				}
			}
		}
		
		
		arr = new int[M];
		min = Integer.MAX_VALUE;
		chickenArr = new int[M][2];
		comb(0,0);	//치킨집 총 개수 중에서 누굴 살릴지 조합 찾기
		System.out.println(min);

	}

	private static void comb(int idx, int cnt) {
		if(idx == M) {		//살릴 치킨집 인덱스 조합
			//살릴 치킨 위치 배열 생성
			for (int i = 0; i < M; i++) {
				int[] xy = ChickenList.get(arr[i]);
				int x = xy[0];
				int y = xy[1];
				
				chickenArr[i][0] = x;
				chickenArr[i][1] = y;
				
				//살릴 치킨집 map에 찍어주기
				map[x][y] = 2;
			}
			
			//치킨집 표시 후 거리 계산하기
			chickDistance();
			
			//거리 계산 후 다른 조합에서의 경우도 따져봐야 하니까 원복시키기
			for (int i = 0; i < M; i++) {
				int[] xy = ChickenList.get(arr[i]);
				int x = xy[0];
				int y = xy[1];
				map[x][y] = 0;
			}
			
			return;
		}
		
		
		for (int i = cnt; i < chickenNum; i++) {
			arr[idx] = i;
			comb(idx+1, i+1);
		}
		
	}

	private static void chickDistance() {
		//1 위치마다 치킨집 리스트 돌면서 최소 거리 누적
		int dSum = 0;	//현재 조합에서 치킨집 까지의 (최소)누적 거리
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					int tmp = Integer.MAX_VALUE;	//각 치킨집마다의 거리 비교를 위한 변수
					for (int k = 0; k < M; k++) {
						int x = chickenArr[k][0];
						int y = chickenArr[k][1];
						int d = Math.abs(i - x) + Math.abs(j - y);
						if(tmp > d) {	//이전 치킨집 거리보다 현재 거리가 더 가깝다면 
							tmp = d;	//거리 갱신
						}
					}
					dSum += tmp;	//최종 갱신된 치킨집까지의 거리 누적
				}
			}
		}
		
		//모든 탐색 종료 후 누적 거리가 찐최종 거리보다 작은지 갱신
		min = Math.min(dSum, min);		
	}

	
	
	
	
	
	
	
	
	
}
