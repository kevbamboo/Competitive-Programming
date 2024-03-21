import java.io.*;
import java.util.*;

public class AliceBobChocolate {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer token = new StringTokenizer(br.readLine());
        int[] time = new int[n];
        for (int i = 0; i < n; i++) time[i] = Integer.parseInt(token.nextToken());
        
        int aliceIndex = 0;
        int aliceTime = 0;
        int bobIndex = n-1;
        int bobTime = 0;
        
        while (aliceIndex < bobIndex) {
            if (aliceTime == bobTime) {
                aliceTime += time[aliceIndex++];
                bobTime += time[bobIndex--];
            } else if (aliceTime < bobTime) {
                aliceTime += time[aliceIndex++];
            } else {
                bobTime += time[bobIndex--];
            }
        }
        if (aliceIndex == bobIndex) {
            if (aliceTime <= bobTime) aliceIndex++;
            else bobIndex--;
        }
        
        pw.println(aliceIndex + " " + (n-bobIndex-1));
        
        pw.close();
    }
}