import java.io.*;

public class Day6Part1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String s = br.readLine();
        for (int i = 4; i < s.length(); i++) {
            char[] arr = new char[4];
            for (int j = 0; j < arr.length; j++) arr[j] = s.charAt(j-4+i);
            
            if (allDifferent(arr)) {
                System.out.println(i);
                return;
            }
        }
    }
    public static boolean allDifferent(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) continue;
                if (arr[i] == arr[j]) return false;
            }
        }
        return true;
    }
}