import java.io.*;
import java.util.*;

public class RomanticGlasses {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer token = new StringTokenizer(br.readLine());
            ArrayList<Long> list = new ArrayList<Long>();
            long[] prefix = new long[n+1];
            prefix[0] = 0;
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    prefix[i+1] = prefix[i]+Long.parseLong(token.nextToken());
                }
                else {
                    prefix[i+1] = prefix[i]-Long.parseLong(token.nextToken());
                }
            }
            HashSet<Long> set = new HashSet<Long>();
            boolean yes = false;
            for (int i = 0; i <= n; i++) {
                if (set.contains(prefix[i])) {
                    yes = true;
                    break;
                }
                else set.add(prefix[i]);
            }
            pw.println(yes ? "YES" : "NO");
        }
        pw.close();
    }
}