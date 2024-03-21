import java.io.*;
import java.util.*;

public class CycleCorrespondence {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken());
        
        ArrayList<Integer> aList = new ArrayList<Integer>();
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            aList.add(Integer.parseInt(token.nextToken()));
        }
        ArrayList<Integer> bList = new ArrayList<Integer>();
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            bList.add(Integer.parseInt(token.nextToken()));
        }
        
        Collections.sort(aList);
        Collections.sort(bList);
        int[] aArr = new int[K];
        for (int i = 0; i < K; i++) aArr[i] = aList.get(i);
        int[] bArr = new int[K];
        for (int i = 0; i < K; i++) bArr[i] = bList.get(i);
        
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (binSearch(aArr, i) == binSearch(bArr, i)) count++;
        }
        pw.println(count);
        pw.close();
    }
    public static boolean binSearch(int[] arr, int num) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] == num) return true;
            if (arr[mid] < num) lo = mid+1;
            else hi = mid;
        }
        return false;
    }
}