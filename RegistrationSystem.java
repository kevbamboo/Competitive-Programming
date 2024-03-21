import java.io.*;
import java.util.*;

public class RegistrationSystem {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        while (n-- > 0) {
            String s = br.readLine();
            if (map.containsKey(s)) {
                map.put(s, Integer.valueOf(map.get(s))+1);
                pw.println(s+Integer.valueOf(map.get(s)));
            } else {
                map.put(s, 0);
                pw.println("OK");
            }
        }
        
        pw.close();
    }
}