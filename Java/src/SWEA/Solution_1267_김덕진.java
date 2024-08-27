package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1267_김덕진 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, arr[][];
	static List<Integer> list = new ArrayList<>();
	static boolean check[][];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			arr = new int[V+1][V+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());	//from을 해야
				int to = Integer.parseInt(st.nextToken());	//to를 한다.
				arr[from][to] = 1;
			}
			
//			for (int i = 0; i < V; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			list.clear();
			check = new boolean[V+1][V+1];
			//열 탐색 중에 아무 겂도 없으면, to가 없어도 되는 작업, 선행 작업이 없다.
			boolean flag;
			for (int j = 1; j <= V; j++) {
				flag = false;
				for (int i = 1; i <= V; i++) {
					if(arr[i][j] == 1) {	//to 작업이 있으면 flag 변수 갱신
						flag = true;
						break;
					}
				}
				if(!flag) {		//하나의 to 작업도 없었다면
					if(!list.contains(j)) list.add(j);	//선행 작업 없어도 되는 작업 번호 넣어주고
					afterWork(j); 	//이 작업으로 시행되는 작업들 추가
				}
				
			}
			
			sb.append("#"+tc+" ");
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i)+" ");
			}
			sb.append("\n");
			System.out.println(sb);
		}
	}



	private static void afterWork(int before) {
		//1. 여기서는 선행 작업(from)을 시행해야 할 수 있는 작업들을 봐야하기 때문에 행 탐색
		//2. 하지만 인수로 넘겨준 하나의 작업에 대해서만 살펴볼 것이기 때문에 for문은 하나.
		//3. 작업이 실행되려면, 열 탐색 시 1이 위치한 인덱스가 모두 true여야 함
		for (int i = 1; i <= V; i++) {
			if(arr[i][before] == 1 && !check[i][before]) {	//필요한 선행 작업이 있는데 아직 완료되지 않은 경우 종료
				return;
			}
		}
		
		
		if(!list.contains(before)) list.add(before);	//인수로 넘겨받은 선행 작업으로 인해 할 수 있는 작업을 리스트에 추가 시켜주고
		
		
		for (int i = 1; i <= V; i++) {
			if(arr[before][i] == 1) {
				check[before][i] = true;	//작업 했으니 방문 표시
				afterWork(i);	//이로 인해 할 수 있는 다른 일 다시 재귀 호출.
			}
		}
		
	}

}
