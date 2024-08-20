package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5215NP_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T,N,L;
	static int chooseScore = 0;
	static int chooseCal = 0;
	static int maxScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L  = Integer.parseInt(st.nextToken());
			int[][] ingredient = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				ingredient[i] = new int[] {score,cal};
			}
			
			int[] npArr = new int[N];
			maxScore = Integer.MIN_VALUE;
			for (int i = N-1; i >= 0; i--) {
				Arrays.sort(npArr);
				npArr[i] = 1;
				
				do {
					chooseScore = 0;
					chooseCal = 0;
					for (int j = 0; j < N; j++) {
						if (npArr[j] == 1) {
							chooseScore += ingredient[j][0];
							chooseCal += ingredient[j][1];
						}
					}
					if(chooseCal <= L) {
						maxScore = Math.max(maxScore, chooseScore);
					}
				}while(np(npArr));
							
			}
			
			System.out.println("#"+tc+" "+maxScore);
			
		}

		
	}

	private static boolean np(int[] npArr) {
		int len = npArr.length;
		int i = len-1;
		while(i>0 && npArr[i-1] >= npArr[i]) --i;
		if(i==0) return false;
		
		int j = len-1;
		while(npArr[i-1] >= npArr[j]) --j;
		
		swap(npArr, i-1,j);
		
		int k = len-1;
		while(i<k) swap(npArr,i++,k--);
		
		return true;
	}

	private static void swap(int[] npArr, int i, int j) {
		int tmp = npArr[i];
		npArr[i] = npArr[j];
		npArr[j] = tmp;
		
	}

}
