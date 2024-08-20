package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4796_김덕진 {
	/* 모든 구간을 다 탐색하고 조건에 맞는지 확인하자
	 * 하나의 구간에 대해 제일 높은 지점을 찾고
	 * 높은 지점을 기준으로 양옆이 낮아지는지 확인
	 * 하나의 구간에는 최소 3개의 연속된 자리배열이 필요하다.
	 * >> 제한 시간 초과 발생.
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T,N,cnt;
	static int[] harr;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	//산의 개수			
			
			harr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				harr[i] = Integer.parseInt(st.nextToken());
			}
			
			cnt = 0;
			for (int i = N; i >= 3; i--) {
				subset(0,i-1);
				}
			
			System.out.println("#"+tc+" "+cnt);
			
		}
	}

	private static void subset(int start, int end) {
		//시작 지점과 끝 지점 하나씩 밀기
		while(end<N) {
			int topIdx = -1;	//구간 내에서 제일 높은 곳의 인덱스
			int top = -1; 	//구간 내에서 제일 높은 곳의 값
			for (int i = start; i <= end; i++) {
				if (top < harr[i]) {
					top = harr[i];
					topIdx = i;
				}
			}

			boolean flag1 = false;
			boolean flag2 = false;
			
			for (int h1 = start; h1 < topIdx; h1++) {
				if(harr[h1] < harr[h1+1]) flag1 = true;
				if(harr[h1] > harr[h1+1]) {		//한번 이라도 삑사리 나면
					flag1 = false;	
					break;
				}
			}
			
			for (int h2 = topIdx; h2 < end; h2++) {
				if(harr[h2] > harr[h2+1]) flag2 = true;
				if(harr[h2] < harr[h2+1]) {
					flag2 = false;
					break;
				}
			}
			
			if(flag1 && flag2) cnt++;	//왼쪽 오른쪽 모두 조건에 맞아야 한다.
			
			
			start++;
			end++;
		}
	}

}
