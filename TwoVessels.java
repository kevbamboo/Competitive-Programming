import java.io.*;
import java.util.*;

public class TwoVessels {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int diff = Integer.parseInt(token.nextToken())-Integer.parseInt(token.nextToken());
            pw.println((int)Math.ceil(Math.abs(1.0*diff/2/Integer.parseInt(token.nextToken()))));
        }
        
        pw.close();
    }
}