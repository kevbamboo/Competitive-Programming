import java.io.*;

public class CalorieCounting1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        long max = -1;
        long elf = 0;
        String s;
        while ((s = br.readLine()) != null) {
            if (s.equals("")) {
                if (elf > max) max = elf;
                elf = 0;
            } else {
                elf += Double.parseDouble(s);
            }
        }
        
        System.out.println(max);
    }
}