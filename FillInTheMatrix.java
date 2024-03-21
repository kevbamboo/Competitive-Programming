import java.io.*;
import java.util.*;

public class FillInTheMatrix {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int m = Integer.parseInt(token.nextToken());
            int[][] M = new int[n][m];
            
            if (m == 1) {
                pw.println(0);
                for (int i = 0; i < n; i++) {
                    M[i][0] = 0;
                }
            }
            else if (n-m < -1) {
                pw.println(n+1);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        M[i][j] = (i+j+1)%m;
                    }
                }
            } else {
                pw.println(m);
                for (int i = 0; i < m-1; i++) {
                    for (int j = 0; j < m; j++) {
                        M[i][j] = (i+j)%m;
                    }
                }
                for (int i = m-1; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        M[i][j] = M[0][j];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                pw.print(M[i][0]);
                for (int j = 1; j < m; j++) {
                    pw.print(" " + M[i][j]);
                }
                pw.println("");
            }
        }
        pw.close();
    }
}