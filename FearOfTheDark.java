import java.io.*;
import java.util.*;

public class FearOfTheDark {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = sc.nextInt();
        
        while (numCases-- > 0) {
            int px = sc.nextInt();
            int py = sc.nextInt();
            int ax = sc.nextInt();
            int ay = sc.nextInt();
            int bx = sc.nextInt();
            int by = sc.nextInt();
            double minPower = Math.min(distance(px,py,ax,ay),distance(px,py,bx,by));
            minPower = Math.max(minPower, distance(ax,ay,bx,by)/2);
            minPower = Math.max(minPower, Math.min(distance(0,0,ax,ay),distance(0,0,bx,by)));
            pw.println(minPower);
        }
        
        pw.close();
    }
    
    public static double distance(int p1x, int p1y, int p2x, int p2y) {
        return Math.sqrt(Math.pow(p1x-p2x,2)+Math.pow(p1y-p2y,2));
    }
}