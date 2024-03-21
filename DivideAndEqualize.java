import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class DivideAndEqualize {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Integer> list = new ArrayList<Integer>();
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) list.add(Integer.parseInt(token.nextToken()));
            double product = 1;
            for (int i = 0; i < n; i++) product *= list.get(i);
            if (product == 0) pw.println("YES");
            else {
                pw.println(binSearch(0, 1000000000, n, product)? "YES" : "NO");
            }
        }
        pw.close();
    }
    public static boolean binSearch(int lo, int hi, int n, double product) {
        while (lo < hi) {
            int mid = (hi-lo)/2+lo;
            double power = Math.pow(mid,n);
            if (power == product) return true;
            if (power < product) lo = mid+1;
            else hi = mid;
        }
        return false;
    }
}