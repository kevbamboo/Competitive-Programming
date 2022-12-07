import java.io.*;
import java.util.*;

public class Day7Part2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        String s;
        Node root = new Node("", true, 0, null);
        Node cur = root;
        while ((s = br.readLine()) != null) {
            if (s.equals("$ ls")) {
                while ((s = br.readLine()) != null) {
                    if (s.charAt(0) == '$') break;
                    if (s.substring(0,3).equals("dir")) {
                        cur.addChild(new Node(s.substring(4), true, 0, cur));
                    } else {
                        StringTokenizer token = new StringTokenizer(s);
                        double size = Double.parseDouble(token.nextToken());
                        String name = token.nextToken();
                        cur.addChild(new Node(name, false, size, cur));
                    }
                }
            }
            if (s == null) break;
            if (s.equals("$ cd /")) cur = root;
            else if (s.equals("$ cd ..")) {
                cur = cur.parent;
            }
            else if (s.contains("$ cd ")) {
                for (Node node : cur.children) {
                    if (node.name.equals(s.substring(5))) {
                        cur = node;
                        break;
                    }
                }
            }
        }
        
        root.size();
        double freeSpace = 70000000-root.size;
        double moreSpaceNeeded = 30000000-freeSpace;
        System.out.println(getMinDirectoryRemoveSize(root, moreSpaceNeeded, root.size));
    }
    
    public static double getMinDirectoryRemoveSize(Node cur, double moreSpaceNeeded, double curMin) {
        for (Node node : cur.children) {
            curMin = getMinDirectoryRemoveSize(node, moreSpaceNeeded, curMin);
        }
        if (cur.isDirectory && cur.size >= moreSpaceNeeded) curMin = Math.min(curMin, cur.size);
        
        return curMin;
    }
}
class Node {
    String name;
    boolean isDirectory;
    double size;
    Node parent;
    ArrayList<Node> children;
    
    public Node(String name, boolean isDirectory, double size, Node parent) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.size = size;
        this.parent = parent;
        this.children = new ArrayList<Node>();
    }
    
    public void addChild(Node node) {
        children.add(node);
    }
    
    public double size() {
        double newSize = this.size;
        
        for (Node node : this.children) {
            newSize += node.size();
        }
        this.size = newSize;
        return newSize;
    }
}