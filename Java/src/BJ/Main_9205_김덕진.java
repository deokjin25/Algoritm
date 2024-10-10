package BJ;

import java.io.*;
import java.util.*;

public class Main_9205_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t, n, hx, hy, cx, cy, fx, fy;
	static List<int[]> clist;
	
	public static void main(String[] args) throws Exception {
		t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());	//편의점 개수
			
			st = new StringTokenizer(br.readLine());
			hx = Integer.parseInt(st.nextToken());	//집 좌표 x
			hy = Integer.parseInt(st.nextToken());	//집 좌표 y
			
			clist = new ArrayList<>();	//편의점 리스트
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				cx = Integer.parseInt(st.nextToken());	//i번째 편의점 좌표 x
				cy = Integer.parseInt(st.nextToken());	//i번째 편의점 좌표 y
				clist.add(new int[] {cx, cy});
			}
			
			Collections.sort(clist, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0] > o2[0]) return 1;
					else if(o1[0] == o2[0]) {
						if(o1[1] > o2[1]) return 1;
						else return -1;
					}else {
						return -1;
					}
				}
			});
			
			st = new StringTokenizer(br.readLine());
			fx = Integer.parseInt(st.nextToken());	//페스티벌 좌표 x
			fy = Integer.parseInt(st.nextToken());	//페스티벌 좌표 y
			
			System.out.println(isHappy());
			
		}
		

	}

	private static String isHappy() {
		int now_x = hx; int now_y = hy;
		
		for (int i = 0; i < n; i++) {
			if((Math.abs(now_x - fx) + Math.abs(now_y - fy)) <= 1000) return "happy";
			
			int nx = clist.get(i)[0];
			int ny = clist.get(i)[1];
			
			if((Math.abs(now_x - nx) + Math.abs(now_y - ny)) <= 1000) {
				now_x = nx;
				now_y = ny;
			}
		}
		
		if((Math.abs(now_x - fx) + Math.abs(now_y - fy)) <= 1000) return "happy";

		return "sad";
	}


}
