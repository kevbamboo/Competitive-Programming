import java.io.*;
import java.util.*;

public class ChatBan {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            long k = Long.parseLong(token.nextToken());
            long x = Long.parseLong(token.nextToken());
            if (x >= k*k) pw.println(2*k-1);
            else if (x > (k*k+k)/2) pw.println(k+down(k, x-(k*k+k)/2));
            else pw.println(up(k,x));
        }
        
        pw.close();
    }
    
    public static long down(long k, long x) {
        long lo = 1;
        long hi = k;
        
        while (lo < hi) {
            long mid = (hi-lo)/2 + lo;
            if (downSum(k-1, mid-1) < x && downSum(k-1, mid) >= x) return mid;
            if (downSum(k-1, mid-1) < x) lo = mid+1;
            else hi = mid;
        }
        return -1;
    }
    
    public static long downSum(long start, long num) {
        return (start+start-num+1)*num/2;
    }
    
    public static long up(long k, long x) {
        long lo = 1;
        long hi = k+1;
        while (lo < hi) {
            long mid = (hi-lo)/2 + lo;
            if (upSum(mid-1) < x && upSum(mid) >= x) return mid;
            if (upSum(mid) < x) lo = mid+1;
            else hi = mid;
        }
        return -1;
    }
    
    public static long upSum(long num) {
        return num*(num+1)/2;
    }
}