import java.io.*;
import java.util.*;

public class DontTryToCount {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int m = Integer.parseInt(token.nextToken());
            String x = br.readLine();
            String s = br.readLine();
            if (x.contains(s)) pw.println(0);
            else {
                boolean found = false;
                int numDouble = 0;
                do {
                    String newX = x+x;
                    x = newX;
                    numDouble++;
                    if (x.contains(s)) {
                        found = true;
                        break;
                    }
                } while (x.length() <= m*2);
                pw.println(found ? numDouble : -1);
            }
        }
        pw.close();
    }
}