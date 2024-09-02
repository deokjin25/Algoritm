package BJ;

import java.io.*;

public class Main_BJ_1339 {
    static boolean check[];
    static int arr[], N, alphabet[], max, cnt;
    static String[] word;
    static int[] value;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        alphabet = new int[26];  // 알파벳의 개수만큼 크기를 잡음

        N = Integer.parseInt(br.readLine());
        word = new String[N];
        value = new int[26]; // 각 알파벳의 값을 저장할 배열

        for (int i = 0; i < N; i++) {
            word[i] = br.readLine();
            for (int j = 0; j < word[i].length(); j++) {
                int index = word[i].charAt(j) - 'A';
                alphabet[index] = 1; // 해당 알파벳이 등장함을 기록
            }
        }

        // 등장한 알파벳의 개수(cnt) 계산
        cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] == 1) cnt++;
        }

        arr = new int[cnt];
        check = new boolean[10];
        max = Integer.MIN_VALUE;

        perm(0);
        System.out.println(max);
    }

    private static void perm(int idx) {
        if (idx == cnt) {
            int tmp = cnt - 1;

            // 각 알파벳에 대해 값 할당
            for (int i = 0; i < 26; i++) {
                if (alphabet[i] == 1) {
                    value[i] = arr[tmp--];
                }
            }

            // 각 단어를 숫자로 변환하고 합 계산
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int num = 0;
                for (int j = 0; j < word[i].length(); j++) {
                    int index = word[i].charAt(j) - 'A';
                    num = num * 10 + value[index];
                }
                sum += num;
            }

            max = Math.max(max, sum);
            return;
        }

        for (int i = 9; i >= 10 - cnt; i--) {
            if (!check[i]) {
                check[i] = true;
                arr[idx] = i;
                perm(idx + 1);
                check[i] = false;
            }
        }
    }
}
