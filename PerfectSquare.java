import java.io.*;
import java.util.*;

public class PerfectSquare {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            char[][] matrix = new char[n][n];
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) matrix[i][j] = s.charAt(j);
            }
            int min = 0;
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n/2; j++) {
                    int first = matrix[i][j]-'a';
                    int second = matrix[n-1-j][i]-'a';
                    int third = matrix[n-1-i][n-1-j]-'a';
                    int fourth = matrix[j][n-1-i]-'a';
                    int max = Math.max(first,Math.max(second,Math.max(third,fourth)));
                    min += 4*max-first-second-third-fourth;
                }
            }
            pw.println(min);
        }
        pw.close();
    }
}