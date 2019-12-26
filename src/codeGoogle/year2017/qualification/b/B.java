package codeGoogle.year2017.qualification.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// https://code.google.com/codejam/contest/3264486/dashboard#s=p1&a=1
public class B {
  public static void main(String[] args) throws Exception {
    String file = "src/year2017/qualification/b/" + "B-large-practice.in";
    BufferedReader br = new BufferedReader(new FileReader(new File(file)));

    int T = Integer.parseInt(br.readLine());

    for (int C = 0; C < T; C++) {
      String N = br.readLine();
      
      int i = 1;
      int[] credits = new int[N.length()];
      while (i < N.length()) {
        int v1 = Integer.parseInt(String.valueOf(N.charAt(i - 1)));
        int v2 = Integer.parseInt(String.valueOf(N.charAt(i)));
        
        if (v1 > v2) {
          if (credits[i - 1] == 0)
            v1--;
          
          v2 = 9;
          N = N.substring(0, i - 1) + String.valueOf(v1) + String.valueOf(v2) + N.substring(i + 1);
          
          credits[i]++;
          i = 1;
        } else {
          i++;
        }
      }
      
      System.out.printf("Case #%d: %s\n", C + 1, N.replaceFirst("0", ""));
    }
  }
}
