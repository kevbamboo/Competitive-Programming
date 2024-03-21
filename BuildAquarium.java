import java.io.*;
import java.util.*;

public class BuildAquarium {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int numCols = Integer.parseInt(token.nextToken());
            long maxWater = Long.parseLong(token.nextToken());
            token = new StringTokenizer(br.readLine());
            long[] heights = new long[numCols];
            for (int i = 0; i < numCols; i++) heights[i] = Long.parseLong(token.nextToken());
            long maxHeight = 0;
            for (int i = 0; i < numCols; i++) maxHeight = Math.max(maxHeight, heights[i]);
            
            long low = 0;
            long high = maxHeight+maxWater/numCols+1;
            long h = 0;
            
            while (low <= high) {
                long mid = low+(high-low)/2;
                if (numWater(heights, numCols, mid) <= maxWater && numWater(heights, numCols, mid+1) > maxWater) {
                    h = mid;
                    break;
                }
                if (numWater(heights, numCols, mid) <= maxWater) low = mid+1;
                else high = mid-1;
            }
            pw.println(h);
        }
        
        pw.close();
    }
    private static long numWater(long[] heights, int numCols, long waterHeight) {
        long sum = 0;
        for (int i = 0; i < numCols; i++) {
            sum += Math.max(0, waterHeight-heights[i]);
        }
        return sum;
    }
}