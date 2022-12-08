import java.io.*;
import java.util.*;

public class Day8Part2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        ArrayList<ArrayList<Integer>> heights = new ArrayList<ArrayList<Integer>>();
        String s;
        while ((s = br.readLine()) != null) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            for (int i = 0; i < s.length(); i++) cur.add(Character.getNumericValue(s.charAt(i)));
            heights.add(cur);
        }
        
        int maxScenicScore = 0;
        
        for (int i = 1; i < heights.size()-1; i++) {
            for (int j = 1; j < heights.get(0).size()-1; j++) {
                int curVal = heights.get(i).get(j);
                int top = i;
                for (int a = 0; a < i; a++) {
                    if (heights.get(a).get(j) >= curVal) {
                        top = i-a;
                    }
                }
                int bottom = heights.size() - i - 1;
                for (int a = i+1; a < heights.size(); a++) {
                    if (heights.get(a).get(j) >= curVal) {
                        bottom = a-i;
                        break;
                    }
                }
                
                int left = j;
                for (int b = 0; b < j; b++) {
                    if (heights.get(i).get(b) >= curVal) {
                        left = j-b;
                    }
                }
                
                int right = heights.get(0).size() - j - 1;
                for (int b = j+1; b < heights.get(0).size(); b++) {
                    if (heights.get(i).get(b) >= curVal) {
                        right = b-j;
                        break;
                    }
                }
                
                maxScenicScore = Math.max(maxScenicScore, top*bottom*left*right);
            }
        }
        
        System.out.println(maxScenicScore);
    }
}