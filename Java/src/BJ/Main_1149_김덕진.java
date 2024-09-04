package BJ;

import java.io.*;
import java.util.*;

public class Main_1149_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, price[][], dp[];
	static boolean choice[] = new boolean[3];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		price = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			price[i][0] = Integer.parseInt(st.nextToken());
			price[i][1] = Integer.parseInt(st.nextToken());
			price[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N];
		int answer = Integer.MAX_VALUE;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			choice[i] = true;
			dp[0] = price[0][i];
			for (int j = 0; j < 3; j++) {
				list.clear();
				list.add(i);
				list.add(j);
				if(choice[j]) continue;
				choice[j] = true;
				dp[1] = dp[0] + price[1][j];
				for (int k = 2; k < N; k++) {
					for (int c = 0; c < 3; c++) {
						if(choice[c]) continue;
						dp[k] = dp[k-1] + price[k][c];
						list.add(c);
					}
					choice[list.get(k-2)] = false;
				}
				
				answer = Math.max(answer, dp[N-1]);
				
			}
		}
		
		System.out.println(answer);
		
	}
	
	

}
