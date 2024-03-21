import java.io.*;
import java.util.*;

public class Raspberries {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int k = Integer.parseInt(token.nextToken());
            int[] arr = new int[n];
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(token.nextToken());
            
            if (k == 2) {
                boolean hasEven = false;
                for (int i = 0; i < n; i++) if (arr[i]%2==0) hasEven = true;
                if (hasEven) pw.println(0);
                else pw.println(1);
            } else if (k == 3) {
                int minDiffFromThree = 2;
                for (int i = 0; i < n; i++) {
                    if (arr[i] % 3 == 0) {
                        minDiffFromThree = 0;
                        break;
                    } else minDiffFromThree = Math.min(minDiffFromThree, 3-(arr[i]%3));
                }
                pw.println(minDiffFromThree%3);
            } else if (k == 4) {
                int numEven = 0;
                int minDiffFromFour = 3;
                for (int i = 0; i < n; i++) {
                    if (arr[i]%4==0) {
                        numEven = 2;
                        break;
                    }
                    if (arr[i]%2==0) numEven++;
                    minDiffFromFour = Math.min(minDiffFromFour, 4-(arr[i]%4));
                }
                pw.println(Math.max(0, Math.min(2-numEven, minDiffFromFour)));
            } else {
                int minDiffFromFive = 4;
                for (int i = 0; i < n; i++) {
                    if (arr[i] % 5 == 0) {
                        minDiffFromFive = 0;
                        break;
                    } else minDiffFromFive = Math.min(minDiffFromFive, 5-(arr[i]%5));
                }
                pw.println(minDiffFromFive%5);
            }
        }
        pw.close();
    }
}