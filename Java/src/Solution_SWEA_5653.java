import java.io.*;
import java.util.*;

public class Solution_SWEA_5653 {
	public static int T, N, M, K;
	public static int[][] map;
	public static BufferedReader br;
	public static StringTokenizer st;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static List<int[]> cellInfo;	//관리할 셀 정보 리스트(살아 있는 세포만 관리)
	
	//생명력 만큼의 시간이 흘렀을 때 활성화 상태
	//활성화 상태(생명력만큼 시간이 지난 후)에서 시간이 +1 되었을 때 번식
	//활성화 상태이후 생명력만큼 살아있다가 죽음
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//세로(행)
			M = Integer.parseInt(st.nextToken());	//가로(열)
			K = Integer.parseInt(st.nextToken());	//시간
			map = new int[N+K][M+K];	//최대 번식 만큼 배열 생성
			cellInfo = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int r = K/2+i;
					int c = K/2+j;
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c] != 0) {
						cellInfo.add(new int[]{r,c, map[r][c],map[r][c]+1});	//세포 위치, 세포의 생명력, 삽입 시간			
					}
				}
			}
			
			
			for (int time = 1; time <= K; time++) {
				//생명력이 높은 애가 우선되어야 하므로 생명력을 기준으로 내림차순 리스트 정렬
				// >> 내림차순 정렬 후 앞에서부터 서칭하면 조건이 리스트 사이즈 값이라 추가/삭제를 했을 때 사이즈 값의 변경이 생겨서 추가의 경우 성능문제, 삭제의 경우 스킵 문제가 생김
				//때문에 오름차순 정렬 후 뒤에서부터 탐색하면 문제 발생의 경우가 없다.
				Collections.sort(cellInfo, (o1, o2) -> Integer.compare(o1[2], o2[2]));
				
				//셀 값들이 저장된 리스트(cellInfo) 순회
				for (int i = cellInfo.size()-1; i >= 0 ; i--) {
					int[] cell = cellInfo.get(i);
					if(cell[3] == time) {	//활성화 상태 시간이라면
						//번식시작
						for (int j = 0; j < 4; j++) {
							int cell_r = cell[0] + dx[j];
							int cell_c = cell[1] + dy[j];
							if(map[cell_r][cell_c] == 0) {	//배열 탐색 값에 아무것도 없으면
								map[cell_r][cell_c] = cell[2];	//배열에 값 삽입
								//삽입된 세포 관리 리스트에 추가, 생명력 값에 흐른 시간 방영.
								cellInfo.add(new int[] {cell_r, cell_c, cell[2], time+cell[2]+1});
							}
						}
					}
					
					//활성화 단계도 지났고 추가 생명력이 끝날 시간이라면 죽임(관리대상에서 제거)	
					if(cell[3] - 1 + cell[2] == time) {
						cellInfo.remove(i);
					}
				}
				
////				정답 코드
//				for (int i = cellInfo.size()-1; i >= 0 ; i--) {
//					int[] cell = cellInfo.get(i);
//					if(cell[3]-1+cell[2] == time) {
//						cellInfo.remove(i);
//					}
//					if(cell[3] == time) {
//						int r= cell[0];
//						int c=cell[1];
//						int n=cell[2];
//						for (int j = 0; j < 4; j++) {
//							int nr = r+dx[j];
//							int nc = c+dy[j];
//							if(map[nr][nc] == 0) {
//								map[nr][nc] = n;
//								cellInfo.add(new int[] {nr,nc,n,time + map[nr][nc] + 1});
//							}
//						}
//					}
//				}
				
				
			}
			//K만큼의 시간이 지난 후 살아있는 세포의 수는 관리 대상 리스트에 들어 있는 세포의 수
			System.out.printf("#%d %d%n", tc, cellInfo.size());	
			
		}
		

	}
}