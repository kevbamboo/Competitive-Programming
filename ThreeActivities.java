import java.io.*;
import java.util.*;

public class ThreeActivities {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int len = Integer.parseInt(br.readLine());
            ArrayList<Pair> firstDay = new ArrayList<Pair>();
            ArrayList<Pair> secondDay = new ArrayList<Pair>();
            ArrayList<Pair> thirdDay = new ArrayList<Pair>();
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) firstDay.add(new Pair(Integer.parseInt(token.nextToken()),i));
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) secondDay.add(new Pair(Integer.parseInt(token.nextToken()), i));
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) thirdDay.add(new Pair(Integer.parseInt(token.nextToken()), i));
            Collections.sort(firstDay);
            Collections.sort(secondDay);
            Collections.sort(thirdDay);
            
            int max = 0;
            int loop = Math.min(10, len);
            for (int i = 0; i < loop; i++) {
                for (int j = 0; j < loop; j++) {
                    for (int k = 0; k < loop; k++) {
                        Pair p1 = firstDay.get(i);
                        Pair p2 = secondDay.get(j);
                        Pair p3 = thirdDay.get(k);
                        if (p1.index != p2.index && p1.index != p3.index && p2.index != p3.index) {
                            max = Math.max(max, p1.friends+p2.friends+p3.friends);
                            break;
                        }
                    }
                }
            }
            pw.println(max);
        }
        pw.close();
    }
}

class Pair implements Comparable<Pair> {
    public int friends;
    public int index;
    
    public Pair(int friends, int index) {
        this.friends = friends;
        this.index = index;
    }
    
    public int compareTo(Pair b) {
        return b.friends - this.friends;
    }
}