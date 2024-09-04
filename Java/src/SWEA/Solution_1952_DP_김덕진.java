package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1952_DP_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, price[] = new int[4], plan[] = new int[13], min, dpArr[];
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 13; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			dpArr = new int[13];
			dp();
			System.out.println("#" + tc + " "+ dpArr[12]);

		}
	}
	
	
	private static void dp() {
		for (int i = 1; i < 13; i++) {
			dpArr[i] = dpArr[i-1] +  price[0] * plan[i];	//직전달 + 1일 이용권을 우선 최소값으로 설정
			dpArr[i] = Math.min(dpArr[i-1]+price[1], dpArr[i]); 
			if(i >= 3) dpArr[i] = Math.min(dpArr[i-3]+price[2], dpArr[i]);	//3달 이용권을 쓴 경우와 현재까지의 비용을 비교
			if(i >= 12) dpArr[i] = Math.min(dpArr[i-12]+price[3], dpArr[i]);	//12달 이용권을 쓴 경우와 현재까지의 비용을 비교
		}

	}

}
