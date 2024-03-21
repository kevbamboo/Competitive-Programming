import java.io.*;
import java.util.*;

public class ABFlipping {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int len = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int start = len;
            int end = -1;
            for (int i = 0; i < len; i++) if (s.charAt(i) == 'A') {
                start = i;
                break;
            }
            for (int i = len-1; i >= 0; i--) if (s.charAt(i) == 'B') {
                end = i;
                break;
            }
            pw.println(Math.max(0, end-start));
        }
        
        pw.close();
    }
}