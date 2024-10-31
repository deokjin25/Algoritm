package BJ;

import java.io.*;
import java.util.*;

public class Main_1786_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int table[], ans;
	static String parent[], pattern[];

	public static void main(String[] args) throws Exception {
		parent = br.readLine().split("");
		pattern = br.readLine().split("");
		
		ans = 0;
		KMP();
		System.out.println(ans);
		System.out.println(sb);
	}

	private static void KMP() {
		table = makeTable();
		
		int idx = 0;	//현재까지 대응되고 있는 글자 수
		for (int i = 0; i < parent.length; i++) {
			
			while(idx>0 && !parent[i].equals(pattern[idx])) {
				idx = table[idx-1];
			}
			
			if(parent[i].equals(pattern[idx])) {
				
				if(++idx == pattern.length) {
					ans++;
					sb.append((i-idx+2) + " ");
					idx = table[idx-1];	//계속 탐색
				}
				
			}
			
		}
		
	}
	
	//특정 i에서의 일치하는 접두사 접미사 문자개수 생성
	private static int[] makeTable() {
		int[] table = new int[pattern.length];
		
		int idx = 0;
		for (int i = 1; i < pattern.length; i++) {
			
			//일치하는 문자가 있을 때(idx>0) 다음 문자가 이어지지 않으면
			while(idx > 0 && !pattern[i].equals(pattern[idx])) {
				idx = table[idx-1];
			}
			
			
			if(pattern[i].equals(pattern[idx])) {
				idx++;
				table[i] = idx;
			}
		}
		
		return table;
	}

}
