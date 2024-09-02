import java.io.*;
import java.util.*;

class Solution {
	static int N, T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map = new int[500][500];
	
	public static void main(String args[]) throws Exception { 
		 
		 //test_case 10개
		for (int tc = 1; tc <= 10; tc++) {
			
			N = Integer.parseInt(br.readLine());
			 
			 
			 //초기 입력
			 for (int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			 
			//첫 셀에 있는 벽돌만 하강
			 Down();
			 
			 
			 

			 Right();
		
			 
			 int cntB = 0;
			 int cntR = 0;
			 for (int i = 0; i < N; i++) {
				 if(map[N-1][i] > 0) cntB++;
				 if(map[i][N-1] > 0) cntR++;
			}
			 System.out.println("#" + tc + " " + cntB + " " + cntR);

		}
		
	}
	
	
	private static void Down() {
		//첫 행만 탐색
		for (int i = 0; i < N; i++) {
			if(map[0][i] > 0 && map[1][i] == 0) {	//밑에 뭐가 있어야 하강 가능
				brick(0,i, 1, 1);
			}
		}
	}
	
	private static void Right() {
		for (int i = 0; i < N; i++) {
			if(map[i][0] > 0 && map[i][1] == 0) {
				brick2(i, 0, 1, 1);
			}
		}
		
		
	}
	
	
	
	//우하강
	private static void brick2(int x, int y, double m, int brickN) {
		while(true) {
			if(y+1 >= N) break;
			if(map[x][y+1] > 0) {	//하강하다가 벽돌을 만났을 때
				int cnt = 0;	//밑에 몇개가 있는지 세주기
				
				int re_y = 0;	//제일 밑에 있던 벽돌의 위치
				for (int i = y+1; i < N; i++) {
					if(map[x][i] == 0 || i >= N) break;
					if (map[x][i] > 0) cnt++;
					re_y = i;
				}
				
				if(m > cnt) {	//밑에 있는 무게보다 하강 무게가 더 크다면
					//x 제일 밑 벽돌로 갱신 필요!!
					brick2(x, re_y, m+cnt, brickN+cnt);	//무게와 위치 갱신해서 다시 하강 시작
					break; //해당 매서드는 종료
				}else {	//밑에 있는 무게가 더 크면
					break;	//그대로 종료
				}
			}else {		//밑에 벽돌이 없을 때
				m = m * 1.9;	//무게는 계속 1.9씩 증가
				
				//여러개가 움직이고 있을 경우가 있다.
				for (int i = y; i > y-brickN; i--) {
					map[x][i+1] = map[x][i];
					map[x][i] = 0;
				}
				
				y++;	//제일 하단 벽돌 위치 갱신
//				System.out.println(x);
			}

		}
		
	}

	

	private static void brick(int x, int y, double m, int brickN) {
		while(true) {
			if(x+1 >= N) break;
			if(map[x+1][y] > 0) {	//하강하다가 벽돌을 만났을 때
				int cnt = 0;	//밑에 몇개가 있는지 세주기
				
				int re_x = 0;	//제일 밑에 있던 벽돌의 위치
				for (int i = x+1; i < N; i++) {
					if(map[i][y] == 0 || i >= N) break;
					if (map[i][y] > 0) cnt++;
					re_x = i;
				}
				
				if(m > cnt) {	//밑에 있는 무게보다 하강 무게가 더 크다면
					//x 제일 밑 벽돌로 갱신 필요!!
					brick(re_x, y, m+cnt, brickN+cnt);	//무게와 위치 갱신해서 다시 하강 시작
					break; //해당 매서드는 종료
				}else {	//밑에 있는 무게가 더 크면
					break;	//그대로 종료
				}
			}else {		//밑에 벽돌이 없을 때
				m = m * 1.9;	//무게는 계속 1.9씩 증가
				
				//여러개가 움직이고 있을 경우가 있다.
				for (int i = x; i > x-brickN; i--) {
					map[i+1][y] = map[i][y];
					map[i][y] = 0;
				}
				
				x++;	//제일 하단 벽돌 위치 갱신
//				System.out.println(x);
			}

			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}