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
			personA = new int[M];
			for (int i = 0; i < M; i++) {
				personA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			personB = new int[M];
			for (int i = 0; i < M; i++) {
				personB[i] = Integer.parseInt(st.nextToken());
			}
			
			map = new String[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = "";
				}
			}
			
			ap = new int[A][4];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					ap[i][j] = Integer.parseInt(st.nextToken());
				}
				
				//[0]-1 : y, [1]-1: x, [2]: 충전거리, [3]: 충전량 
				int centerX = ap[i][1]-1;
				int centerY = ap[i][0]-1;
				int distance = ap[i][2];
				
				for (int x = centerX-distance; x <= centerX+distance; x++) {
					for (int y = centerY-distance; y <= centerY+distance; y++) {
						if(x<0 || x>9 || y<0 || y>9) continue;
						if(Math.abs(x-centerX) + Math.abs(y-centerY) <= distance) {
							map[x][y]+=String.valueOf(i);
						}
					}
				}
			}
				
			maxCharge = 0;
			A_position = new int[]{0,0};
			B_position = new int[]{9,9};
			simulation();
			System.out.println("#"+tc+" "+maxCharge);
			
		}
	}
	
	private static void simulation() {
		for (int i = 0; i <= M; i++) {
			int ax = A_position[0]; int ay = A_position[1];
			int bx = B_position[0]; int by = B_position[1];
			int charge = 0; int tmp = 0;
			String bcA = map[ax][ay];	//A가 서있는 충전기 번호
			String bcB = map[bx][by];	//B가 서있는 충전기 번호
			
			if(bcA.length() > 0 && bcB.length() > 0) {
				for (int j = 0; j < bcA.length(); j++) {
					for (int k = 0; k < bcB.length(); k++) {
						int a = Integer.parseInt(bcA.charAt(j)+"");
						int b = Integer.parseInt(bcB.charAt(k)+"");
						
						if(a==b) {
							tmp = ap[a][3];	//어차피 둘로 나누나 한명걸로 하나 똑같음
						}else {
							tmp = ap[a][3] + ap[b][3];							
						}
						charge = Math.max(charge, tmp);
					}
				}
				maxCharge += charge;
			}else if(bcA.length() > 0) {
				for (int j = 0; j < bcA.length(); j++) {
					int a = Integer.parseInt(bcA.charAt(j)+"");
					tmp = ap[a][3];
					charge = Math.max(tmp, charge);
				}
				maxCharge += charge;
			}else if(bcB.length() > 0) {
				for (int j = 0; j < bcB.length(); j++) {
					int b = Integer.parseInt(bcB.charAt(j)+"");
					tmp = ap[b][3];
					charge = Math.max(tmp, charge);
				}
				maxCharge += charge;
			}
			
			if(i==M) break;
			
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
