import java.io.*;
import java.util.*;

public class DataStructuresFan {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int length = Integer.parseInt(br.readLine());
            StringTokenizer token = new StringTokenizer(br.readLine());
            int[] arr = new int[length];
            for (int i = 0; i < length; i++) arr[i] = Integer.parseInt(token.nextToken());
            String str = br.readLine();
            boolean[] isFlip = new boolean[length];
            
            int numQueries = Integer.parseInt(br.readLine());
            
            int xorZero = 0;
            int xorOne = 0;
            for (int i = 0; i < length; i++) {
                if (str.charAt(i) == '0') xorZero = xorZero^arr[i];
                else xorOne = xorOne^arr[i];
            }
            int[] prefix = new int[length+1];
            for (int i = 1; i < length+1; i++) {
                prefix[i] = prefix[i-1]^arr[i-1];
            }
            
            int[] output = new int[numQueries];
            int numQ2 = 0;
            while (numQueries-- > 0) {
                token = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(token.nextToken());
                if (type == 1) {
                    int left = Integer.parseInt(token.nextToken());
                    int right = Integer.parseInt(token.nextToken());
                    xorZero = xorZero^prefix[left-1]^prefix[right];
                    xorOne = xorOne^prefix[left-1]^prefix[right];
                } else {
                    boolean gZero = (Integer.parseInt(token.nextToken()) == 0);
                    output[numQ2++] = gZero ? xorZero: xorOne;
                }
                
            }
            if (numQ2 > 0) {
                pw.print(output[0]);
                for (int i = 1; i < numQ2; i++) pw.print(" " + output[i]);
            }
            pw.println("");
        }
        
        pw.close();
    }
}