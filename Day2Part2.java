import java.io.*;

public class Day2Part2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        long points = 0;
        String s;
        while ((s = br.readLine()) != null) {
            points += points(s.charAt(0), s.charAt(2));
        }
        
        System.out.println(points);
    }
    public static int points(char other, char result) throws IOException {
        if (other == 'A' && result == 'X') return 3;
        if (other == 'A' && result == 'Y') return 4;
        if (other == 'A' && result == 'Z') return 8;
        if (other == 'B' && result == 'X') return 1;
        if (other == 'B' && result == 'Y') return 5;
        if (other == 'B' && result == 'Z') return 9;
        if (other == 'C' && result == 'X') return 2;
        if (other == 'C' && result == 'Y') return 6;
        if (other == 'C' && result == 'Z') return 7;
        throw new IOException();
    }
}