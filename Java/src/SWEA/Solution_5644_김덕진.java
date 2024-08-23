package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5644_김덕진 {
	static int T, M, A, ap[][];
	static int[] personA;
	static int[] personB;
	static int[] A_position;
	static int[] B_position;
	static String[][] map;
	static int maxCharge;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	//이동 횟수
			A = Integer.parseInt(st.nextToken());	//무선 충전기 개수
			
			st = new StringTokenizer(br.readLine());
			personA = new int[M];	//A의 이동 커맨드 배열
			for (int i = 0; i < M; i++) {
				personA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			personB = new int[M];	//B의 이동 커맨드 배열
			for (int i = 0; i < M; i++) {
				personB[i] = Integer.parseInt(st.nextToken());
			}
			
			map = new String[10][10];	//전체 맵 배열("" << 빈칸으로 초기화)
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = "";
				}
			}
			
			ap = new int[A][4];		//충전배터리 정보 배열
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					ap[i][j] = Integer.parseInt(st.nextToken());
				}
				
				//[0]-1 : y, [1]-1: x, [2]: 충전거리, [3]: 충전량 
				int centerX = ap[i][1]-1;
				int centerY = ap[i][0]-1;
				int distance = ap[i][2];
				//i번째에 충전기 정보가 들어왔을 때 map에 바로 입력
				for (int x = centerX-distance; x <= centerX+distance; x++) {
					for (int y = centerY-distance; y <= centerY+distance; y++) {
						if(x<0 || x>9 || y<0 || y>9) continue;		//전체 맵에서 벗어나는 영역이면 pass
						if(Math.abs(x-centerX) + Math.abs(y-centerY) <= distance) {		//충전 거리 내에 있으면
							map[x][y]+=String.valueOf(i);	//문자열로 관리. ex) 1번 2번 충전기가 같은 영역에 있다면 "12"로 저장(순서는 중요하지 않음.)
						}
					}
				}
			}
				
			maxCharge = 0;
			A_position = new int[]{0,0};	//초기위치 세팅
			B_position = new int[]{9,9};	//초기위치 세팅
			simulation();
			System.out.println("#"+tc+" "+maxCharge);
			
		}
	}
	
	private static void simulation() {
		for (int i = 0; i <= M; i++) {
			int ax = A_position[0]; int ay = A_position[1];		//0번 인덱스가 x(행), 1번 인덱스가 y(열)
			int bx = B_position[0]; int by = B_position[1];
			int charge = 0; int tmp = 0;	//실제 충전 할 값(charge)과 충전기별로 비교해서 최대값을 찾아줄 tmp 변수. (한 순간의 충전량)
			String bcA = map[ax][ay];	//A가 서있는 충전기 번호(하나가 아닐 수도 있음)
			String bcB = map[bx][by];	//B가 서있는 충전기 번호(하나가 아닐 수도 있음)
			
			if(bcA.length() > 0 && bcB.length() > 0) {		//둘다 충전기 영역 위에 있는 경우
				//어떤 경우가 최대인지, 둘이 겹치는 곳에 있는지 하나하나 따져 볼 수도 있지만 그냥 모든 조합 살피기 
				for (int j = 0; j < bcA.length(); j++) {	//a의 충전기 번호개수만큼
					for (int k = 0; k < bcB.length(); k++) {	//b의 충전기 번호개수 만큼
						int a = Integer.parseInt(bcA.charAt(j)+"");		//a에 해당하는 충전기 번호 중 하나 고르기
						int b = Integer.parseInt(bcB.charAt(k)+"");		//b에 해당하는 충전기 번호 중 하나 고르기
						
						if(a==b) {	//a랑 b의 충전기를 같게 둔 경우 혹은 겹치는 영역이 아니라 그냥 둘다 하나의 영역 안에 있는 경우.
							tmp = ap[a][3];	//어차피 둘로 나누나 한명걸로 하나 똑같음
						}else {		//다르게 할 경우 따로 하는게 무조건 이득.
							tmp = ap[a][3] + ap[b][3];							
						}
						charge = Math.max(charge, tmp);		//이전 값과 비교해서 최대한 큰 값으로 저장
					}
				}
				maxCharge += charge;	//최종 출력 할 max에 합산.
			}else if(bcA.length() > 0) {	//a만 충전 영역에 있는 경우
				//a가 중복된 영역에 있을 수 있으니 최대값 비교 저장은 해줘야 함.
				for (int j = 0; j < bcA.length(); j++) {
					int a = Integer.parseInt(bcA.charAt(j)+"");
					tmp = ap[a][3];
					charge = Math.max(tmp, charge);
				}
				maxCharge += charge;
			}else if(bcB.length() > 0) {	//b만 충전 영역에 있는 경우
				//위랑 같음
				for (int j = 0; j < bcB.length(); j++) {
					int b = Integer.parseInt(bcB.charAt(j)+"");
					tmp = ap[b][3];
					charge = Math.max(tmp, charge);
				}
				maxCharge += charge;
			}
			
			if(i==M) break;	//마지막 이동 후 충전까지 끝냈다면 end.
			
			//초기 위치에서도 충전이 가능하면 해야하기 때문에 움직이는 걸 나중에.
			moveA(personA[i]);
			moveB(personB[i]);
		}
	}

	//0:이동하지 않음, 1:상, 2:우, 3:하, 4:좌
	private static void moveB(int command) {
		if(command==1) B_position[0]--;
		if(command==2) B_position[1]++;
		if(command==3) B_position[0]++;
		if(command==4) B_position[1]--;
	}
	
	//0:이동하지 않음, 1:상, 2:우, 3:하, 4:좌
	private static void moveA(int command) {
		if(command==1) A_position[0]--;
		if(command==2) A_position[1]++;
		if(command==3) A_position[0]++;
		if(command==4) A_position[1]--;
	}

}
