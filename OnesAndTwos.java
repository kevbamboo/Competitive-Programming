import java.io.*;
import java.util.*;

public class OnesAndTwos {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int q = Integer.parseInt(token.nextToken());
            
            int[] arr = new int[n];
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(token.nextToken());
            
            while (q-- > 0) {
                token = new StringTokenizer(br.readLine());
                if (Integer.parseInt(token.nextToken()) == 1) {
                    int want = Integer.parseInt(token.nextToken());
                    int sum = 0;
                    int frontIndex = 0;
                    boolean can = false;
                    for (int i = 0; i < n; i++) {
                        sum += arr[i];
                        if (sum == want) {
                            break;
                        }
                        if (sum > want) {
                            sum -= arr[frontIndex];
                            frontIndex++;
                        }
                    }
                    if (sum == want) pw.println("YES");
                    else pw.println("NO");
                } else {
                    arr[Integer.parseInt(token.nextToken())-1] = Integer.parseInt(token.nextToken());
                }
            }
        }
        
        
        pw.close();
    }
}