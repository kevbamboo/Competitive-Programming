import java.io.*;
import java.util.*;

public class Day8Part1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        ArrayList<ArrayList<Integer>> heights = new ArrayList<ArrayList<Integer>>();
        String s;
        while ((s = br.readLine()) != null) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            for (int i = 0; i < s.length(); i++) cur.add(Character.getNumericValue(s.charAt(i)));
            heights.add(cur);
        }
        
        int numVisible = 2*heights.size() + 2*heights.get(0).size() - 4; // edges
        
        for (int i = 1; i < heights.size()-1; i++) {
            for (int j = 1; j < heights.get(0).size()-1; j++) {
                int curVal = heights.get(i).get(j);
                boolean canSeeTop = true;
                for (int a = 0; a < i; a++) {
                    if (heights.get(a).get(j) >= curVal) {
                        canSeeTop = false;
                        break;
                    }
                }
                boolean canSeeBottom = true;
                for (int a = i+1; a < heights.size(); a++) {
                    if (heights.get(a).get(j) >= curVal) {
                        canSeeBottom = false;
                        break;
                    }
                }
                
                boolean canSeeLeft = true;
                for (int b = 0; b < j; b++) {
                    if (heights.get(i).get(b) >= curVal) {
                        canSeeLeft = false;
                        break;
                    }
                }
                
                boolean canSeeRight = true;
                for (int b = j+1; b < heights.get(0).size(); b++) {
                    if (heights.get(i).get(b) >= curVal) {
                        canSeeRight = false;
                        break;
                    }
                }
                
                if (canSeeTop || canSeeBottom || canSeeLeft || canSeeRight) numVisible++;
            }
        }
        
        System.out.println(numVisible);
    }
}