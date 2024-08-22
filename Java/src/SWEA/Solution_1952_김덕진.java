package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1952_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, price[] = new int[4], plan[] = new int[13], min;
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
			
			min = Integer.MAX_VALUE;
			search(1,0);
			System.out.println("#" + tc + " "+min);

		}
	}
	
	
	private static void search(int idx, int money) {
		if(idx >= 13) {
			min = Math.min(min, money);
			return;
		}
		
		if(plan[idx] == 0) {
			search(idx+1, money);
		}else {
			if(idx == 1) search(idx+12, price[3]);	// 첫 달만 일년 이용권 이용
			search(idx+1, money + plan[idx] * price[0]);	//하루 이용권이용
			search(idx+1, money + price[1]);	//한달 이용권이용
			search(idx+3, money + price[2]);	//세달 이용권 이용
		}

	}

}
