package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4008_김덕진 {
	static int T,N,max,min;
	static int[] operator = new int[4], nums, arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer> list = new ArrayList<>();

	/* 사칙연산 배열에서의 (인덱스 번호)조합
	 * 방문처리 대신 +-1 처리(0이면 pass)
	 * 만들어진 인덱스 번호로 숫자 배열 돌면서 최소 최대 동시 갱신
	 */ 

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//테케마다 초기화
			list.clear();
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());	//연산자 배열
			}
			
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());	//숫자 배열
			}
			
			arr = new int[N-1];
			perm(0);	//연산자 조합
			System.out.println("#" + tc + " " + (max-min));

			
		}
	}

	private static void perm(int idx) {
		if(idx == N-1) {	//숫자 배열 보다 하나 적은 조합 확보(arr: 선택된 연산자 배열)
			int tmp = nums[0];
			for (int i = 0; i < N-1; i++) {
				switch(arr[i]+1) {
					case 1: 
						tmp += nums[i+1];
						break;
					case 2:
						tmp -= nums[i+1];
						break;
					case 3:
						tmp *= nums[i+1];
						break;
					case 4:
						tmp /= nums[i+1];
						break;
				}			
			}
			max = Math.max(max, tmp);
			min = Math.min(min, tmp);			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(operator[i] == 0) continue;
			arr[idx] = i;
			operator[i] -= 1;
			perm(idx+1);
			operator[i] += 1;
		}
		
	}
	
	
	
	
	
	
	
	
	
	

}
