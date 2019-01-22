/*
LANG: JAVA
PROB: milk2
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class milk2 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("milk2.in"));
        int N = Integer.parseInt(sc.nextLine());
        int[][] times = new int[N][2];
        long maxTime = 0;
        for(int i = 0;i < N; i++){
            String[] s = sc.nextLine().split(" ");
            times[i][0] = Integer.parseInt(s[0]);
            times[i][1] = Integer.parseInt(s[1]);
            maxTime = Math.max(maxTime, times[i][1]);
        }
        sc.close();
        boolean[] isMilking = new boolean[(int)maxTime];
        for(int i = 0;i < N; i++){
            for(int time = times[i][0]; time < times[i][1]; time++){
                isMilking[time] = true;
            }
        }

        int strk0 = 0, strk1 = 0, max0Strk = 0, max1Strk = 0;
        for(int i = 0;i < maxTime; i++) {
            boolean milk = isMilking[i];
            if(milk){
                if(strk0 != 0){
                    max0Strk = Math.max(max0Strk, strk0);
                    strk0 = 0;
                }
                strk1++;
            }else{
                if(strk1 != 0){
                    max1Strk = Math.max(max1Strk, strk1);
                    strk1 = 0;
                }
                if(max1Strk != 0) {
                    strk0++;
                }
            }
        }
        max0Strk = Math.max(max0Strk, strk0);
        max1Strk = Math.max(max1Strk, strk1);


        PrintWriter pw = new PrintWriter("milk2.out", "UTF-8");
        pw.println(max1Strk + " " + max0Strk);
        pw.close();
    }
}
