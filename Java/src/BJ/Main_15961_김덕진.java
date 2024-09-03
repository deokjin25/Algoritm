package BJ;

import java.io.*;
import java.util.*;

public class Main_15961_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, d, k, c, dish[], max;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//회전초밥 벨트에 놓인 접시의 수
		d = Integer.parseInt(st.nextToken());	//초밥의 가지수
		k = Integer.parseInt(st.nextToken());	//연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken());	//쿠폰 번호
		
		dish = new int[N];
		for (int i = 0; i < N; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		
		int check[] = new int[d+1];
		int count = 0;
		int answer = 0;
//		0 1 2 3 4 5 6 7 >> 0 1 2 3 4 5 6 7
		for (int i = -k; i < N; i++) {
			//dish[(i+k)%N]: 들어올 값.
			
			//들어올 값이 한번도 쓰인적이 없다면, 개수 늘려주고 가짓수 증가
			//중복된 값이 있으면 가짓수는 늘지 않고 쓰인 개수만 늘어남
			//무슨 말이냐면 9 7 2 7 입력이 들어오면 가지수는 3이니까 의미상 9 7 2만 카운트하고 마지막에 들어온 7은 판단 안함(쓰인 개수는 증가) 
			if(check[dish[(i+k)%N]]++ == 0) {
				count++;
			}
			
			/*
			 * i가 0보다 클 때(가짓수가 k만큼일 때)
			 * 지금까지 고른 k개 중에서 제일 앞에 있는 숫자의 쓰임 정도는 -1을 해주는데 0이면 아예 사라진거니까 가짓수도 하나 빼줌
			 * 이 또한 무슨 말이냐면 7 2 7 5 뒤에 9가 들어오는 경우 앞단에 7은 중복값으로 check 배열에 2로 찍혀있음
			 * check 배열에서 쓰임 정도는 무조건 하나 내려주기 때문에 의미상 2 7 5 9 만 남은 것으로 본다.(이 때 가짓수는 유지)
			 * 여기서 25가 들어오는 경우 위에서 가짓수는 하나 증가, k보다 많아지지만
			 * 앞단에 있던 2는 쓰임 정도가 하나 내려가서 0, 없어진 것으로 판단되어서 가짓수가 k개로 맞춰진다. >> (7 5 9 25)
			 * 여기서 한 번 더 중복 값, 9가 들어온다 해도 위에서 가짓수(count) 증가는 되지 않았고 (쓰임 개수는 2로 갱신)
			 * 앞단에 있던 값(7)이 중복 값이 아니면 가짓수는 내려가서 의미상 5 9 25 만 count로 치고 (실제 유지 값은 5 9 25 9)
			 * 중복값이었다면(9 5 7 25인데 9가 들어온 경우) count는 유지, 5 7 25 9로 가짓수, k개가 유지된다.
			 * 여하튼 앞에 있는 값이 0이 될 때 가짓수를 내려주면 의미상 연속성을 판단할 수 있게 된다.  
			 */
			if(i >= 0 && --check[dish[i%N]]==0) {
				count--;
			}
			
			answer = Math.max(answer, check[c] > 0 ? count : count+1);
			
		}
		
		System.out.println(answer);
		
	}

}
