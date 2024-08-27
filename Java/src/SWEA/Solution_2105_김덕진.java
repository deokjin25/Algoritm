package SWEA;
/*
 * 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
 * 카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안 된다.
 * 만약, 디저트를 먹을 수 없는 경우 -1을 출력한다.
 * 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력하는 프로그램을 작성하라.
 * 출력해야 할 정답은 가능한 경우 중 디저트를 가장 많이 먹을 때의 디저트 수 이다.
 */
import java.util.*;
import java.io.*;
public class Solution_2105_김덕진 {
	static int T, N, map[][], max;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
	/*
	 * 2중 for문 으로 가능한 사각형 조합 만들어서 try
	 * list에 add하고 반목 돌면서 frequency로 하나만 있는지 확인 x
	 * >> contains로 확인해서 들어 있으면 out
	 * 범위 벗어나면 안봄
	 * 한 변의 최대 길이 N-2 사각형
	 * 끝까지 탐색 해봤는데 되는게 아무것도 없었으면 -1
	 */
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = -1;
			//사각형 시작할 시작점 찾기(i,j)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= N-2; k++) {		//사각형 한 변 최대 길이만큼의 조합(우하강)
						int rightx = i+1*k;
						int righty = j+1*k;
						if(rightx >= N || righty >= N) break;	//범위 확인
						for (int m = 1; m <= N-2; m++) {	//(좌하강)
							boolean flag = false;	//사각형 탐색중 중복 값 확인 이진값
							list.clear();	//시작점 마다 리스트 초기화
							int downx = rightx+1*m;
							int downy = righty-1*m;
							if(downx >= N || downy < 0) break;	//범위 확인
							
							//(좌상승, 사각형이니까 k만큼 이동)
							int leftx = downx-1*k;
							int lefty = downy-1*k;
							if(leftx<0 || lefty<0) break;	//범위 확인
							
							//(우상승, 사각형이니까 m만큼 이동)
							int upx = leftx-1*m;
							int upy = lefty+1*m;
							if(upx<0 || upy>=N) break;	//범위 확인
							
							//마름모 완성(i,j에서부터 한바퀴 돌면서 원소들 삽입)
							for (int l = 1; l <= k; l++) {	//우하강
								if(list.contains(map[upx+1*l][upy+1*l])) {	//이미 있는 숫자면 중복 확인 변수 갱신
									flag = true;
									break;
								}else {
									list.add(map[upx+1*l][upy+1*l]);	//없으면 추가
								}
							}
							
							
							for (int l = 1; l <= m; l++) {	//좌하강
								if(list.contains(map[rightx+1*l][righty-1*l])) {
									flag = true;
									break;
								}else {
									list.add(map[rightx+1*l][righty-1*l]);
								}
							}
							
							for (int l = 1; l <= k; l++) {	//좌상승
								if(list.contains(map[downx-1*l][downy-1*l])) {
									flag = true;
									break;
								}else {
									list.add(map[downx-1*l][downy-1*l]);
								}
							}
							
							for (int l = 1; l <= m; l++) {	//우상승
								if(list.contains(map[leftx-1*l][lefty+1*l])) {
									flag = true;
									break;
								}else {
									list.add(map[leftx-1*l][lefty+1*l]);
								}
							}

							
							if(!flag) {		//중복이 있었다면 flag는 true.
								max = Math.max(max, list.size());	//중복 없었으면 최대치 갱신
							}
						}
					}
					
				}
			}
			System.out.println("#" + tc + " " +max);
		}
	}

}
