import java.io.*;
import java.util.*;

public class SortingWithTwos {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int len = Integer.parseInt(br.readLine());
            StringTokenizer token = new StringTokenizer(br.readLine());
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) arr[i] = Integer.parseInt(token.nextToken());
            boolean isNO = false;
            int i = 1;
            while (i < len) {
                for (int j = i+1; j < i*2; j++) {
                    if (j == len) break;
                    if (arr[j] < arr[j-1]) {
                        isNO = true;
                        break;
                    }
                }
                i*= 2;
            }
            if (!isNO) pw.println("YES");
            else pw.println("NO");
        }
        
        pw.close();
    }
}