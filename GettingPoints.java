import java.io.*;
import java.util.*;

public class GettingPoints {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            long n = Long.parseLong(token.nextToken());
            long P = Long.parseLong(token.nextToken());
            long lesson = Long.parseLong(token.nextToken());
            long task = Long.parseLong(token.nextToken());
            long numPracticals = (n+6)/7;
            long totalDoublePracticalDaysPoints = numPracticals/2*(lesson+2*task);
            if (totalDoublePracticalDaysPoints >= P) {
                pw.println(n-numPracticals/2+(totalDoublePracticalDaysPoints-P)/(lesson+2*task));
            } else if (numPracticals%2 == 1 && totalDoublePracticalDaysPoints+lesson+task >= P) {
                pw.println(n-numPracticals/2-1);
            } else {
                long totalPracticalDaysPoints = totalDoublePracticalDaysPoints;
                if (numPracticals%2 == 1) {
                    numPracticals++;
                    totalPracticalDaysPoints = totalDoublePracticalDaysPoints+lesson+task;
                }
                pw.println(n-(numPracticals/2+(long)Math.ceil((P-totalPracticalDaysPoints)*1.0/lesson)));
            }
        }
        pw.close();
    }
}