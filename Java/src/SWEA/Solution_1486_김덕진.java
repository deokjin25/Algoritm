package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1486_김덕진 {
	static int T, N, B;
	static int[] height, arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			list.clear();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());	//선반 높이
			
			st = new StringTokenizer(br.readLine());
			height = new int[N];
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			arr = new int[N];
			subset(0);
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < list.size(); i++) {
				min = Math.min(min, Math.abs(list.get(i)-B));
			}
//			System.out.println(Arrays.toString(list.toArray()));
			System.out.println("#" + tc +" " +min);
			
		} 
		

	}

	private static void subset(int idx) {
		if(idx == N) {
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				tmp += arr[i];
			}
			if(tmp<B)	return;//키 조합이 B보다 작으면 pass
			list.add(tmp);	//list << 점원 키 조합(부분 조합 합산 값)
			return;
		}
		
		arr[idx] = height[idx];
		subset(idx+1);
		arr[idx] = 0;
		subset(idx+1);		
		
		
	}

}
