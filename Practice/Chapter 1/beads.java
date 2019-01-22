/*
LANG: JAVA
PROB: beads
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class beads {
    private static int N;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("beads.in"));
        N = Integer.parseInt(sc.nextLine());
        String beads = sc.nextLine();
        char[] allBeads = (beads + beads).toCharArray();
        int maxBeads = 0;
        sc.close();

        for(int i = N / 2 - 1 ;i < N * 1.5 - 1; i++){//brute force
            int maxLen = 0;
            maxLen = Math.max(maxLen, breakMyAss(allBeads, i, 'r', 'r'));
            maxLen = Math.max(maxLen, breakMyAss(allBeads, i, 'r', 'b'));
            maxLen = Math.max(maxLen, breakMyAss(allBeads, i, 'b', 'r'));
            maxLen = Math.max(maxLen, breakMyAss(allBeads, i, 'b', 'b'));
            maxBeads = Math.max(maxBeads, Math.min(maxLen, N));
        }

        PrintWriter pw = new PrintWriter("beads.out", "UTF-8");
        pw.println(maxBeads);
        pw.close();
    }
    private static int breakMyAss(char[] necklace, int breakpoint, char c1, char c2){
        char[] beads = necklace.clone();
        for(int i = 0;i <= breakpoint - 1; i++){
            if(beads[i] == 'w'){beads[i] = c1;}
        }
        char color = beads[breakpoint];
        char nextColor = color;
        int count = 0;
        while(nextColor == color){
            count++;
            if(breakpoint - count < 0) {
                if(count >= N){
                    return N;
                }else{
                    return 0;
                }
            }
            nextColor = beads[breakpoint - count];
        }

        for(int i = 0;i < beads.length; i++){
            if(beads[i] == 'w'){beads[i] = c2;}
        }
        color = beads[breakpoint + 1];
        nextColor = color;
        int ctr = 0;
        while(nextColor == color){
            ctr++;
            if(breakpoint + 1 + ctr >= beads.length){
                if(ctr >= N){
                    return N;
                }else{
                    return 0;
                }
            }
            nextColor = beads[breakpoint + 1 + ctr];
        }
        return count + ctr;
    }
}