package BJ;

import java.io.*;
import java.util.*;

/*
 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 
 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
 * 같은 적이 여러 궁수에게 공격당할 수 있다.
 */

public class Main_17135_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, D, map[][], simulationMap[][], max, monster, kill, monsterTmp;
	static int archer[];
	static boolean attack[], monsterAttack[][];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		monster = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) monster++;	//모든 몬스터의 수 합산
			}
		}
		
		archer = new int[3];
		max = Integer.MIN_VALUE;
		position(0,1);	//궁수 자리 조합찾기(1번 자리 부터 시작)
		System.out.println(max);
		
	}

	private static void position(int n, int idx) {
		if(n == 3) {	//3궁수 배치 완료
			kill = 0;	//한 번의 시뮬레이션마다 잡는 수 초기화
			simulationMap = new int[N][M];	//매 시뮬마다 시뮬레이션 맵으로 진행하기(static 원본 훼손 방지) 
			for (int i = 0; i < N; i++) {
				simulationMap[i] = map[i].clone();
			}
			monsterTmp = monster;	//원본 훼손 방지
			simulation();
			return;
		}
		for (int i = idx; i <= M; i++) {
			archer[n] = i;
			position(n+1, i+1);
		}
	}

	private static void simulation() {
		//몬스터가 다 없어질 때까지
		while(monsterTmp > 0) {
			//공격 후 몬스터 이동
			monsterAttack = new boolean[N][M];
			for (int i = 0; i < 3; i++) {
				Range(i);
			}
			
			//Range 매서드에서 피해 판정된 몬스터들 처리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(monsterAttack[i][j]) {
						simulationMap[i][j] = 0;
						kill++;
						monsterTmp--;
					}
				}
			}
			
			toCastle();		//공격 이후 몬스터 이동
		}
				
		//모든 진행 종료후
		max = Math.max(max, kill);	//현재 시뮬 결과와 지금까지의 결과 비교 저장
		
	}
	
	
	//제일 가까운 적, 거리가 같다면 왼쪽에서부터 타격
	private static void Range(int p) {
		//궁수의 x좌표 N고정, y좌표는 archer의 원소-1
		int archerX = N;
	    int archerY = archer[p] - 1;
	    int targetX = -1, targetY = -1;		//목표로 삼을 몬스터 좌표
	    int minDistance = D + 1; // 최대 거리 + 1

	    for (int x = N - 1; x >= 0; x--) {
	        for (int y = 0; y < M; y++) {
	            if (simulationMap[x][y] == 1) {
	                int distance = Math.abs(archerX - x) + Math.abs(archerY - y);
	                if (distance <= D) {	//사정거리 내에 있더라도 제일 가까운지, 왼쪽에 있는지 확인 필요
	                    if (distance < minDistance || (distance == minDistance && y < targetY)) {
	                        minDistance = distance;
	                        targetX = x;
	                        targetY = y;
	                    }
	                }
	            }
	        }
	    }

	    if (targetX != -1 && targetY != -1) {
	        monsterAttack[targetX][targetY] = true;
	    }
	}
	

	private static void toCastle() {
		//밑에서부터 열 탐색
		for (int j = 0; j < M; j++) {
			for (int i = N-1; i >= 0; i--) {
				if(simulationMap[i][j] == 0) continue;
				
				if(i+1 == N) {		//성으로 몬스터 입성
					simulationMap[i][j] = 0;	//해당자리 0 갱신
					monsterTmp--;
					continue;
				}
				simulationMap[i+1][j] = simulationMap[i][j];	//밑으로 전진.
				simulationMap[i][j] = 0;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
