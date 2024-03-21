import java.io.*;
import java.util.*;

public class SumOfThree {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = sc.nextInt();
        while (numCases-- > 0) {
            int num = sc.nextInt();
            if (num < 7) {
                pw.print("NO\n");
                continue;
            }
            if (num%3==0) {
                if (num == 9) pw.print("NO\n");
                else {
                    pw.print("YES\n");
                    pw.print(1+" "+4+" "+(num-5)+"\n");
                }
            } else if (num%3==1) {
                pw.print("YES\n"+1+" "+4+" "+(num-5)+"\n");
            } else {
                pw.print("YES\n"+2+" "+5+" "+(num-7)+"\n");
            }
        }
        
        pw.close();
    }
}