import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/*
LANG: JAVA
PROB: palsquare
*/
public class palsquare {
    private static int B;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("palsquare.in"));
        PrintWriter pw = new PrintWriter("palsquare.out", "UTF-8");
        B = Integer.parseInt(sc.nextLine());
        sc.close();

        for(int i = 1; i < 301; i++) {
            if (isPalindrome(toBase(i*i, B))) {
                pw.println(toBase(i, B) + " " + toBase(i*i, B));
            }
        }




        pw.close();
    }
    private static boolean isPalindrome(String s) {
        for(int i = 0; i < s.length() / 2 + 1; i++) {
            if (s.charAt(i) != s.charAt(s.length()-i-1))
                return false;
        }
        return true;
    }
    private static String toBase(int i, int base) {
        char[] rep = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J'};
        StringBuilder sb = new StringBuilder();
        int val = i;
        while(val >= base) {
            sb.insert(0, rep[val%base]);
            val = val/base;
        }
        return sb.insert(0, rep[val]).toString();
    }
}