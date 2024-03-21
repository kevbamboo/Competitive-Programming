import java.io.*;
import java.util.*;

public class PointsAndMinDistance {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer token = new StringTokenizer(br.readLine());
            ArrayList<Integer> nums = new ArrayList<Integer>();
            for (int i = 0; i < 2*n; i++) nums.add(Integer.parseInt(token.nextToken()));
            Collections.sort(nums);
            int[] sortedNums = new int[2*n];
            for (int i = 0; i < 2*n; i++) sortedNums[i] = nums.get(i);
            
            int minLength = 0;
            for (int i = 1; i < n; i++) minLength += (sortedNums[i+n]-sortedNums[i+n-1])+(sortedNums[i]-sortedNums[i-1]);
            pw.println(minLength);
            for (int i = 0; i < n; i++) {
                pw.println(sortedNums[i+n] + " " + sortedNums[i]);
            }
        }
        pw.close();
    }
}