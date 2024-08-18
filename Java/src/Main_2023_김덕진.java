import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_김덕진 {
    
    static int N;  // 자릿수
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        // 한 자리 수 소수부터 시작 (2, 3, 5, 7만 가능)
        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
        
        // 결과 출력
        System.out.println(sb);
    }

    // DFS로 자릿수별 소수 생성
    static void dfs(int num, int depth) {
        if (depth == N) {	//목표 자리수에 도달하면 종료(여기 까지 온 소수들은 신비한 소수 조건 충족한 수들.)
            if (isPrime(num)) {
                sb.append(num).append("\n");
            }
            return;
        }

        // 다음 자릿수로 확장
        for (int i = 1; i < 10; i += 2) {  // 짝수는 소수가 될 수 없으므로 2를 제외하고는 홀수만 탐색
        	//신비한 소수는 왼쪽 자리부터 하나씩 봐도 모두 소수 이므로 소수로 판별 된 수를 10을 곱해서 +i 하는 방식으로 다시 탐색
            int nextNum = num * 10 + i;		
            if (isPrime(nextNum)) {
                dfs(nextNum, depth + 1);
            }
        }
    }

    // 소수 판별 함수(루트 값 까지만 탐색하면 충분하다.)
    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;		//나머지가 0이라는 것은 소수가 아님.
        }
        return true;	//위에서 걸러지지 않았다면 소수 o
    }
}
