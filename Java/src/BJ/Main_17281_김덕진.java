package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
첫 이닝(행)은 1번 인덱스가 4번째 순서임이 고정
나머지는 탐색(순서 바꿔보면서)하면서 최대 점수 기록.
하나의 순서를 결정하면 끝 이닝까지 유지
아웃 당하면 다음 이닝때는 아웃 당한 사람 다음 순서부터 시작
아웃 당할때까지 같은 이닝 반복

>> 선수 조합을 모두 만들어 두고 각 이닝에 대입해서 최대 점수 기록.
>> 이닝마다 0이 3번 기록되면 다음 이닝으로 넘어가고 선수 순서 넘기기.
1,2,3,4 점수 기록해서 점수 올리기 >> 각 루에 있는 주자를 배열로? >> 하나하나 밀지 말고 경우 수를 나눠서 더하자.
 */
public class Main_17281_김덕진 {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] arr;		//이닝(행)과 각 선수 결과(열) 배열
	static int[] Player = new int[9];	//선수 번호 배열
	static boolean[] check;	//선수 조합 만들 때 방문 체크할 배열
	static int gameScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][9];
			
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Player[0] = 4;	//1번 선수(0번 인덱스)는 무조건 4번
		check = new boolean[10];	//1번 번호부터 9번까지 사용할거라 10까지 생성(0은 안씀)
		check[4] = true;	//4번은 이미 사용중
		
		perm(1);	//1번 인덱스부터 선수 번호를 매김 (순서가 있으니 순열)
		System.out.println(gameScore);
		
	}
	
	//선수 등번호순서 조합 만들기 매서드
	private static void perm(int idx) {
		if(idx == 9) {	//선수 조합 완성 되었을 때 게임 play
			int tmp = play();	//하나의 선수 조합으로 얻을 수 있는 점수 기록
			gameScore = Math.max(tmp, gameScore);	//최대 점수 기록
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if(check[i]) continue;	//이미 사용한 숫자는 pass
			check[i] = true;
			Player[idx] = i;
			perm(idx+1);
			check[i] = false;
			
		}
	
	}

	private static int play() {
		int zeroCnt = 0;
		int player = 1;		//선수 번호
		int Score = 0;
		for (int i = 0; i < N; i++) {
			
			int ground[] = new int[4];	//현재 출루 상태 배열(이닝 마다 초기화)
			
			while(true) {
				int PlayerIdx = -1;	//현재 차례 선수 인덱스 번호
				for (int j = 0; j < 9; j++) {
					if(Player[j] == player) {	//등번호에 맞는 선수 인덱스 찾기
						PlayerIdx = j;
						break;
					}
				}
				
				
				switch(arr[i][PlayerIdx]) {
				case 0 :
					zeroCnt++;
					break;
				case 1 :	//안타일때
					Score += ground[3];
					ground[3] = ground[2] == 1 ? 1 : 0;
					ground[2] = ground[1] == 1 ? 1 : 0;
					ground[1] = 1;
					break;
				case 2 :	//2루타 일 때
					Score += ground[3] + ground[2];
					ground[3] = ground[1] == 1? 1: 0;
					ground[1]=0; ground[2] = 1;
					break;
				case 3 :	//3루타 일 때
					Score += ground[3] + ground[2] + ground[1];
					ground[1] = 0; ground[2] = 0; ground[3] = 1;
					break;
				case 4 :
					Score += ground[3] + ground[2] + ground[1] + 1;
					ground[1]=0; ground[2]=0; ground[3]=0;
					break;
				}

				
				player++;	//다음 선수 등번호(아웃 당해도 다음 선수부터 해야되니까 밑보다 먼저 수행)
				if(player >= 10) player = 1;	//선수는 9번까지, 넘어가면 1번 선수부터. 
				
				if(zeroCnt == 3) {
					zeroCnt = 0;	//아웃 카운트 초기화
					break;	//아웃 3번이면 다음 이닝(행)으로
				}
			}
			
		}
		
		return Score;
		
	}

}
