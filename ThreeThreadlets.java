import java.io.*;
import java.util.*;

public class ThreeThreadlets {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());
            if (a == b && b == c) pw.println("YES");
            else {
                int sortA = Math.max(a, Math.max(b,c));
                int sortC = Math.min(a, Math.min(b,c));
                int sortB = a+b+c-sortA-sortC;
                if (can1(sortA,sortB,sortC)) pw.println("YES");
                else if (can2(sortA,sortB,sortC)) pw.println("YES");
                else if (can3(sortA,sortB,sortC)) pw.println("YES");
                else pw.println("NO");
            }
        }
        pw.close();
    }
    
    public static boolean can1(int a, int b, int c) {
        if (b == c && a == 2*b) return true;
        return false;
    }
    
    public static boolean can2(int a, int b, int c) {
        if (b == c && a == 3*b) return true;
        if (a == b && a == 2*c) return true;
        return false;
    }
    
    public static boolean can3(int a, int b, int c) {
        if (b == c && a == 4*b) return true;
        if (a == 3*c && b == 2*c) return true;
        return false;
    }
}