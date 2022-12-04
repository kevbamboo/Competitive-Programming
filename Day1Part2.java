import java.io.*;

public class Day1Part2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("txt.txt"));
        long max1 = -1;
        long max2 = -1;
        long max3 = -1;
        long elf = 0;
        String s;
        while ((s=br.readLine()) != null) {
            if (s.equals("")) {
                if (max1 == -1) max1 = elf;
                if (max2 == -1) max2 = elf;
                if (max3 == -1) max3 = elf;
                if (elf >= max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = elf;
                } else if (elf >= max2) {
                    max3 = max2;
                    max2 = elf;
                } else if (elf >= max3) {
                    max3 = elf;
                }
                elf = 0;
            } else {
                elf += Double.parseDouble(s);
            }
        }
        if (max1 == -1) max1 = elf;
        if (max2 == -1) max2 = elf;
        if (max3 == -1) max3 = elf;
        if (elf >= max1) {
            max3 = max2;
            max2 = max1;
            max1 = elf;
        } else if (elf >= max2) {
            max3 = max2;
            max2 = elf;
        } else if (elf >= max3) {
            max3 = elf;
        }
        System.out.println(max1+max2+max3);
    }
}
