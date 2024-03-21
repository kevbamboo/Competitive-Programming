import java.io.*;
import java.util.*;

public class ProblemsolvingLog {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int time = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int[] arr = new int[26];
            int numSolved = 0;
            for (int i = 0; i < 26; i++) arr[i] = i+1;
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i)-'A';
                arr[index]--;
                if (arr[index] == 0) numSolved++;
            }
            pw.println(numSolved);
        }
        pw.close();
    }
}