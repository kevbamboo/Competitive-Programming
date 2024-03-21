import java.io.*;
import java.util.*;

public class RudolphAnotherComp {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numCases = Integer.parseInt(br.readLine());
        while (numCases-- > 0) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int m = Integer.parseInt(token.nextToken());
            int h = Integer.parseInt(token.nextToken());
            ArrayList<Participant> participants = new ArrayList<Participant>();
            
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> times = new ArrayList<Integer>();
                token = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) times.add(Integer.parseInt(token.nextToken()));
                Collections.sort(times);
                int points = 0;
                int time = 0;
                long penalty = 0;
                for (int j = 0; j < m; j++) {
                    time += times.get(j);
                    if (time > h) break;
                    else {
                        points++;
                        penalty += time;
                    }
                }
                participants.add(new Participant((i==0), points, penalty));
            }
            Collections.sort(participants);
            for (int i = 0; i < n; i++) {
                if (participants.get(i).isRudolph) {
                    pw.println(n-i);
                    break;
                }
            }
        }
        pw.close();
    }
}

class Participant implements Comparable<Participant> {
    public boolean isRudolph;
    public int points;
    public long penalty;
    
    public Participant(boolean isRudolph, int points, long penalty) {
        this.isRudolph = isRudolph;
        this.points = points;
        this.penalty = penalty;
    }
    
    public int compareTo(Participant p) {
        if (this.points < p.points) return -1;
        if (this.points > p.points) return 1;
        
        if (this.penalty < p.penalty) return -1;
        if (this.penalty > p.penalty) return 1;
        if (this.isRudolph) return 1;
        else return -1;
    }
}