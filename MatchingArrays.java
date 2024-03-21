import java.io.*;
import java.util.*;

public class MatchingArrays {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(token.nextToken());
            int beauty = Integer.parseInt(token.nextToken());
            ArrayList<Pair> aList = new ArrayList<Pair>();
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < size; i++) aList.add(new Pair(Integer.parseInt(token.nextToken()), i));
            Collections.sort(aList);
            ArrayList<Pair> bList = new ArrayList<Pair>();
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < size; i++) bList.add(new Pair(Integer.parseInt(token.nextToken()), i));
            Collections.sort(bList);
            
            Pair[] rearranged = new Pair[size];
            boolean wrong = false;
            for (int i = 0; i < size-beauty; i++) {
                rearranged[i] = bList.get(i+beauty);
                if (aList.get(i).num > rearranged[i].num) wrong = true;
            }
            if (wrong) {
                pw.println("NO");
                continue;
            }
            for (int i = size-beauty; i < size; i++) {
                rearranged[i] = bList.get(i-size+beauty);
                if (!(aList.get(i).num > rearranged[i].num)) wrong = true;
            }
            if (wrong) pw.println("NO");
            else {
                pw.println("YES");
                int[] trueRearranged = new int[size];
                for (int i = 0; i < size; i++) {
                    trueRearranged[aList.get(i).index] = rearranged[i].num;
                }
                pw.print(trueRearranged[0]);
                for (int i = 1; i < size; i++) pw.print(" " + trueRearranged[i]);
                pw.println("");
            }
        }
        
        
        pw.close();
    }
}

class Pair implements Comparable<Pair> {
    int num;
    int index;
    
    public Pair(int num, int index) {
        this.num = num;
        this.index = index;
    }
    
    public int compareTo(Pair p) {
        return this.num-p.num;
    }
}