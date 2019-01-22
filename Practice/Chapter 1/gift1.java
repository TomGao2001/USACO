/*
LANG: JAVA
PROB: gift1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class gift1 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        File f = new File("gift1.in");
        Scanner sc = new Scanner(f);
        int NP = sc.nextInt();
        sc.nextLine();
        String[] names = new String[NP];
        int[] moneys = new int[NP];
        for (int i = 0;i < NP; i++){
            names[i] = sc.nextLine();
            moneys[i] = 0;
        }

        while(sc.hasNextLine()){
            String benefactor = sc.nextLine();
            String money = sc.nextLine();
            int total = Integer.parseInt(money.split(" ")[0]);
            int numPeople = Integer.parseInt(money.split(" ")[1]);

            int oneMoney = (numPeople == 0 ? 0 : total / numPeople);
            moneys[searchArray(names, benefactor)] -= numPeople * oneMoney;
            for(int i = 0; i < numPeople; i++){
                moneys[searchArray(names, sc.nextLine())] += oneMoney;
            }

        }
        sc.close();

        PrintWriter pw = new PrintWriter("gift1.out", "UTF-8");
        for(int i = 0;i < NP; i++){
            pw.println(names[i] + " " + moneys[i]);
        }
        pw.close();
    }
    private static int searchArray(String[] names, String key){
        for(int i = 0;i < names.length; i++){
            if(key.equals(names[i])){
                return i;
            }
        }
        return -1;
    }
}
