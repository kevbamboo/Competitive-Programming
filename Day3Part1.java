import java.io.*;

public class Day3Part1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        long totalPriority = 0;
        String s;
        while ((s = br.readLine()) != null) {
            totalPriority += priority(s);
        }
        System.out.println(totalPriority);
    }
    public static int priority(String s) throws IOException {
        int mid = s.length()/2;
        for (int i = 0; i < mid; i++) {
            for (int j = mid; j < s.length(); j++) {
                if (s.substring(i,i+1).equals(s.substring(j,j+1))) {
                    char c = s.charAt(i);
                    if (c < 91) return c-64+26;
                    else return c-96;
                }
            }
        }
        throw new IOException();
    }
}
