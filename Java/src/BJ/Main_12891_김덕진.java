package BJ;
import java.io.*;
import java.util.*;

public class Main_12891_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int s,p;
	static String[] arr;
	static int[] acgt = new int[4];
	static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		st= new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		//초기 상황
		int[] arr_score = new int[4];
		arr = br.readLine().split("");
		for (int i = 0; i < p; i++) {
			if (arr[i].equals("A")) arr_score[0] += 1;
			if (arr[i].equals("C")) arr_score[1] += 1;
			if (arr[i].equals("G")) arr_score[2] += 1;
			if (arr[i].equals("T")) arr_score[3] += 1;
		}
		
		
		//acgt 조건 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			acgt[i] = Integer.parseInt(st.nextToken());
		}
		
		//초기 점검
		if(arr_score[0] >= acgt[0] && arr_score[1] >= acgt[1] && 
				arr_score[2] >= acgt[2] && arr_score[3] >= acgt[3]) {
			cnt++;
		}
		
		//앞에거 지우고 하나씩 뒤에 걸 보면서(계산만) 조건에 맞으면 cnt + 1
		for (int i = p; i < s; i++) {
			String a = arr[i]; //얘는 더하고
			String b= arr[i-p];	//얘는 빼고

			if (a.equals("A")) arr_score[0] += 1;
			if (a.equals("C")) arr_score[1] += 1;
			if (a.equals("G")) arr_score[2] += 1;
			if (a.equals("T")) arr_score[3] += 1;
			
			if (b.equals("A")) arr_score[0] -= 1;
			if (b.equals("C")) arr_score[1] -= 1;
			if (b.equals("G")) arr_score[2] -= 1;
			if (b.equals("T")) arr_score[3] -= 1;
			
			
			
			//조건 점검
			if(arr_score[0] >= acgt[0] && arr_score[1] >= acgt[1] && 
					arr_score[2] >= acgt[2] && arr_score[3] >= acgt[3]) {
				cnt++;
			}
		}
		
		System.out.println(cnt);

		
	}


}
