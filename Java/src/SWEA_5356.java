package SWEA;

import java.io.*;
import java.util.Arrays;

public class ploblem_5356 {

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[][] answer = new char[5][15];
    for(int i=0; i<5; i++) {
      for(int j=0; j<15; j++) {
        answer[i][j] = '!';
      }
    }
    
    for(int i=0; i<5; i++) {
      String s = br.readLine();
      for(int j=0; j<s.length(); j++) {
        answer[i][j] = s.charAt(j);
      }
    }
    
    StringBuilder sb = new StringBuilder();
    int cnt = 0;
    for(int i=0; i<15; i++) {
      if(cnt == 5) {
        break;
      }
      for(int j=0; j<5; j++) {
        if (answer[j][i] != '!') {
          sb.append(answer[j][i]);
        }else{
          cnt++;
        }
      }
    }
    
    System.out.println(sb);
  }

}
