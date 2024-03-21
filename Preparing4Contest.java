import java.io.*;
import java.util.*;

public class Preparing4Contest {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int k = Integer.parseInt(token.nextToken());
            int[] arr = new int[n];
            for (int i = 0; i < k; i++) arr[i] = i+1;
            arr[k] = n;
            for (int i = k+1; i < n; i++) arr[i] = n-i+k;
            
            pw.print(arr[0]);
            for (int i = 1; i < n; i++) pw.print(" " + arr[i]);
            pw.println("");
        }
        pw.close();
    }
}