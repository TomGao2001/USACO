import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/*
LANG: JAVA
PROB: dualpal
*/
public class dualpal {
    private static int N, S;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("dualpal.in"));
        PrintWriter pw = new PrintWriter("dualpal.out", "UTF-8");

        String s = sc.nextLine();
        sc.close();
        N = Integer.parseInt(s.split(" ")[0]);
        S = Integer.parseInt(s.split(" ")[1]) + 1;
        int ctr = 0;
        while(ctr < N){
            int basecount = 0;
            for(int i = 2;i < 11; i++){
                if(isPalindrome(toBase(S,i)))
                    basecount++;
            }
            if(basecount > 1) {
                ctr++;
                pw.println(S);
            }
            S++;
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
        char[] rep = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder sb = new StringBuilder();
        int val = i;
        while(val >= base) {
            sb.insert(0, rep[val%base]);
            val = val/base;
        }
        return sb.insert(0, rep[val]).toString();
    }
}