import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day11Part1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
        String s;
        while ((s = br.readLine()) != null) {
            if (!parseMonkey(br, monkeys)) break;
        }
        
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                ArrayList<Integer> items = monkey.items;
                monkey.numInspections += items.size();
                
                for (int j = 0; j < items.size(); j++) {
                    solveItem(monkeys, monkey, items, j);
                }
                
                monkey.items = new ArrayList<Integer>();
            }
        }
        int first = 0;
        int second = 0;
        for (Monkey monkey : monkeys) {
            int numInspections = monkey.numInspections;
            if (numInspections > first) {
                second = first;
                first = numInspections;
            }
            else if (numInspections > second) {
                second = numInspections;
            }
        }
        
        System.out.println(first*second);
    }
    
    // only return false if it's the end of all input
    public static boolean parseMonkey(BufferedReader br, ArrayList<Monkey> monkeys) throws IOException {
        String[] strItems = br.readLine().substring(18).split(",");
        ArrayList<Integer> items = new ArrayList<Integer>();
        for (String s : strItems) items.add(Integer.parseInt(s.trim()));
        String operation = br.readLine().substring(23);
        int test = Integer.parseInt(br.readLine().substring(21));
        int ifTrue = Integer.parseInt(br.readLine().substring(29));
        int ifFalse = Integer.parseInt(br.readLine().substring(30));
        
        monkeys.add(new Monkey(items, operation, test, ifTrue, ifFalse));
        
        if (br.readLine() == null) return false;
        else return true;
    }
    
    public static void solveItem(ArrayList<Monkey> monkeys, Monkey monkey, ArrayList<Integer> items, int itemIndex) throws IOException {
        int newItem = getNew(items.get(itemIndex), monkey.operation);
        newItem = newItem/3; // yes integer division;
        
        boolean isTrue = (newItem % monkey.test) == 0;
        
        int newIndex;
        if (isTrue) newIndex = monkey.ifTrue;
        else newIndex = monkey.ifFalse;
        monkeys.get(newIndex).items.add(newItem);
    }
    
    private static int getNew(int item, String operation) throws IOException {
        char op = operation.charAt(0);
        String strVal = operation.substring(2);
        int val;
        if (strVal.equals("old")) val = item;
        else val = Integer.parseInt(strVal);
        
        if (op == '*') return item*val;
        if (op == '+') return item+val;
        
        throw new IOException();
    }
}

class Monkey {
    ArrayList<Integer> items;
    String operation;
    int test;
    int ifTrue;
    int ifFalse;
    int numInspections;
    
    public Monkey(ArrayList<Integer> items, String operation, int test, int ifTrue, int ifFalse) {
        this.items = items;
        this.operation = operation;
        this.test = test;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        this.numInspections = 0;
    }
}