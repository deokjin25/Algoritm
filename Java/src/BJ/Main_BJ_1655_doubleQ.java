package BJ;

import java.io.*;
import java.util.*;

public class Main_BJ_1655_doubleQ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        // 최대 힙과 최소 힙 선언
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            
            // 최대 힙과 최소 힙 균형 맞추기
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            
            // 최대 힙의 최대값이 최소 힙의 최소값보다 큰 경우 교환
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int maxHeapRoot = maxHeap.poll();
                int minHeapRoot = minHeap.poll();
                maxHeap.offer(minHeapRoot);
                minHeap.offer(maxHeapRoot);
            }
            
            // 현재 중앙값 출력 (최대 힙의 루트가 중앙값)
            sb.append(maxHeap.peek()).append("\n");
        }
        
        System.out.print(sb);
    }
}
