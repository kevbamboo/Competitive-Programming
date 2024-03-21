import java.io.*;
import java.util.*;

public class SmallGCD {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        HashMap<Pair, Integer> map = new HashMap<Pair, Integer>();
        while (numCases-- > 0) {
            int len = Integer.parseInt(br.readLine());
            ArrayList<Integer> list = new ArrayList<Integer>();
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) list.add(Integer.parseInt(token.nextToken()));
            Collections.sort(list);
            long sum = 0;
            for (int i = 0; i < len-2; i++) {
                for (int j = i+1; j < len-1; j++) {
                    sum += gcd(list.get(i), list.get(j), map)*(len-j-1);
                }
            }
            pw.println(sum);
        }
        pw.close();
    }
    public static int gcd(int a, int b, HashMap<Pair, Integer> map) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a == b) return a;
        Integer ret = map.get(new Pair(a,b));
        if (ret != null) return ret;
        int gcd = 0;
        if (a > b) gcd = gcd(a%b, b, map);
        else gcd = gcd(a, b%a, map);
        map.put(new Pair(a,b), gcd);
        return gcd;
    }
}

class Pair implements Comparable<Pair> {
    int a;
    int b;
    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    public int compareTo(Pair p) {
        return this.a - p.a + this.b - p.b;
    }
}