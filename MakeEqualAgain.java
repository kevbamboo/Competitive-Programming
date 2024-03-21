import java.io.*;
import java.util.*;

public class MakeEqualAgain {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(token.nextToken());
            int front = 1;
            for (int i = 1; i < n; i++) {
                if (arr[i] == arr[i-1]) front++;
                else break;
            }
            int back = 1;
            for (int i = n-2; i >= 0; i--) {
                if (arr[i] == arr[i+1]) back++;
                else break;
            }
            if (n == 1) pw.println(0);
            else if (front == n) pw.println(0);
            else if (arr[0] == arr[n-1]) pw.println(n-front-back); // doesn't work for n = 1 or if all elements are equal
            else pw.println(n-Math.max(front,back));
        }
        pw.close();
    }
}