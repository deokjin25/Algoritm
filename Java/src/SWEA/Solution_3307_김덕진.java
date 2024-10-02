package SWEA;

import java.io.*;
import java.util.*;

public class Solution_3307_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, arr[], dp[];

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	//원소의 개수
			
			arr = new int[N];	//원소 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			//i번째 길이에 올 수 있는 최소 값
			dp = new int[N];
			//0으로 초기화 시에는 이진탐색의 음수치 결과를 양수로 전환했을 때 제일 오른쪽의 위치를 찾아주기 때문에
			//ex) [0,0,0,0,0] 배열에 1이 들어갈 위치가 5로 나옴(원하는 값은 0)
			//때문에 배열을 int 최대값으로 초기화
			Arrays.fill(dp, Integer.MAX_VALUE); 
			for (int i = 0; i < N; i++) {
				int x =(Math.abs(Arrays.binarySearch(dp, arr[i]))-1);	//현재의 원소가 들어갈 위치
				dp[x] = arr[i];
			}
			
			
			int ans = 0;
			for (int i = 0; i < N; i++) {
				if(dp[i] != Integer.MAX_VALUE) ans = i+1;	//0부터 값이 들어가 있으니 +1
			}			
			System.out.println("#" + tc + " " + ans);

		}
		

	}

}
