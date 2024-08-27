package SWEA;

import java.io.*;
import java.util.*;

public class Solution_2382_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T,N,M,K, micro[][], map[][][];
	static boolean[] consume;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//셀의 개수(한 변의 길이)
			M = Integer.parseInt(st.nextToken());	//격리 시간
			K = Integer.parseInt(st.nextToken());	//미생물 군집의 개수
			
			map = new int[N][N][2];
			micro = new int[K][4];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					micro[i][j] = Integer.parseInt(st.nextToken());
				}
				map[micro[i][0]][micro[i][1]][0] = micro[i][2];		//2차원 배열 한칸에(0인덱스) 미생물 수.
				map[micro[i][0]][micro[i][1]][1] = i;				//1인덱스는 미생물 번호
			}
			
			consume = new boolean[K];	//흡수 여부 배열
			simulation();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf(map[i][j][0]+" ");
				}
				System.out.println();
			}
			
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt += map[i][j][0];
				}
			}
			System.out.println("#" + tc + " " + cnt);
			
		}
	}
	//(상: 1, 하: 2, 좌: 3, 우: 4)
	private static void simulation() {
		for (int time = 0; time < M; time++) {
			for (int i = 0; i < K; i++) {
				if(consume[i]) continue;	//흡수 당한 미생물이면 pass
				int x = micro[i][0];	//가로, 행
				int y = micro[i][1];	//세로, 열
				int n = map[x][y][0];	//미생물 수
				int d = micro[i][3];	//이동 방향
				
				//이동방향 조정.
				if(d == 1) x--;	//상
				if(d == 2) x++;	//하
				if(d == 3) y--; //좌
				if(d == 4) y++;	//우
				
				if(x==-1 || y==-1) {
					System.out.println(x+ " " +y);
				}
				
				if(x==0 || x==N-1 || y==0 || y==N-1) { 	//가려는 이동 방향이 빨간 약품, 가장자리일 경우
					n = n/2;	//미생물 수는 반으로.
					//이동방향 반대로 조정.
					if(d==1) d=2;
					else if(d==2) d=1;
					else if(d==3) d=4;
					else if(d==4) d=3;
				}
				
				if(map[x][y][0] > 0) {		//가려는 이동 방향에 미생물이 있는 경우
					if(n > map[x][y][0]) {	//현재 미생물의 크기가 더 크다면
						n+=map[x][y][0];	//흡수만 진행
						consume[map[x][y][1]] = true;	//흡수당한 미생물 흡수 true 처리
					}else {				//더 작다면(이동 방향에 있는 미생물이 더 크다면)
						map[x][y][0] += n;	//이동 방향에 있는 미생물 수에 현재 미생물 더해주고
						map[micro[i][0]][micro[i][1]][0] = 0;	//원래 있었던 자리는 없던 것처럼.
						consume[i] = true;	//지금 미생물 흡수 처리
					}
				}
				
				if(!consume[i]) {	//이동과정 중에 흡수 처리가 되지 않았다면
					map[x][y][0] = n;	//나아가려고 하는 방향에 미생물 표시해주고
					map[x][y][1] = i;	//미생물 번호까지.
					map[micro[i][0]][micro[i][1]][0] = 0;	//원래 있었던 자리는 없던 것처럼.
					micro[i][0] = x;
					micro[i][1] = y;
					micro[i][2] = n;
					micro[i][3] = d;
				}
				
			}

		}
	}

}
