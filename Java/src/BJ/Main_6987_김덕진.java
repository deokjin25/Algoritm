package BJ;

import java.io.*;
import java.util.*;
/*
 * 가능한 결과.
 * 승 패는 그 수가 똑같아야 함
 * 무승부는 2로 나뉘어야 하고 최소 2나라에 카운트가 들어가 있어야 함.
 * 각 조별로 동일한 조에 소속된 국가들과 한 번씩, 각 국가별로 총 5번의 경기
 * >> 6C2 * 5 = 총 75번의 경기(경기표 조합의 수)
 * 
 */
public class Main_6987_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] arr = new int[4][18];
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 18; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		
		for (int i = 0; i < 4; i++) {
			int win = 0;
			int lose = 0;
			int draw = 0;
			int drawCnt = 0;
			for (int j = 0; j < 18; j+=3) {
				win += arr[i][j];
				draw += arr[i][j+1];
				lose += arr[i][j+2];
				if(arr[i][j+1] > 0) drawCnt++;
			}
			if(win+lose+draw != 30 || win != lose || draw%2 != 0 || drawCnt == 1) {
				System.out.printf("0 ");				
			}else {
				System.out.printf("1 ");
			}
			
		}
		
		
		
	}
}
