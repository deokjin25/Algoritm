package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5658_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	static int T, N, K;
	static char arr[];
	static List<String> listStr;
	static List<Integer> listInt;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = br.readLine().toCharArray();
			
			listStr = new ArrayList<>();
			int N4 = N/4;
			for (int i = 0; i < N4; i++) {	// N/4 만큼 회전하면 원위치
				
				for (int start = 0; start < N; start+=N4) {	//한 문자열의 시작 위치
					int end = start + N4;	//끝 위치
					String str = "";
					for (int k = start; k < end; k++) {		//문자열 만들기
						str += arr[k] + "";
					}
					//문자열 완성
					if(!listStr.contains(str))  listStr.add(str);	//중복 안되게 검사하고 넣어주기
				}
				
				//회전 시키기 끝 인덱스 처음으로 옮기고 밀면 됨
				char tmp = arr[N-1];
				for (int j = N-1; j > 0; j--) {
					arr[j] = arr[j-1];
				}
				arr[0] = tmp;
			}
			
			//문자열 리스트에 있는 16진수 10진수로 변환
			listInt = new ArrayList<>();
			for (int i = 0; i < listStr.size(); i++) {
				listInt.add(Integer.parseInt(listStr.get(i), 16));
			}
			
			Collections.sort(listInt, (o1, o2) -> o2-o1);	//내림차순 정렬
			System.out.println("#"+tc+" "+listInt.get(K-1));	//0번 인덱스가 1번째이므로 k-1
		}
		
	}

}
