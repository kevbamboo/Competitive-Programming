import java.io.*;
import java.util.*;

public class LauraAndOperations {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int numOne = Integer.parseInt(token.nextToken());
            int numTwo = Integer.parseInt(token.nextToken());
            int numThree = Integer.parseInt(token.nextToken());
            pw.print(canAll(numOne, numTwo, numThree) ? "1 " : "0 ");
            pw.print(canAll(numTwo, numOne, numThree) ? "1 " : "0 ");
            pw.println(canAll(numThree, numOne, numTwo) ? "1" : "0");
        }
        pw.close();
    }
    public static boolean canAll(int want, int other1, int other2) {
        int min = Math.min(other1, other2);
        want += min;
        int leftover = Math.max(other1-min, other2-min);
        return leftover%2==0 && want > 0;
    }
}