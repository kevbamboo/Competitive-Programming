import java.io.*;
import java.util.*;

public class AnjisBinaryTree {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int len = Integer.parseInt(br.readLine());
            ArrayList<Integer> list = new ArrayList<Integer>();
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) list.add(Integer.parseInt(token.nextToken()));
            Collections.sort(list);
            long sum = 0;
            for (int i = 0; i < len-2; i++) {
                for (int j = i+1; j < len-1; j++) {
                    sum += gcd(list.get(i), list.get(j))*(len-j-1);
                }
            }
            pw.println(sum);
        }
        pw.close();
    }
    public static int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a == b) return a;
        if (a > b) return gcd(a%b, b);
        return gcd(a, b%a);
    }
}