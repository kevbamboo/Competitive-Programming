import java.io.*;

public class Day4Part2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int numContained = 0;
        String s;
        while ((s = br.readLine()) != null) {
            String[] elves = s.split(",");
            String[] elf1 = elves[0].split("-");
            int elf1Start = Integer.parseInt(elf1[0]);
            int elf1End = Integer.parseInt(elf1[1]);
            
            String[] elf2 = elves[1].split("-");
            int elf2Start = Integer.parseInt(elf2[0]);
            int elf2End = Integer.parseInt(elf2[1]);
            if (elf1Start <= elf2Start && elf1End >= elf2Start) numContained++;
            else if (elf1Start >= elf2Start && elf1Start <= elf2End) numContained++;
            else if (elf2Start <= elf1Start && elf2End >= elf1Start) numContained++;
            else if (elf2Start >= elf1Start && elf2Start <= elf1End) numContained++;
        }
        System.out.println(numContained);
    }
}
