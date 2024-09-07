package SWEA;

import java.io.*;
import java.util.*;
/*
 * 고객은 도착하는 대로 1번부터 고객번호를 부여 받는다.
 * <접수 창구의 우선순위>는 아래와 같다.
 * 1. 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선 접수한다.
 * >> 도착한 순서대로 접수 창구에 들어감(입력 들어온 순서대로)
 * 2. 빈 창구가 여러 곳인 경우 접수 창구번호가 작은 곳으로 간다.
 * <정비 창구의 우선순위>는 아래와 같다.
 * 1. 먼저 기다리는 고객이 우선한다.
 * >> 접수창구에서 빠져 나온 순서대로
 * 2. 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우, 이용했던 접수 창구번호가 작은 고객이 우선한다.
 * >> 시간으로 우선 정리가 되어 있고 같은 시간 중에서도 접수번호 순으로 정렬 >> 다중 조건 정렬
 * 3. 빈 창구가 여러 곳인 경우 정비 창구번호가 작은 곳으로 간다.
 * 만약, 그런 고객이 없는 경우 -1을 출력한다.
 * 
 * 접수/정비 창구 모두 빈 창구 중에서는 번호가 낮은 것 부터 채움.(우선 순위 큐로 창구번호 관리)
 */
public class Solution_2477_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, M, K, A, B, receptionTime[], repairTime[];
	static PriorityQueue<Integer> receptionNs, repairNs;
	static PriorityQueue<Guest> reception, repair;
	static Queue<Guest> guests, waitRepair;
	
	static class Guest implements Comparable<Guest>{
		int gT;		//고객 도착 시간
		int gN;		//고객 번호
		int recN;	//이용한 접수창구 번호
		int recEnd;	//접수 완료 시간
		int repN;	//이용한 정비창구 번호
		int repEnd;	//정비 종료시간
		
		public Guest(int gT, int gN) {
			super();
			this.gT = gT;	
			this.gN = gN;	//고객 번호
		}
		
		@Override
		public int compareTo(Guest o) {
			if(this.recEnd > o.recEnd) return 1;	//접수 종료 시간으로 정렬
			else if(this.recEnd == o.recEnd) {	//접수 종료 시간이 같다면
				if(this.recN > o.recN) return 1;	//접수 창구 번호 순으로 정렬
				else return -1;
			}
			else return -1;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//접수 창구의 개수
			M = Integer.parseInt(st.nextToken());	//정비 창구의 개수
			K = Integer.parseInt(st.nextToken());	//고객의 수
			A = Integer.parseInt(st.nextToken());	//찾아야 하는 접수 창구번호
			B = Integer.parseInt(st.nextToken());	//찾아야 하는 정비 창구번호
			
			receptionTime = new int[N+1];	//접수 창구(1번 부터 관리)별 걸리는 시간
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				receptionTime[i] = Integer.parseInt(st.nextToken());
			}
			
			repairTime = new int[M+1];	//정비 창구(1번 부터 관리)별 걸리는 시간
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				repairTime[i] = Integer.parseInt(st.nextToken());
			}
			
			guests = new ArrayDeque<>();	//손님이 도착하는 시간
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				//고객 도착시간, 고객 번호 , 이용한 접수창구 번호, 접수가 끝나는 시간, 이용할 정비창구 번호, 정비 끝나는 시간
				int tk = Integer.parseInt(st.nextToken()); 
				Guest guest = new Guest(tk, i);
				guests.offer(guest);
			}
			
			receptionNs = new PriorityQueue<>();
			for (int i = 1; i <= N; i++) {
				receptionNs.add(i);
			}
			
			repairNs = new PriorityQueue<>();
			for (int i = 1; i <= M; i++) {
				repairNs.add(i);
			}
			
			reception = new PriorityQueue<>();	// 접수 창구
			waitRepair = new ArrayDeque<>();	//정비 대기큐
			repair = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.repEnd, o2.repEnd));  //정비 창구
			
			int time = 0;
			int ans = 0;	//고객 번호 합 변수
			//빈 자리를 먼저 만들고(시간되어서 나갈 애들 내보내고) 빈자리에 채우는건 나중에
			//도착할 손님도 더이상 없고 접수창구도 비었고 정비대기중인 사람도 없을 때까지 (접수창구 번호, 정비창구 번호만 알면 되기 때문에 정비 창구가 비었는지는 상관 없음)
			while(!guests.isEmpty() || !reception.isEmpty() || !waitRepair.isEmpty()) {
				//정비시간이 끝나면 배출하는 블록
				while(!repair.isEmpty()) {	//정비창구에 사람이 있을 때만 가동
					if(repair.peek().repEnd == time) {	//정비 끝났으면
						repairNs.offer(repair.poll().repN);	//정비창구 돌려주고 내보내고 끝
						continue;
					}else {
						break;
					}
				}
				
				//대기열에서 정비창구로 보내는 블록
				while(!waitRepair.isEmpty() && !repairNs.isEmpty()) {
					Guest guest = waitRepair.poll();	//대기열 제일 앞 빼내줌(이미 시간/접수창구별로 정렬 되어있는걸 가져온 것이기 때문에 제일 앞에 있는 애 빼면 됨)
					int repairN = repairNs.poll();	//가능한 정비 창구 번호 가져오기
					guest.repN = repairN;	//정비 창구 번호
					guest.repEnd = time + repairTime[repairN];	//현재 시간 + 정비 창구별 시간
					repair.offer(guest);	//정비 시작
					if(guest.recN == A && guest.repN == B) ans += guest.gN;	//출력 조건 달성시 합산
					continue;	//접수창구에 끝날 애들이 여러명 있을 수 있음
				}
				
				
				//접수창구에서 정비대기열로 보내는 블록
				while(!reception.isEmpty()) {
					if(reception.peek().recEnd == time) {	//접수 완료 시간이 되었다면
						Guest guest = reception.poll();
						receptionNs.offer(guest.recN);	//이용했던 접수창고 돌려주기
						waitRepair.offer(guest);	//대기열로 이동
						continue;
					}else {
						break;
					}
				}
				
				
				//고객 도착 후 접수창구로 들어가는 블록
				while(!receptionNs.isEmpty() && !guests.isEmpty()) {	//접수 창구가 비어 있고 고객 도착줄도 있을 때만 가동
					if(guests.peek().gT <= time) {	//고객 도착 시간이 현재 시간보다 크거나 같으면(딱 도착 했거나 기다리고 있다면)
						Guest guest = guests.poll();	//고객 도착줄에서 빼고
						int receptionN = receptionNs.poll();	//이용할 접수 창구 번호
						guest.recN = receptionN; 
						guest.recEnd = time + receptionTime[receptionN];	//현재 시간 + 접수 창구 번호에 따라 걸리는 시간
						//[0]: 접수가 끝나는 시간, [1]: 이용한 접수창구 번호, [2]: 이용할 정비창구 번호(아직은 모르니까 -1), [3]: 정비 끝나는 시간, [4]: 고객 번호
						reception.offer(guest);
						continue;	//동시에 도착한 사람이 있을 수 있어서 컨티뉴
					}else {
						break;
					}
				}
				
				time++;
			}
			
			//시뮬 종료 후 정답 출력
			if(ans == 0) ans = -1;
			System.out.println("#" + tc + " " +ans);
		}
	}

	
	
}
