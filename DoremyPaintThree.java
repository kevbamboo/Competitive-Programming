import java.io.*;
import java.util.*;

public class DoremyPaintThree {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = sc.nextInt();
        
        while (numCases-- > 0) {
            int len = sc.nextInt();
            
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = sc.nextInt();
            }
            
            if (len == 2) {
                pw.println("YES");
                continue;
            }
            
            int c1 = arr[0];
            int c2 = 0;
            for (int i = 1; i < len; i++) {
                if (arr[i] != c1) c2 = arr[i];
            }
            int count1 = 0;
            int count2 = 0;
            for (int i = 0; i < len; i++) {
                if (arr[i] == c1) count1++;
                if (arr[i] == c2) count2++;
            }
            if (count1+count2 == len && ((count1==0||count2==0) || Math.abs(count1-count2) <= 1)) pw.println("YES");
            else pw.println("NO");
        }
        pw.close();
    }
}