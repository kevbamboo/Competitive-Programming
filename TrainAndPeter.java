import java.io.*;
import java.util.*;

public class TrainAndPeter {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        StringBuilder s = new StringBuilder();
        s.append(br.readLine());
        String first = br.readLine();
        String second = br.readLine();
        
        boolean isForward = didSee(s.toString(), first, second);
        s.reverse();
        boolean isBackward = didSee(s.toString(), first, second);
        
        if (isForward && isBackward) pw.println("both");
        else if (!isForward && !isBackward) pw.println("fantasy");
        else if (isForward) pw.println("forward");
        else pw.println("backward");
        
        pw.close();
    }
    public static boolean didSee(String s, String first, String second) {
        int indexFirst = s.indexOf(first);
        if (indexFirst == -1) return false;
        int indexSecond = s.indexOf(second, indexFirst+first.length());
        if (indexSecond == -1) return false;
        return true;
    }
}