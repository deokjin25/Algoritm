package BJ;

import java.io.*;
import java.util.*;
/*
 * 원숭이가 말의 이동으로 이동한 맵과 그냥 이동한 맵의 배열을 다르게 유지해줘야 하는데
 * 맵은 바뀔게 없고 visit 배열에 높이 차원을 추가해서 관리.
 */
public class Main_1600_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, W, H, map[][];
	static boolean visit[][][];
	static int[] dr = {-1,1,0,0,-2,-1,1,2,2,1,-1,-2};	//4방향과 말의 움직임 방향배열 [12]
	static int[] dc = {0,0,1,-1,1,2,2,1,-1,-2,-2,-1};
	
	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[H][W][K+1];		//기존의 [가로][세로]배열에 점프 유무에 다른 [높이] 차원 추가
		System.out.println(horseMonkey());
		
	}

	private static int horseMonkey() {
		Queue<int[]> q = new LinkedList<>();
		//시작점은 0
		q.offer(new int[] {0,0,0,0});	//[0]: i, [1]: j, [2]: minMap[i][j], [3]: k 카운트
		
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			int value = now[2];
			int kCnt = now[3];
			
			if(r == H-1 && c == W-1) return value;
			int k = kCnt >= K ? 4 : 12;		//kCnt가 허용된 수치를 넘긴 경우 인접방향만 살핌
			
			for (int i = 0; i < k; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr < 0 || nc < 0  || nr >= H  || nc >= W) continue;
				if(map[nr][nc] == 1) continue;
				
				
				if(i<4) {	//인접으로만 이동한 경우
					if(visit[nr][nc][kCnt]) continue;	//방문한 적이 있는 경우 pass
					visit[nr][nc][kCnt] = true;
					q.offer(new int[] {nr, nc, value+1, kCnt});
					
				}else {		//말 이동으로 이동한 경우([높이] 인덱스 1 증가)
					if(visit[nr][nc][kCnt+1]) continue;
					visit[nr][nc][kCnt+1] = true;
					q.offer(new int[] {nr, nc, value+1, kCnt+1});
				}
				
			}
			
		}
		
		return -1;
	}
	
	
	
	
	
	
	
	
	
	
	

}
