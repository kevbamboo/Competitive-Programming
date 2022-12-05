import java.io.*;

public class Day2Part1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        long points = 0;
        String s;
        while ((s = br.readLine()) != null) {
            points += points(toInt(s.charAt(0)), toInt(s.charAt(2)));
        }
        
        System.out.println(points);
    }
    public static int toInt(char c) throws IOException {
        if (c == 'A' || c == 'X') return 1;
        if (c == 'B' || c == 'Y') return 2;
        if (c == 'C' || c == 'Z') return 3;
        throw new IOException();
    }
    public static int points(int other, int you) throws IOException {
        int diff = other-you;
        if (diff == -2) return you;
        if (diff == -1) return you+6;
        if (diff == 0) return you+3; 
        if (diff == 1) return you;
        if (diff == 2) return you+6;
        throw new IOException();
    }
}