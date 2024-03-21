import java.io.*;
import java.util.*;

public class Chemistry {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int k = Integer.parseInt(token.nextToken());
            String s = br.readLine();
            int[] count = new int[26];
            for (int i = 0; i < n; i++) count[s.charAt(i)-'a']++;
            int numOdd = 0;
            for (int i = 0; i < 26; i++) if (count[i]%2==1) numOdd++;
            pw.println((numOdd-k <= 1) ? "YES" : "NO");
        }
        pw.close();
    }
}