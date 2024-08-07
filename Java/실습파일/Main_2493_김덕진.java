//import java.io.*;
//import java.util.*;
//
//public class Main_2493_김덕진 {
//
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine()); //입력 자리 수
//				
//		Stack<int[]> arr = new Stack<>();	//[막대기 위치, 높이 값]을 스택에 쌓자.
//		String[] inLine = br.readLine().split(" ");	//[입력 받은 막대기 배열]
//		int[] answer = new int[N];	//정답 배열
//		Arrays.fill(answer, 0); //0 초기화
//		
//		for(int i=N-1; i>-1; i--) {				
//			int[] x = new int[]{i,Integer.parseInt(inLine[i])}; //비교 할 막대기
//			
//			while(!arr.isEmpty()) {
//				int[] back = arr.pop();	//스택에 쌓여 있는 값(인덱스 상 뒤에 있는 막대기)
//				//스택에 쌓인 값과 앞에 있는 막대기를 비교
//				if(x[1] >= back[1]) {	//앞 막대기가 더 크거나 같으면
//					answer[back[0]] = x[0]+1;	//스택에서 뽑은 막대기 자리(인덱스)에 앞자리 인덱스(위치는 +1) 생성
//				}else {	//스택에 쌓여 있는게 더 크다면
//					arr.push(back); //빼둔 값 다시 집어 넣고 종료
//					break;
//				}				
//			}
//			arr.push(x); //스택 비교가 끝난 후 비교값은 앞 막대기와 비교해야 하니까 스택 삽입
//		}
//		for(int i=0; i<N; i++) {
//			System.out.printf("%d ",answer[i]);
//		}
//	}
//}

import java.io.*;
import java.util.*;

public class Main_2493_김덕진 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 입력 자리 수
                
        Stack<int[]> stack = new Stack<>(); // [막대기 위치, 높이 값]을 스택에 쌓자.
        int[] answer = new int[N]; // 정답 배열
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) { 
            int height = Integer.parseInt(st.nextToken());
            
            while (!stack.isEmpty() && stack.peek()[1] < height) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                answer[i] = 0;
            } else {
                answer[i] = stack.peek()[0] + 1;
            }
            
            stack.push(new int[]{i, height});
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(answer[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}
