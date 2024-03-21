import java.io.*;
import java.util.*;
 
public class SumOfProgression {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int q = Integer.parseInt(token.nextToken());
            long[] arr = new long[n];
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(token.nextToken());
            
            int sqrt = (int)Math.sqrt(n);
            long[][][] squareRootSuffix = new long[sqrt+1][][];
            long[][][] allSuffix = new long[sqrt+1][][];
            for (int i = 1; i <= sqrt; i++) {
                long[][] jump = new long[i][];
                long[][] differenceSuffix = new long[i][];
                for (int j = n-1; j >= n-i; j--) {
                    int len = j/i+1;
                    long[] suffix = new long[len];
                    suffix[len-1] = arr[j];
                    for (int k = len-2; k >= 0; k--) {
                        suffix[k] = suffix[k+1]+arr[j-i*(len-1-k)];
                    }
                    long[] suffixSuffix = new long[len];
                    suffixSuffix[len-1] = suffix[len-1];
                    for (int k = len-2; k >= 0; k--) {
                        suffixSuffix[k] = suffixSuffix[k+1]+suffix[k];
                    }
                    jump[n-1-j] = suffixSuffix;
                    differenceSuffix[n-1-j] = suffix;
                }
                squareRootSuffix[i] = jump;
                allSuffix[i] = differenceSuffix;
            }
            
            long[] ret = new long[q];
            for (int i = 0; i < q; i++) {
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken())-1;
                int d = Integer.parseInt(token.nextToken());
                int k = Integer.parseInt(token.nextToken());
                if (d <= sqrt) {
                    int secondIndex = (n-1)-(s+((n-1-s)/d)*d);
                    int thirdIndex = (s-(s%d))/d;
                    long toEnd = squareRootSuffix[d][secondIndex][thirdIndex];
                    if (s+d*(k-1)+d < n) {
                        int numExtraTerms = ((s+((n-1-s)/d)*d)-(s+d*(k-1)))/d;
                        long extra = squareRootSuffix[d][secondIndex][thirdIndex+k];
                        extra += ((long)k)*allSuffix[d][secondIndex][thirdIndex+k];
                        ret[i] = toEnd-extra;
                    }
                    else {
                        ret[i] = toEnd;
                    }
                } else {
                    long sum = 0;
                    for (int j = 0; j < k; j++) {
                        sum += ((long)arr[s+j*d])*(j+1);
                    }
                    ret[i] = sum;
                }
            }
            pw.print(ret[0]);
            for (int i = 1; i < q; i++) pw.print(" " + ret[i]);
            pw.println("");
        }
        pw.close();
    }
}