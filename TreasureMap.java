import java.io.*;
import java.util.*;

public class TreasureMap {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            int k = Integer.parseInt(token.nextToken());
            
            if (y <= x) {
                pw.println(x);
            } else if (x+k >= y) {
                pw.println(y);
            } else {
                pw.println((x+k)+2*(y-x-k));
            }
        }
        pw.close();
    }
}