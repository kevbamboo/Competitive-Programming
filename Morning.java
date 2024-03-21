import java.io.*;
import java.util.*;

public class Morning {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int[] arr = new int[5];
            arr[0] = 1;
            String pin = br.readLine();
            for (int i = 1; i < 5; i++) {
                char c = pin.charAt(i-1);
                arr[i] = (c == '0') ? 10 : (c-'0');
            }
            int time = 0;
            for (int i = 1; i < 5; i++) time += Math.abs(arr[i]-arr[i-1])+1;
            pw.println(time);
        }
        pw.close();
    }
}