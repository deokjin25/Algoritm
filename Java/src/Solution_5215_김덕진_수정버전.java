

/*
 * 민기는 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거를 주문하여 먹으려고 한다.
 * 재료는 미리 만들어서 준비해놓기 때문에 조합에 들어가는 재료를 잘라서 조합해주지는 않고, 재료를 선택하면 준비해놓은 재료를 그대로 사용하여 조합해준다. 
 * 햄버거의 재료에 대한 맛을 자신의 오랜 경험을 통해 점수를 매겨놓았다.
 * 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해주는 프로그램을 만들어보자.
 * 같은 재료를 여러 번 사용할 수 없으며, 햄버거의 조합의 제한은 칼로리를 제외하고는 없다. >> 조합
 */

import java.io.*;
import java.util.*;

public class Solution_5215_김덕진_수정버전 {
	static int T, N, L;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] scoreL;
	static int maxFlavor;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//재료의 수
			L = Integer.parseInt(st.nextToken());	//제한 칼로리
			
			scoreL = new int[N][2];	//재료의 맛과 칼로리 정보 배열
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				scoreL[i] = new int[]{score,cal};
			}
			
			maxFlavor=0;
			hamburger(0, 0, 0);
			System.out.println("#"+tc+" "+maxFlavor);
			
		}
		

	}

	private static void hamburger(int idx, int score, int c) {
		if(c > L) return;
		
		if(idx == N) {
			if(maxFlavor < score) {
				maxFlavor = score;				
			}
			return;
		}
		
		hamburger(idx+1, score+scoreL[idx][0], c+scoreL[idx][1]);
		hamburger(idx+1, score, c);
	}

}
