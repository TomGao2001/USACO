import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
LANG: JAVA
PROB: crypt1
*/
public class crypt1 {
    private static int N;
    private static char[] digits;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("crypt1.in"));
        PrintWriter pw = new PrintWriter("crypt1.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        digits = new char[N];
        int result = 0;
        String s = sc.nextLine();
        for(int i = 0;i < N; i++){
            digits[i] = s.charAt(2*i);
        }
        Arrays.sort(digits);
        sc.close();

        ArrayList<Integer> hundreds = new ArrayList<>((int)Math.pow(digits.length, 3));
        ArrayList<Integer> tens = new ArrayList<>((int)Math.pow(digits.length, 2));

        for(char c1 : digits){
            for(char c2 : digits){
                tens.add(Integer.parseInt("" + c1 + c2));
                for(char c3 : digits){
                    hundreds.add(Integer.parseInt("" + c1 + c2 + c3));
                }
            }
        }

        for(int i1 : hundreds){
            for(int i2 : tens){
                if(goodCrypt(i1, i2)){
                    result++;
                }
            }
        }
        pw.println(result);
        pw.close();

    }
    private static boolean goodCrypt(int hundreds, int tens){
        if(badNum(hundreds, 3) || badNum(tens, 2))
            return false;
        if(badNum(hundreds * (tens % 10), 3))
            return false;
        if(badNum(hundreds * (tens / 10), 3))
            return false;
        if(badNum(hundreds * tens, 4))
            return false;
        return true;
    }
    private static boolean badNum(int number, int length){
        int highLim = (int) Math.pow(10, length);
        int lowLim = (int) Math.pow(10, length - 1);
        if(number >= highLim || number < lowLim)
            return true;
        char[] c = Integer.toString(number).toCharArray();
        for(char ch : c){
            if(Arrays.binarySearch(digits, ch) < 0)
                return true;
        }
        return false;
    }
}