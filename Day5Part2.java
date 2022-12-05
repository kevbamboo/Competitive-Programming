import java.io.*;
import java.util.*;

public class Day5Part2 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String s;
        Stack<String> boxInput = new Stack<String>();
        while ((s = br.readLine()).charAt(1) != '1') {
            boxInput.add(s);
        }
        int numStacks = (boxInput.get(0).length()+1)/4;
        Stack[] stacks = new Stack[numStacks];
        for (int i = 0; i < stacks.length; i++) stacks[i] = new Stack<Character>();
        
        while (boxInput.size() != 0) {
            String str = boxInput.pop();
            for (int i = 1; i < str.length(); i += 4) {
                if (str.charAt(i) != ' ') stacks[(i-1)/4].push(str.charAt(i));
            }
        }
        
        br.readLine();
        while ((s = br.readLine()) != null) {
            String[] line = s.split(" ");
            int numMove = Integer.parseInt(line[1]);
            int from = Integer.parseInt(line[3]);
            int to = Integer.parseInt(line[5]);
            Stack<Character> cur = new Stack<Character>();
            while (numMove-- > 0) cur.push((Character) stacks[from-1].pop());
            while (cur.size() != 0) stacks[to-1].push(cur.pop());
        }
        for (Stack stack : stacks) {
            if (stack.size() != 0) System.out.print(stack.pop());
        }
    }
}