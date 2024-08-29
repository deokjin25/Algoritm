package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1251_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T,N, parents[];
	static double E, xarr[], yarr[];
	static List<double[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			list.clear();
			
			xarr = new double[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				xarr[i] = Double.parseDouble(st.nextToken());
			}
			
			yarr = new double[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				yarr[i] = Double.parseDouble(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			//서로소 집합 생성
			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}
						
			
			//섬 끼리의 환경 부담금 정책값 계산
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					double d = Math.pow(xarr[i] - xarr[j], 2) + Math.pow(yarr[i] - yarr[j], 2);
					list.add(new double[] {i,j,E * d});
				}
			}
			
			Collections.sort(list, (o1, o2) -> Double.compare(o1[2], o2[2]));
			
			double answer = 0;
			int cnt = 0;
			for (int i = 0; i < list.size(); i++) {
				if(cnt == N-1) break;
				double[] arr = list.get(i);
				int from = (int) arr[0];
				int to = (int) arr[1];
				double value = arr[2];
				
				if(isUnion(from,to)) {
					//최소 환경 부담금 누적
					answer += value;
					cnt++;	//간선 수 카운트
				}else {
					continue;
				}
			}
			
			System.out.printf("#%d %.0f \n", tc, answer);
			
		}
		
	}

	private static int findSet(int x) {
		if(parents[x] == x) {
			return x;
		}else{
			return parents[x] = findSet(parents[x]);
		}
	}

	private static boolean isUnion(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) {
			return false;
		}else {
			parents[aRoot] = bRoot;	//부모 노드 갱신
			return true;
		}
	}

}
