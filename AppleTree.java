import java.io.*;
import java.util.*;

public class AppleTree {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        StringTokenizer token;
        while (numCases-- > 0) {
            int numVertices = Integer.parseInt(br.readLine());
            Vertex[] vertices = new Vertex[numVertices+1];
            for (int i = 0; i <= numVertices; i++) vertices[i] = new Vertex(i);
            for (int i = 0; i < numVertices-1; i++) {
                token = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());
                vertices[Math.min(u,v)].children.add(Math.max(u,v));
                vertices[Math.max(u,v)].parents.add(Math.min(u,v));
            }
            Vertex.vertices = vertices;
            
            for (int i = 1; i <= numVertices; i++) if (vertices[i].parents.size() == 0) vertices[i].parents.add(0);
            
            for (int i = 1; i <= numVertices; i++) {
                vertices[i].setLeaves();
            }
            
            int Q = Integer.parseInt(br.readLine());
            while (Q-- > 0) {
                token = new StringTokenizer(br.readLine());
                pw.println(vertices[Integer.parseInt(token.nextToken())].numLeaves*vertices[Integer.parseInt(token.nextToken())].numLeaves);
            }
        }
        pw.close();
    }
}

class Vertex {
    public static Vertex[] vertices;
    public int index;
    public ArrayList<Integer> children;
    public ArrayList<Integer> parents;
    public long numLeaves;
    public HashSet<Integer> descendants;
    
    public Vertex(int index) {
        this.index = index;
        this.children = new ArrayList<Integer>();
        this.parents = new ArrayList<Integer>();
        this.numLeaves = 0;
        this.descendants = new HashSet<Integer>();
    }
    
    public void setLeaves() {
        if (this.numLeaves != 0) return;
        if (this.children.size() == 0) {
            numLeaves = 1;
            addDescendant(this.index);
        }
        else {
            for (int i = 0; i < this.children.size(); i++) {
                int index = this.children.get(i);
                vertices[index].setLeaves();
            }
            this.numLeaves = descendants.size();
        }
    }
    
    public void addDescendant(int index) {
        if (this.index == 0) return;
        if (!this.descendants.contains(index)) {
            this.descendants.add(index);
            for (int i = 0; i < parents.size(); i++) vertices[this.parents.get(i)].addDescendant(index);
        }
    }
}