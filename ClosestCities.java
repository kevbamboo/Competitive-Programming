import java.io.*;
import java.util.*;

public class ClosestCities {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(token.nextToken());
            int numQ = Integer.parseInt(br.readLine());
            long[] forwardPrefix = new long[n];
            forwardPrefix[0] = 0;
            forwardPrefix[1] = 1;
            for (int i = 2; i < n; i++) {
                forwardPrefix[i] = forwardPrefix[i-1] + ((arr[i-1]-arr[i-2] > arr[i]-arr[i-1]) ? 1 : arr[i]-arr[i-1]);
            }
            long[] backwardPrefix = new long[n];
            backwardPrefix[n-1] = 0;
            backwardPrefix[n-2] = 1;
            for (int i = n-3; i >= 0; i--) {
                backwardPrefix[i] = backwardPrefix[i+1] + ((arr[i+2]-arr[i+1] > arr[i+1]-arr[i]) ? 1 : arr[i+1]-arr[i]);
            }
            while (numQ-- > 0) {
                token = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(token.nextToken());
                int b = Integer.parseInt(token.nextToken());
                pw.println((a < b) ? forwardPrefix[b-1]-forwardPrefix[a-1] : backwardPrefix[b-1]-backwardPrefix[a-1]);
            }
        }
        pw.close();
    }
}