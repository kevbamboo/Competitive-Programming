import java.io.*;
import java.util.*;

public class PowerOfPoints {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coords = new int[n];
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) coords[i] = Integer.parseInt(token.nextToken());
            
            ArrayList<Integer> sortArrList = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) sortArrList.add(coords[i]);
            Collections.sort(sortArrList);
            
            int[] sortCoords = new int[n];
            for (int i = 0; i < n; i++) sortCoords[i] = sortArrList.get(i);
            long[] prefix = new long[n+1];
            for (int i = 1; i <= n; i++) prefix[i] = prefix[i-1]+sortCoords[i-1];
            
            long[] output = new long[n];
            for (int i = 0; i < n; i++) {
                int index = binSearch(sortCoords, n, coords[i]);
                output[i] = prefix[n]-(long)(n-index)*coords[i]-(long)2*prefix[index]+(long)(index)*coords[i]+n;
            }
            
            pw.print(output[0]);
            for (int i = 1; i < n; i++) pw.print(" " + output[i]);
            pw.println("");
        }
        
        pw.close();
    }
    public static int binSearch(int[] arr, int len, int val) {
        int low = 0;
        int high = len-1;
        
        while (low <= high) {
            if (low == high) return arr[low]==val ? low : -1;
            
            int mid = low + (high-low)/2;
            if (arr[mid] == val) return mid;
            if (arr[mid] < val) low = mid+1;
            else high = mid-1;
        }
        
        return -1;
    }
}