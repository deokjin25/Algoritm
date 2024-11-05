package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Stack<int[]> stack = new Stack<>();
        long result = 0;
        
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            int count = 1;
            
            // 스택의 top보다 현재 height가 크거나 같다면 쌍을 계산하고 제거
            while (!stack.isEmpty() && stack.peek()[0] <= height) {
                result += stack.peek()[1];
                
                // 같은 키면 중복 가능하므로 카운트
                if (stack.peek()[0] == height) {
                    count += stack.pop()[1];
                } else {
                    stack.pop();
                }
            }
            
            // 스택이 비어 있지 않으면 현재 사람과 스택 top 사람도 서로 볼 수 있음
            if (!stack.isEmpty()) result++;

            // 현재 키와 빈도를 스택에 삽입
            stack.push(new int[]{height, count});
        }
        
        System.out.println(result);
    }
}