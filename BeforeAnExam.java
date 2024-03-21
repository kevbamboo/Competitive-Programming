import java.io.*;
import java.util.*;

public class BeforeAnExam {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        StringTokenizer token = new StringTokenizer(br.readLine());
        int days = Integer.parseInt(token.nextToken());
        int hours = Integer.parseInt(token.nextToken());
        int[][] minmaxTime = new int[days][2];
        for (int i = 0; i < days; i++) {
            token = new StringTokenizer(br.readLine());
            minmaxTime[i][0] = Integer.parseInt(token.nextToken());
            minmaxTime[i][1] = Integer.parseInt(token.nextToken());
        }
        
        int minSum = 0;
        for (int i = 0; i < days; i++) minSum += minmaxTime[i][0];
        int maxSum = 0;
        for (int i = 0; i < days; i++) maxSum += minmaxTime[i][1];
        
        if (minSum > hours || maxSum < hours) pw.println("NO");
        else {
            int curSum = minSum;
            pw.println("YES");
            for (int i = 0; i < days; i++) {
                if (curSum + minmaxTime[i][1] - minmaxTime[i][0] < hours) {
                    curSum += minmaxTime[i][1] - minmaxTime[i][0];
                    minmaxTime[i][0] = minmaxTime[i][1];
                } else {
                    minmaxTime[i][0] += hours-curSum;
                    break;
                }
            }
            
            pw.print(minmaxTime[0][0]);
            for (int i = 1; i < days; i++) {
                pw.print(" " + minmaxTime[i][0]);
            }
            pw.println();
        }
        pw.close();
    }
}