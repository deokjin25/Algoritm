package BJ;

import java.io.*;
import java.util.*;
/*
 * 돌아다니는 경우의 수 모두 추출(최대 10!)
 * 다음 도시로의 이동 비용 계산 중에 이전 갱신 값보다 커지면 가지치기
 */
public class Main_10971_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], arr[], min;
	static boolean visit[];
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		arr = new int[N];
		visit = new boolean[N];
		min = Integer.MAX_VALUE;
		perm(0, 0);
		System.out.println(min);
	}
	
	private static void perm(int idx, int distance) {
		if(distance >= min) return;	//중간에 합산되고 있는 거리가 갱신된 최소거리보다 커지면 가지치기
		
		// ! 경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며
		if(idx == N) {	//순회 경로 완성
			if(map[arr[idx-1]][arr[0]] == 0) return;	//길이 없는 경우 리턴.
			distance += map[arr[idx-1]][arr[0]];	//마지막 장소(출발지)로 돌아가는 거리 추가
			min = Math.min(min, distance);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			arr[idx] = i;
			if(idx == 0) {	//처음 출발지 고를때는 이전 출발지가 없으니 거리 변수 그냥 넘겨줌
				perm(idx+1, distance);
			}
			//그 다음 부터는 일단, 길이 있어야 하고
			else if(map[arr[idx-1]][i] != 0) {
				//현재 거리 = 직전 거리 + 이전 정점에서 다음 정점의 거리(가중치) 합산
				perm(idx+1, distance + map[arr[idx-1]][i]);	//길이 있는 경우 그대로 진행
			}
			visit[i] = false;
		}
		
	}

}
