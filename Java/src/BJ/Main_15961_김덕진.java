package BJ;

import java.io.*;
import java.util.*;

public class Main_15961_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, d, k, c, dish[], max;
	static Queue<Integer> q;
	
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
		
//		0 1 2 3 4 5 6 7 >> 0 1 2 3 4 5 6 7
		max = Integer.MIN_VALUE;
		q = new LinkedList<>();
		int cnt = 0;
		while(true) {
			
			for (int i = 0; i < N; i++) {
				if(q.contains(dish[i]))
				q.offer(dish[i]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			boolean flag = false;
			q.clear();
			
			
			
			
			
			for (int j = 0; j < k; j++) {
				if(list.contains(dish[(i+j)%N])) flag = true;
				list.add(dish[(i+j)%N]);
			}
			
			
			
			if(flag) continue;
			if(!list.contains(c)) list.add(c);
			max = Math.max(max, list.size());
		}
		System.out.println(max);
		
	}

}
