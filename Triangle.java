import java.io.*;
import java.util.*;

public class Triangle {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int[] lens = new int[4];
        StringTokenizer token = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) lens[i] = Integer.parseInt(token.nextToken());
        
        Arrays.sort(lens);
        
        if ((lens[1]+lens[2] > lens[3]) || (lens[0]+lens[1] > lens[2])) pw.println("TRIANGLE");
        else if ((lens[1]+lens[2] == lens[3]) || (lens[0]+lens[1] == lens[2])) pw.println("SEGMENT");
        else pw.println("IMPOSSIBLE");
        
        pw.close();
    }
}