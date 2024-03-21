import java.io.*;
import java.util.*;

public class RatingIncrease {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            String s = br.readLine();
            int secondNumStart = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != '0') {
                    secondNumStart = i;
                    break;
                }
            }
            if (Integer.parseInt(s.substring(0, secondNumStart)) >= 
            Integer.parseInt(s.substring(secondNumStart)))
                pw.println("-1");
            else pw.println(Integer.parseInt(s.substring(0, secondNumStart)) + " " + Integer.parseInt(s.substring(secondNumStart)));
        }
        
        pw.close();
    }
}