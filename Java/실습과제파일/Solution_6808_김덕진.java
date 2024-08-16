import java.io.*;
import java.util.*;
public class Solution_6808_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T;
	static List<Integer> gyuyeong = new ArrayList<>();
	static List<Integer> inyeong_card = new ArrayList<>();
	static int[] winLose = {0,0}; //규영이 총점, 인영이 총점
	static int[] inyeong = new int[9];
	static boolean[] check;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());	//테스트 케이스 개수
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			winLose[0] = 0; winLose[1] = 0;	//전역 변수로 관리해주고 있기 때문에 테스트 케이스마다 초기화를 해주어야 한다.
			gyuyeong.clear();  inyeong_card.clear();  //역시 전역 변수로 관리되므로 초기화 하지 않으면 데이터가 쌓인다.
			
			for (int i = 0; i < 9; i++) {
				gyuyeong.add(Integer.parseInt(st.nextToken()));
			}
			
			for (int i = 1; i < 19; i++) {
				if (Collections.frequency(gyuyeong, i) == 0) {
					inyeong_card.add(i);
				}
			}
			
			for (int i = 0; i < 9; i++) {
				inyeong[i] = (int) inyeong_card.toArray()[i];
			}
			
			
			
			check = new boolean[9];
			
			battle(0,0,0);
			System.out.println("#" + tc + " " + winLose[0] + " " + winLose[1]);
			
		}
		
	}
	
	//!9 = 362,880
	static void battle(int idx, int g_score, int i_score) {
		if(idx == 9) {	//인영이의 조합 중 하나가 끝났을 때
			if(g_score > i_score) {	//규영이 총점이 더 높으면
				winLose[0] += 1;	//규영이 승
			}else if (g_score < i_score){	//규영이가 더 낮으면		
				winLose[1] += 1;	//규영이 패
			}
			return;
		}
		Integer y = gyuyeong.get(idx);	//비교할 규영이의 값
		for (int i = 0; i < inyeong.length; i++) {
			if(check[i]) continue;	//방문했던 곳이면(사용한 적 있는 숫자면) 건너뛰기			
			Integer x = inyeong[i];	//인영이의 값, 인덱스 바꿔가면서 숫자 체인지
			
			//score를 가지면서 가야하니까 비교 먼저
			if(x < y) {		//규영이 승
				check[i] = true;	//방문한 곳 체크
				battle(idx+1, g_score + x + y, i_score);	//방문한 곳 체크 이후 규영이 숫자 파라미터에 합산 점수 더하고 다음 숫자 조합 고르러 재귀 출발
				check[i] = false;	//방문했던 곳 원복
			}else if(x > y){	//규영이 패
				check[i] = true;	//방문한 곳 체크
				battle(idx+1, g_score, i_score+x+y);	//방문한 곳 체크 이후 인영이 숫자 파라미터에 합산 점수 더하고 다음 숫자 조합 고르러 재귀 출발
				check[i] = false;	//방문했던 곳 원복
			}
			//같은 자리 에서의 다른 숫자 for loop 
		}
		return;		
	}
}
