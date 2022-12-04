import java.io.*;

public class Rucksack2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        long totalPriority = 0;
        String s;
        while ((s = br.readLine()) != null) {
            totalPriority += priority(s, br.readLine(), br.readLine());
        }
        System.out.println(totalPriority);
    }
    public static int priority(String a, String b, String c) throws IOException {
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                for (int k = 0; k < c.length(); k++) {
                    if (a.charAt(i) == b.charAt(j) && (a.charAt(i) == c.charAt(k))) {
                        char same = a.charAt(i);
                        if (same < 91) return same-64+26;
                        else return same-96;
                    }
                }
            }
        }
        throw new IOException();
    }
}