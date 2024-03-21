import java.io.*;
import java.util.*;

public class BattlingWithNumbers {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine());
        int[] xPrimes = new int[N];
        StringTokenizer token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) xPrimes[i] = Integer.parseInt(token.nextToken());
        int[] xExp = new int[N];
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) xExp[i] = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(br.readLine());
        int[] yPrimes = new int[M];
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) yPrimes[i] = Integer.parseInt(token.nextToken());
        int[] yExp = new int[M];
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) yExp[i] = Integer.parseInt(token.nextToken());
        
        int len = merge(xPrimes, xExp, N, yPrimes, yExp, M);
        int numPairs = 0;
        if (len != -1) {
            numPairs = 1;
            while (len-- > 0) {
                numPairs = (numPairs*2)%998244353;
            }
        }
        
        pw.println(numPairs);
        pw.close();
    }
    private static int merge(int[] xPrimes, int[] xExp, int N, int[] yPrimes, int[] yExp, int M) {
        int x = 0;
        int y = 0;
        int ret = 0;
        while (x != N && y != M) {
            if (xPrimes[x] < yPrimes[y]) {
                x++;
                ret++;
            } else if (xPrimes[x] > yPrimes[y]) {
                return -1;
            } else {
                if (xExp[x] >= yExp[y]) {
                    if (xExp[x] > yExp[y]) ret++;
                    x++;
                    y++;
                } else return -1;
            }
        }
        if (x == N && y != M) {
            return -1;
        } else {
            ret += N-x;
        }
        return ret;
    }
}