package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//홀수 일때, 정가운데(홀수//2)
//짝수 일때, 왼쪽 값(짝수//2 - 1)
public class Main_BJ_1665_시간초과 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, x, arr[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		int ans;	//말해야 하는 중간값 위치
		int position;	//삽입 위치
		Arrays.fill(arr, Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {	//i가 배열의 크기라고 생각
			x = Integer.parseInt(br.readLine());
			
			//빠른 삽입을 위해 이진탐색 수행
			position = Arrays.binarySearch(arr, x); 
			
			
			//탐색이 안돼서 삽입 위치 값이 나왔을 때
			if(position < 0) {
				position = Math.abs(position) - 1;
				
				if(arr[position] != Integer.MAX_VALUE) {	//삽입 위치에 수가 이미 있다면
					while(true) {	//배열 정리 될 때까지 수행
						
						int tmp = arr[position];	//그 위치에 있던 수 빼내고
						arr[position] = x;	//넣으려던 자리에 넣고
						x = tmp;	//swap
						position = Math.abs(Arrays.binarySearch(arr, tmp)) - 1;	//빼낸 수로 다시 이진탐색
						
						//빼낸 수의 위치를 다시 잡는 과정에서 정리가 됐으면 그 자리에 넣고 종료
						if(arr[position] == Integer.MAX_VALUE) {
							arr[position] = tmp;
							break;
						}
					}
				}
				//없으면 그냥 그 위치에 삽입
				else arr[position] = x;	
			}
			
			//탐색에 성공해서 양수 값이 나온 경우 다 땡기기
			else {
				int end = Arrays.binarySearch(arr, Integer.MAX_VALUE);
				while(end != position) {
					arr[end] = arr[end-1];
					end--;
				}
			}
			
			//말해야 하는 위치 값
			if(i%2 == 0) ans = (i+1)/2;
			else ans = (i+1)/2 - 1;
			
			
			System.out.println(arr[ans]);
		}
		
	}

}
