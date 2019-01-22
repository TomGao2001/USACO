/*
LANG: JAVA
PROB: friday
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class friday {
    public static void main(String[] args) throws FileNotFoundException {
        int[] freqs = new int[7];                                 //Saturday first
        Scanner sc = new Scanner(new File("friday.in"));
        String s = sc.nextLine();
        int yearstocome = Integer.parseInt(s);
        sc.close();
        int endYear = 1899 + yearstocome;                         //until Dec. 31st
        int currentDay = 1;
        for(int yr = 1900; yr <= endYear; yr++){
            int[] monthLengths = {31,febDays(yr), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            for(int i = 0;i < 12; i++){
                currentDay += 13;
                currentDay %= 7;
                freqs[currentDay]++;
                currentDay += monthLengths[i] - 13;
                currentDay %= 7;
            }
        }

        PrintWriter pw = new PrintWriter("friday.out");
        for(int i = 0;i < 7; i++){
            pw.print(freqs[i]);
            if(i != 6){
                pw.print(" ");
            }
        }
        pw.println();
        pw.close();

    }
    private static int febDays(int year){
        boolean leap = false;
        if(year % 4 == 0){
            leap = true;
        }
        if(year % 100 == 0){
            leap = (year % 400 == 0);
        }

        if(leap){
            return 29;
        }
        return 28;
    }
}
