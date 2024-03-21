import java.io.*;
import java.util.*;

public class InLove {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        Segments segments = new Segments();
        int numOps = Integer.parseInt(br.readLine());
        
        while (numOps-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            String opp = token.nextToken();
            int left = Integer.parseInt(token.nextToken());
            int right = Integer.parseInt(token.nextToken());
            
            if (opp.equals("+")) {
                segments.add(left, right);
            } else {
                segments.remove(left, right);
            }
            
            pw.println(segments.hasPairNoIntersect() ? "YES" : "NO");
        }
        
        pw.close();
    }
}

class Segments {
    public int size;
    public TreeSet<Integer> leftSet;
    public HashMap<Integer, Left> map;
    
    public Segments() {
        size = 0;
        leftSet = new TreeSet<Integer>();
        map = new HashMap<Integer, Left>();
    }
    
    public void add(int left, int right) {
        if (leftSet.add(left)) {
            map.put(left, new Left(left, right));
        }
        else {
            map.get(left).addRight(right);
        }
        this.size++;
    }
    
    public void remove(int left, int right) {
        map.get(left).remove(right);
        if (map.get(left).rightSet.size() == 0) {
            leftSet.remove(left);
            map.remove(left);
        }
        this.size--;
    }
    
    public boolean hasPairNoIntersect() {
        if (this.size < 2) return false;
        
        Left smallestLeft = map.get(leftSet.first());
        int smallestRight = smallestLeft.leftMap.get(smallestLeft.rightSet.first()).right;
        int largestLeft = map.get(leftSet.last()).left;
        
        return smallestRight < largestLeft;
    }
}

class Left implements Comparable<Left> {
    public int left;
    public TreeSet<Integer> rightSet;
    public HashMap<Integer, Right> leftMap;
    
    public Left(int left, int right) {
        this.left = left;
        rightSet = new TreeSet<Integer>();
        leftMap = new HashMap<Integer, Right>();
        addRight(right);
    }
    
    public void addRight(int right) {
        if (rightSet.add(right)) leftMap.put(right, new Right(right));
        else leftMap.get(right).count++;
    }
    
    public void remove(int right) {
        Right r = leftMap.get(right);
        if (r.count == 1) {
            rightSet.remove(right);
            leftMap.remove(right);
        }
        else r.count--;
    }
    
    public int compareTo(Left l) {
        return this.left-l.left;
    }
}

class Right implements Comparable<Right> {
    public int right;
    public int count;
    
    public Right(int right) {
        this.right = right;
        this.count = 1;
    }
    
    public int compareTo(Right r) {
        return this.right-r.right;
    }
}