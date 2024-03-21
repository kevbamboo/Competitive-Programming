import java.io.*;
import java.util.*;

public class GameWithMarbles {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        
        while (numCases-- > 0) {
            int numColors = Integer.parseInt(br.readLine());
            int[][] marbles = new int[numColors][2];
            ArrayList<Pair> a_i = new ArrayList<Pair>();
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < numColors; i++) {
                marbles[i][0] = Integer.parseInt(token.nextToken());
                a_i.add(new Pair(marbles[i][0], i));
            }
            ArrayList<Pair> b_i = new ArrayList<Pair>();
            token = new StringTokenizer(br.readLine());
            for (int i = 0; i < numColors; i++) {
                marbles[i][1] = Integer.parseInt(token.nextToken());
                b_i.add(new Pair(marbles[i][1], i));
            }
            
            for (int i = 0; i < numColors; i++) {
                a_i.get(i).opposite = b_i.get(i).marbles;
                b_i.get(i).opposite = a_i.get(i).marbles;
            }
            
            Collections.sort(a_i);
            Collections.sort(b_i);
            
            TreeSet<Integer> indicesUsed = new TreeSet<Integer>();
            
            long difference = 0;
            boolean isAlice = true;
            int aliceIndex = 0;
            int bobIndex = 0;
            for (int i = 0; i < numColors; i++) {
                if (isAlice) {
                    isAlice = false;
                    while (indicesUsed.contains(b_i.get(aliceIndex).index)) aliceIndex++;
                    indicesUsed.add(b_i.get(aliceIndex).index);
                    difference += marbles[b_i.get(aliceIndex).index][0]-1;
                    aliceIndex++;
                } else {
                    isAlice = true;
                    while (indicesUsed.contains(a_i.get(bobIndex).index)) bobIndex++;
                    indicesUsed.add(a_i.get(bobIndex).index);
                    difference -= marbles[a_i.get(bobIndex).index][1]-1;
                    bobIndex++;
                }
            }
            pw.println(difference);
        }
        pw.close();
    }
}

class Pair implements Comparable<Pair> {
    public int marbles;
    public int index;
    public int opposite;
    
    public Pair(int marbles, int index) {
        this.marbles = marbles;
        this.index = index;
        opposite = 0;
    }
    
    public int compareTo(Pair b) {
        return b.marbles - this.marbles + b.opposite - this.opposite;
    }
}