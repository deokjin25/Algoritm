package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_김덕진 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine()); // 괄호 개수
            String whole = br.readLine(); // 괄호 입력

            Stack<Character> stack = new Stack<>();
            boolean isBalanced = true;

            for (int i = 0; i < N; i++) {
                char compare = whole.charAt(i);
                
                // 열리는 괄호일 때
                if (compare == '(' || compare == '{' || compare == '[' || compare == '<') {
                    stack.push(compare);
                } 
                // 닫히는 괄호일 때
                else {
                    if (stack.isEmpty()) {
                        isBalanced = false;
                        break;
                    }
                    char top = stack.pop();
                    if ((compare == ')' && top != '(') ||
                        (compare == '}' && top != '{') ||
                        (compare == ']' && top != '[') ||
                        (compare == '>' && top != '<')) {
                        isBalanced = false;
                        break;
                    }
                }
            }

            if (!stack.isEmpty()) {
                isBalanced = false;
            }

            int answer = isBalanced ? 1 : 0;
            System.out.println("#" + tc + " " + answer);
        }
    }
}
