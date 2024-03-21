import java.io.*;
import java.util.*;

public class YetnotherrokenKeoard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            String s = br.readLine();
            ArrayList<Letter> uppercase = new ArrayList<Letter>();
            ArrayList<Letter> undercase = new ArrayList<Letter>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'b') {
                    if (undercase.size() != 0) undercase.remove(undercase.size()-1);
                }
                else if (c == 'B') {
                    if (uppercase.size() != 0) uppercase.remove(uppercase.size()-1);
                }
                else {
                    if (c > 96) {
                        undercase.add(new Letter(c, i));
                    }
                    else {
                        uppercase.add(new Letter(c, i));
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            int upIndex = 0;
            int underIndex = 0;
            int i = 0;
            while (upIndex < uppercase.size() && underIndex < undercase.size()) {
                if (uppercase.get(upIndex).index == i) {
                    sb.append(uppercase.get(upIndex).c);
                    upIndex++;
                }
                else {
                    sb.append(undercase.get(underIndex).c);
                    underIndex++;
                }
                i++;
            }
            if (upIndex == uppercase.size()) {
                while (underIndex < undercase.size()) {
                    sb.append(undercase.get(underIndex).c);
                    underIndex++;
                }
            } else {
                while (upIndex < uppercase.size()) {
                    sb.append(uppercase.get(upIndex).c);
                    upIndex++;
                }
            }
            pw.println(sb.toString());
        }
        pw.close();
    }
}

class Letter {
    public char c;
    public int index;
    
    public Letter(char c, int index) {
        this.c = c;
        this.index = index;
    }
    
    public String toString() {
        return c + " " + index;
    }
}