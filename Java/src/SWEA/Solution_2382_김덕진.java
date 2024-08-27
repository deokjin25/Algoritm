package SWEA;

import java.io.*;
import java.util.*;
/*
 * 첫번째, map 배열에 모인 군집 번호를 String으로 더해줘서 관리.
 * >> 군집 번호가 2자리가 넘어가면 배열 이동 관리도 어렵고 합치는 것도 어렵고, 합쳤을 때 번호들이 난잡해져서 관리가 안됨.
 * 두번째, map 배열에 미생물 배열을 집어넣은 3차원으로 시도
 * >> 제한시간초과 발생(모든 i,j에 대해서 미생물 배열이 몇 개인지 K번 만큼을 매번 순회해서 인듯하다.)
 * 세번째, map에는 군집 수만 기록하고 미생물 배열의 x,y를 가져와서 map에 1 이상인 x,y좌표랑 비교대조 해보자.
 * >> 성공
 */
public class Solution_2382_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T,N,M,K, micro[][], map[][];
	static boolean[] consume;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//셀의 개수(한 변의 길이)
			M = Integer.parseInt(st.nextToken());	//격리 시간
			K = Integer.parseInt(st.nextToken());	//미생물 군집의 개수
			
			map = new int[N][N];
			micro = new int[K][4];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					micro[i][j] = Integer.parseInt(st.nextToken());
				}
				map[micro[i][0]][micro[i][1]]++;
			}
			
			consume = new boolean[K];	//미생물 흡수 여부 판별 배열
			simulation();
			int cnt = 0;
			for (int i = 0; i < K; i++) {
				if(consume[i]) continue;	//흡수된 놈들은 제외
				cnt += micro[i][2];			//살아남은 미생물의 수 합산
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		System.out.println(sb);
	}
	
	
	private static void simulation() {
		for (int time = 0; time < M; time++) {
			
			//1시간동안의 모든 미생물 이동
			for (int i = 0; i < K; i++) {
				if(consume[i]) continue;	//흡수 당한 미생물은 pass
				int x = micro[i][0];
				int y = micro[i][1];
				int n = micro[i][2];
				int d = micro[i][3];
				
				//(상: 1, 하: 2, 좌: 3, 우: 4)
				if(d==1) x--;
				if(d==2) x++;
				if(d==3) y--;
				if(d==4) y++;
				
				if(x==0 || x==N-1 || y==0 || y==N-1) {	//이동하는 곳이 빨간 약품, 가장자리에 닿았을 경우
					n /= 2;		//미생물 수 반토박
					
					//방향 바꿔주기
					d = (d == 1) ? 2 : (d == 2) ? 1 : (d == 3) ? 4 : 3;
				}
				
				//map에 위치한 미생물 위치 전환
				map[x][y]++;
				map[micro[i][0]][micro[i][1]]--;
				
				//바뀐 값 다시 넣어주기
				micro[i][0] = x;
				micro[i][1] = y;
				micro[i][2] = n;
				micro[i][3] = d;
			}
			
			//모든 미생물들의 이동을 마친 뒤 흡수여부 판별
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 1) {		//맵에 모여있는 군집이 1개 이상, 여러 미생물이 모여있다면
						int sum = 0;	//합산 해줄 변수
						int big = -1;	//미생물 수(비교 변수)
						int bigIdx = -1;	//제일 큰 군집 인덱스
						
						for (int k = 0; k < K; k++) {	//군집 배열 돌아보면서 1개 이상인 위치에 놓인 미생물 찾기
							int x = micro[k][0];
							int y = micro[k][1];
							int n = micro[k][2];
							if(x!=i || y!=j || consume[k]) continue;	//위치가 다르거나 이미 먹힌 애들이면 pass
							sum += n;
							if(big < n) {	//탐색중인 것 보다 현재값이 더 크면
								big = n;
								if(bigIdx != -1) consume[bigIdx] = true;	//직전 최대 흡수 처리
								bigIdx = k;
							}else {
								consume[k] = true;	//최대보다 작은 미생물 흡수 처리.
							}
						}
						
						micro[bigIdx][2] = sum;		//제일 큰 놈한테 미생물 합산 값 넘김.
						map[i][j] = 1;	//몇개가 있었는지는 모르겠지만 하나만 남김
					}
				}
			}			
		}
		
	}

}
