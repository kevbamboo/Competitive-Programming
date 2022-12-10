import java.io.*;
import java.util.*;

public class Day9Part1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        String s;
        int HX = 0;
        int HY = 0;
        int TX = 0;
        int TY = 0;
        ArrayList<int[]> points = new ArrayList<int[]>();
        points.add(new int[] {0,0});
        while ((s = br.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(s);
            char direction = token.nextToken().charAt(0);
            int num = Integer.parseInt(token.nextToken());
            while (num-- > 0) {
                int[][] newPoints = newPoint(HX, HY, TX, TY, direction);
                int[] H = newPoints[0];
                int[] T = newPoints[1];
                HX = H[0];
                HY = H[1];
                TX = T[0];
                TY = T[1];
                //System.out.println(newPoint[0] + " " + newPoint[1]);
                boolean isNewPoint = true;
                for (int[] point : points) {
                    if (point[0] == T[0] && point[1] == T[1]) {
                        isNewPoint = false;
                        break;
                    }
                }
                if (isNewPoint) points.add(T);
            }
        }
        System.out.println(points.size());
    }
    public static int[][] newPoint(int HX, int HY, int TX, int TY, char direction) {
        if (direction == 'U') HY++;
        if (direction == 'D') HY--;
        if (direction == 'L') HX--;
        if (direction == 'R') HX++;
        int[] H = new int[2];
        int[] T = new int[2];
        int XDiff = HX-TX;
        int YDiff = HY-TY;
        if (!(Math.abs(XDiff) + Math.abs(YDiff) < 2 || (Math.abs(XDiff) == 1 && Math.abs(YDiff) == 1))) {
            if (Math.abs(XDiff) + Math.abs(YDiff) == 2) {
                if (XDiff == -2) TX--;
                else if (XDiff == 2) TX++;
                else if (YDiff == -2) TY--;
                else TY++; // YDiff == 2
            } else { // == 3
                if (XDiff == -2) {
                    TX--;
                    TY = HY;
                }
                else if (XDiff == 2) {
                    TX++;
                    TY = HY;
                }
                else if (YDiff == -2) {
                    TY--;
                    TX = HX;
                }
                else { // YDiff == 2
                    TY++;
                    TX = HX;
                }
            }
        }
        H[0] = HX;
        H[1] = HY;
        T[0] = TX;
        T[1] = TY;
        return new int[][] {H, T};
    }
}
