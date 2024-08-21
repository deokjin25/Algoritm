package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4796_김덕진 {
	/* 
	 * 내려가다 올라가면 우뚝 선 산 조건 충족.
	 * 올라가는거 내려가는거 카운트 곱하면 구간 개수
	 */
	static int T,N,cnt;
	static int[] harr;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();	//산의 개수			
			
			harr = new int[N];	//산 높이 배열
			
			for (int i = 0; i < N; i++) {
				harr[i] = sc.nextInt();
			}
			
			cnt = 0;	//구간 개수 카운트
			int up = 0;
			int down = 0;
			for (int i = 0; i < N-1; i++) {
				if (harr[i] < harr[i+1]) {	//올라가는 상태
					if(down > 0) {	//이전에 내려가는 형태의 모습이 있었다면
						cnt += up * down; 	//총 개수 더하기
						up = 0;
						down = 0;		//초기화
					}
					up++;

				}else if(harr[i] > harr[i+1]){		//내려가는 상태
					down++;

				}else {		// 높이가 똑같으면 초기화
					up = 0;
					down = 0;
				}
			}
			
			if(down > 0) {	//우뚝 선 산 조건에 맞는데 내려가다가 끝났을 수도 있으니까 반복문 종료 후 한 번 더 점검
				cnt += up * down;
			}
			
			
			System.out.println("#" + tc+ " " + cnt);
			
		}
	}


}
