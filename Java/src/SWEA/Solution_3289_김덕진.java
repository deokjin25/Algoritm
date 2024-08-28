package SWEA;

import java.io.*;
import java.util.*;

public class Solution_3289_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, s, a, b, parents[];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 1~N까지의 원소
			M = Integer.parseInt(st.nextToken());	// 연산의 개수
			
			parents = new int[N+1];	//1번부터 N번까지의 원소
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			
			sb.append("#" + tc + " ");
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				s = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				//최상단 부모로 갱신 해두고 연산 시작(그러지 않으면 자식이 중간에 다른 부모쪽으로 빠져버림)
				a = set(a);
				b = set(b);
				
				if(s == 0) {
					//합집합 연산
					union();
				}else {
					//같은 집합인지 검사
					if(a==b) {
						sb.append("1");
					}else {
						sb.append("0");
					}
				}
				
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int set(int x) {
		if(x==parents[x]) {
			return x;
		}else {
			return parents[x] = set(parents[x]);
		}
		
	}

	private static void union() {
		//편의상 왼쪽(a)으로 합침(b가 a의 참조값을 따라감)
		parents[b] = parents[a];
	}

}
