import java.io.*;
import java.util.*;

public class GameAndMultiset {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numQ = Integer.parseInt(br.readLine());
        int[] addBinary = new int[40];
        while (numQ-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            if (Integer.parseInt(token.nextToken()) == 1) {
                addBinary[Integer.parseInt(token.nextToken())]++;
            } else {
                String str = Integer.toBinaryString(Integer.parseInt(token.nextToken()));
                int len = str.length();
                int[] wBin = new int[40];
                for (int i = 0; i < len; i++) {
                    wBin[len-1-i] = str.charAt(i)-'0';
                }
                
                int[] tempAdd = new int[40];
                for (int i = 0; i < 40; i++) tempAdd[i] = addBinary[i];
                
                boolean can = true;
                for (int i = 0; i < len; i++) {
                    if (wBin[i] > tempAdd[i]) {
                        can = false;
                        break;
                    }
                    tempAdd[i+1] += (tempAdd[i]-wBin[i])/2;
                }
                pw.println(can ? "YES" : "NO");
            }
        }
        
        pw.close();
    }
}