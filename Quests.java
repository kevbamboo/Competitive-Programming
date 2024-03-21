import java.io.*;
import java.util.*;

public class Quests {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int k = Integer.parseInt(token.nextToken());
            int[] a_i = new int[n];
            int[] b_i = new int[n];
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) a_i[i] = Integer.parseInt(token.nextToken());
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) b_i[i] = Integer.parseInt(token.nextToken());
            
            int[] sum = new int[n];
            sum[0] = a_i[0];
            for (int i = 1; i < n; i++) sum[i] = sum[i-1]+a_i[i];
            int[] max = new int[n];
            max[0] = b_i[0];
            for (int i = 1; i < n; i++) {
                max[i] = Math.max(max[i-1], b_i[i]);
            }
            
            long maxEXP = 0;
            for (int i = 0; i < Math.min(n,k); i++) {
                maxEXP = Math.max(maxEXP, (long)sum[i]+max[i]*(k-i-1));
            }
            pw.println(maxEXP);
        }
        pw.close();
    }
}