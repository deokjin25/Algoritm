package BJ;

import java.io.*;
import java.util.*;

public class Main_6987_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] arr = new int[4][18], game = new int[15][2];
	static int[] compare = new int[18];
	static boolean[] check = new boolean[4];
	
	public static void main(String[] args) throws Exception {
		//판단해야 하는 결과표 배열
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 18; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//모든 경기 대진표
		int k=0;
		for (int i = 0; i < 6; i++) {
			for (int j = i+1; j < 6; j++) {
				game[k][0] = i;
				game[k][1] = j;
				k++;
			}
		}
		
//		for (int i = 0; i < 15; i++) {
//			System.out.println(Arrays.toString(game[i]));
//		}
		
		simulation(0);
		
		for (int i = 0; i < 4; i++) {
			if(check[i]) {
				System.out.printf("1 ");
			}else {
				System.out.printf("0 ");
			}
			
		}
		
	}

	private static void simulation(int gameIdx) {
		if(gameIdx == 15) {		//모든 경기 결과 기록 후
//			System.out.println(Arrays.toString(compare));
			for (int i = 0; i < 4; i++) {
				if(check[i] || !Arrays.equals(arr[i], compare)) continue;	//이미 확인이 되었거나 같지 않으면 pass
				check[i] = true;
			}
			return;
		}
		
		//A팀부터 0, F팀은 5 
		int team1 = game[gameIdx][0];
		int team2 = game[gameIdx][1];
		
		//team1이 이겼을 경우, team2는 패배
		compare[team1*3]++;
		compare[team2*3+2]++;
		simulation(gameIdx+1);
		compare[team1*3]--;
		compare[team2*3+2]--;
		
		//team1이 졌을 경우, team2는 승리
		compare[team1*3+2]++;
		compare[team2*3]++;
		simulation(gameIdx+1);
		compare[team1*3+2]--;
		compare[team2*3]--;
		
		//team1이 무승부, team2도 무승부
		compare[team1*3+1]++;
		compare[team2*3+1]++;
		simulation(gameIdx+1);
		compare[team1*3+1]--;
		compare[team2*3+1]--;
		
		
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
