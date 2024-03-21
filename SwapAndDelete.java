import java.io.*;
import java.util.*;

public class SwapAndDelete {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            String s = br.readLine();
            int num0 = 0;
            int num1 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') num0++;
                else num1++;
            }
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) == '0') {
                    if (num1 == 0) break;
                    num1--;
                } else {
                    if (num0 == 0) break;
                    num0--;
                }
                
                i++;
            }
            pw.println(s.length()-i);
        }
        
        pw.close();
    }
}