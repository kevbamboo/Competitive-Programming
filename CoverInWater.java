import java.io.*;
import java.util.*;

public class CoverInWater {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int len = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int numWater = 0;
            int numConsec = 0;
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '.') {
                    numConsec++;
                }
                else {
                    if (numConsec > 2) {
                        numWater = 2;
                        numConsec = 0;
                        break;
                    }
                    numWater += numConsec;
                    numConsec = 0;
                }
            }
            if (numConsec > 2) {
                numWater = 2;
            }
            else numWater += numConsec;
            pw.println(numWater);
        }
        pw.close();
    }
}