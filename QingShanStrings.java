import java.io.*;
import java.util.*;

public class QingShanStrings {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        
        int numCases = sc.nextInt();
        while (numCases-- > 0) {
            int lenS = sc.nextInt();
            int lenT = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();
            String t = sc.nextLine();
            
            if (isGood(s)) {
                pw.println("YES");
                continue;
            } else {
                if (t.charAt(0)!=t.charAt(t.length()-1) || !isGood(t)) pw.println("NO");
                else {
                    boolean doubleZero = false;
                    boolean doubleOne = false;
                    for (int i = 0; i < lenS-1; i++) {
                        if (s.charAt(i) == s.charAt(i+1)) {
                            if (s.charAt(i) == '0') doubleZero = true;
                            else doubleOne = true;
                        }
                    }
                    if (doubleZero && doubleOne) pw.println("NO");
                    else {
                        char c = t.charAt(0);
                        if ((doubleZero && c=='1') || (doubleOne && c=='0')) pw.println("YES");
                        else pw.println("NO");
                    }
                }
            }
            
        }
        
        pw.close();
    }
    public static boolean isGood(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) return false;
        }
        return true;
    }
}