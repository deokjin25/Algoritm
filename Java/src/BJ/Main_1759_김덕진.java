package BJ;

import java.io.*;
import java.util.*;

/*
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성
 * 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
 * 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열
 */
public class Main_1759_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int L, C, arr[], alphaNum[];
	static char alpha[];
	static List<String> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());	//암호 자리수
		C = Integer.parseInt(st.nextToken());	//암호 조합 알파벳 개수
		
		alpha = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpha);	//사전 순으로 하기 위해서 정렬수행
		list.clear();
		arr = new int[L];
		comb(0,0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}

	private static void comb(int idx, int cnt) {
		if(idx == L) {	//알파벳 조합할 인덱스 번호 완성
			String string = "";
			int vowel = 0;	//모음
			int consonant = 0;	//자음
			for (int i = 0; i < L; i++) {
				string += alpha[arr[i]];
				if(alpha[arr[i]] == 'a' || alpha[arr[i]] == 'e' ||
						alpha[arr[i]] == 'i' || alpha[arr[i]] == 'o' || alpha[arr[i]] == 'u'){
					vowel++;
				}else {
					consonant++;
				}
			}
			
			if(vowel >= 1 && consonant >= 2) {	//조건에 맞다면 리스트에 추가
				list.add(string);
			}
			
			return;
		}
		
		
		for (int i = cnt; i < C; i++) {
			arr[idx] = i;
			comb(idx+1, i+1);
		}
		
		
		
	} 

}
