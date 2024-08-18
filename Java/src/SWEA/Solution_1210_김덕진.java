package SWEA;

import java.io.*;

public class Solution_1210_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String[][] ladder = new String[100][100];
	
	//도착지에서 부터 역으로 올라가서 출발지를 찾자.
	public static void main(String[] args) throws Exception {
		//테스트 케이스 10개라고 지정해줌.
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();	//입력값에 번호 적혀있어서 버림.
			for (int i = 0; i < 100; i++) {		//사다리 그래프 입력
				ladder[i] = br.readLine().split(" ");
			}
			
			int y = 0;	//도착지 인덱스이자 사다리 배열에 사용될 y인덱스
			for (int i = 0; i < 100; i++) {
				if (ladder[99][i].equals("2")) {
					y = i;
				}
			}
			
			int side[] = {-1,1};	//좌우에 연결된 사다리가 있는지 봐줘야 하기 때문에 만든 방향 배열
			
			int x = 99;
			while(true) {
				for (int i = 0; i < 2; i++) {	//위로 올라가기 전에 양 옆에 사다리가 연결되어 있는지 확인
					if(y+side[i] > 0 && y+side[i] < 100 && ladder[x][y+side[i]].equals("1")) {	//연결되어 있다면
						while(true) {	//끊길 때까지 계속 그 방향으로 진행
							if(y+side[i] < 0 || y+side[i] >= 100 || ladder[x][y+side[i]].equals("0")) break;	//더 이상 갈 곳이 없다면 종료
							y += side[i];
						}
						break;
					}
				}
				x-=1;	//1인지 확인 할 것도 없이 그냥 계속 위로 가도 됨
				if(x== -1) break;	//제일 위로 올라왔으면 종료.
			}
			
			System.out.println("#"+tc+" "+y);
		}
		
	}

}
