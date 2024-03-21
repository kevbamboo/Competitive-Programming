import java.io.*;
import java.util.*;

public class AnjisBinaryTree {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            int numVertices = Integer.parseInt(br.readLine());
            String s = br.readLine();
            StringTokenizer token;
            int[][] tree = new int[numVertices][2];
            for (int i = 0; i < numVertices; i++) {
                token = new StringTokenizer(br.readLine());
                tree[i][0] = Integer.parseInt(token.nextToken())-1;
                tree[i][1] = Integer.parseInt(token.nextToken())-1;
            }
            pw.println(numChange(tree, s, 0));
        }
        pw.close();
    }
    public static int numChange(int[][] tree, String s, int index) {
        if (tree[index][0] == -1 && tree[index][1] == -1) return 0;
        if (s.charAt(index) == 'U') {
            if (tree[index][0] == -1) return 1 + numChange(tree, s, tree[index][1]);
            else if (tree[index][1] == -1) return 1 + numChange(tree, s, tree[index][0]);
            else return 1 + Math.min(numChange(tree, s, tree[index][0]), numChange(tree, s, tree[index][1]));
        }
        else if (s.charAt(index) == 'L') {
            if (tree[index][0] == -1) return 1 + numChange(tree, s, tree[index][1]);
            else if (tree[index][1] == -1) return numChange(tree, s, tree[index][0]);
            else return Math.min(numChange(tree, s, tree[index][0]), 1+numChange(tree, s, tree[index][1]));
        }
        if (tree[index][0] == -1) return numChange(tree, s, tree[index][1]);
        else if (tree[index][1] == -1) return 1+numChange(tree, s, tree[index][0]);
        return Math.min(1+numChange(tree, s, tree[index][0]), numChange(tree, s, tree[index][1]));
    }
}