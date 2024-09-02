package SWEA;

import java.io.*;
import java.util.*;

public class Solution_2115_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, C, T, map[][], maxSum, work1Sum, work2Sum;
	static List<Integer> work1, work2;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//맵 크기
			M = Integer.parseInt(st.nextToken());	//선택할 벌통 개수
			C = Integer.parseInt(st.nextToken());	//채취할 수 있는 꿀의 양
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			work1 = new ArrayList<>();
			work2 = new ArrayList<>();
			maxSum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {	//A 작업자의 벌꿀 채취 시작점
					if(j+M > N) break;
					for (int k = i; k < N; k++) {	//B 작업자의 벌꿀 채취 치작점
						int m = 0;
						if(k==i) m = j+M;	//같은 줄일 때만 범위 값 따로 주고 나머지는 다 0에서 전부 탐색 
						for (int p = m; p < N; p++) {
							if(p+M > N) break;
							
							work1.clear();	// 채취할 벌꿀 리스트
							work2.clear();
							work1Sum = 0;	// 조건에 맞게 선택된 꿀의 총량
							work2Sum = 0;
							
							for (int w = j; w < j+M; w++) {	//M만큼 리스트에 넣어주기(1번 작업자는 j부터)
								work1.add(map[i][w]);
							}
							
							for (int w = p; w < p+M; w++) { //M만큼 리스트에 넣어주기(1번 작업자는 p부터)
								work2.add(map[k][w]);
							}
							
							subSet1(1, work1.get(0), Math.pow(work1.get(0), 2));	//벌꿀통 중 하나에 대해서 채취하는 경우
							subSet1(1, 0, 0);				//하지 않는 경우
							
							subSet2(1, work2.get(0), Math.pow(work2.get(0), 2));
							subSet2(1, 0, 0);
							
							maxSum = Math.max(maxSum, work1Sum+work2Sum);
						}
					}
					
					
				}
				
			}
			
			System.out.println("#" + tc + " " + maxSum);
			
		}
		
	}

	private static void subSet2(int idx, Integer honey, double sum) {
		if(honey > C) {	//허용된 총량보다 많으면 종료
			return;
		}
		
		if(idx == work2.size() || honey == C) {		//모든 부분집합의 선택이 끝났거나 꿀이 최대치에 도달한 경우
			work2Sum = (int) Math.max(sum, work2Sum);	//최대 꿀 갱신
			return;
		}
		
		subSet2(idx+1, honey + work2.get(idx), sum + Math.pow(work2.get(idx), 2));
		subSet2(idx+1, honey, sum);
	}
	

	private static void subSet1(int idx, Integer honey, double sum) {
		if(honey > C) {
			return;
		}
		
		if(idx == work1.size() || honey == C) {
			work1Sum = (int) Math.max(sum, work1Sum);
			return;
		}
		
		subSet1(idx+1, honey + work1.get(idx), sum + Math.pow(work1.get(idx), 2));
		subSet1(idx+1, honey, sum);
	}

}
