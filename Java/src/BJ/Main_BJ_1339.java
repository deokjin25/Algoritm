package BJ;

import java.io.*;

public class Main_BJ_1339 {
	static boolean check[];
	static int arr[], N, alphabet[], max, cnt;
	static String[] word;
	public static void main(String[] args) throws Exception {
		//알파벳 배열에  숫자들 꽂아넣고 최대수 dfs
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		alphabet = new int[27];
		
		N = Integer.parseInt(br.readLine());
		
		cnt = 0;
		word = new String[N];
		for (int i = 0; i < N; i++) {
			word[i] = br.readLine();
			for (int j = 0; j < word[i].length(); j++) {
				int index = word[i].charAt(j)-64;
				if(alphabet[index] == 1) continue;
				alphabet[index] = 1; cnt++;				
			}
		}
		
		alphabet = new int[27];
		arr = new int[cnt];
		check = new boolean[10];
		max = Integer.MIN_VALUE;
		perm(0);
		System.out.println(max);
		
	}
	private static void perm(int idx) {
		if(idx == cnt) {
			int tmp = cnt-1;
			
			alphabet = new int[27];
			//알파벳 배열에 인덱스별로 생성된 숫자 조합 차례로 집어넣기 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < word[i].length(); j++) {
					int index = word[i].charAt(j)-64;
					if(alphabet[index] > 0) continue;
					alphabet[index] = arr[tmp--];
				}
			}
			
			//알파벳배열에 들어 있던 숫자를 꺼내서 문자로 합쳐준 다음 한번에 숫자로 변환
			int[] num = new int[N];
			String str = "";
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < word[i].length(); j++) {
					int index = word[i].charAt(j)-64;
					str += String.valueOf(alphabet[index]);		
				}
				num[i] = Integer.parseInt(str);
				str = "";
			}
			
			//변환된 숫자들 모두 합산
			int numSum = 0;
			for (int i = 0; i < N; i++) {
				numSum += num[i];
			}
			
			max = Math.max(max, numSum);	//최대치 갱신	
			
			return;
		}
		
		
		for (int i = 9; i > 9-cnt; i--) {
			if(check[i]) continue;
			check[i] = true;
			arr[idx] = i;
			perm(idx+1);
			check[i] = false;
		}
		
	}

}
