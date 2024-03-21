import java.io.*;
import java.util.*;

public class JaggedSwaps {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int size = Integer.parseInt(br.readLine());
            StringTokenizer token = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) list.add(Integer.parseInt(token.nextToken()));
            int min = list.get(0);
            Collections.sort(list);
            pw.println((min == list.get(0)) ? "YES" : "NO");
        }
        
        
        pw.close();
    }
}