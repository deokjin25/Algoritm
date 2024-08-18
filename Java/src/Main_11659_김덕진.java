import java.io.*;
import java.util.*;

public class Main_11659_김덕진 {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 	//수의 개수
		int M = Integer.parseInt(st.nextToken());	//합을 구해야 하는 횟수
		
		int[] nArr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nArr[i] = Integer.parseInt(st.nextToken());
		}
		
		//누적합 배열
		for (int i = 1; i < nArr.length; i++) {
			nArr[i] = nArr[i]+nArr[i-1]; 
		}
				
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int idxI = Integer.parseInt(st.nextToken());
			int idxJ = Integer.parseInt(st.nextToken());
			//i부터 j까지, j의 값 - i의 직전 인덱스
			System.out.println(nArr[idxJ] - nArr[idxI-1]);
		}
		
	}

}
