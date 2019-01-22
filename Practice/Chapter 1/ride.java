/*
LANG: JAVA
PROB: ride
 */
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
class ride {
    private static String in1, in2;

    public static void main(String[] args) {
        File file = new File("ride.in");
        try{
            Scanner sc = new Scanner(file);
            in1 = sc.nextLine();
            in2 = sc.nextLine();
            sc.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        long num1 = 1, num2 = 1;
        for (int i = 0;i < in1.length(); i++){
            num1 *= (int) (in1.charAt(i)) - 64;
        }
        for (int i = 0;i < in2.length(); i++){
            num2 *= (int) (in2.charAt(i)) - 64;
        }
        num1 %= 47;
        num2 %= 47;
        writeOut((num1 == num2 ? "GO" : "STAY"));

    }
    private static void writeOut(String result){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("ride.out", "UTF-8");
            pw.println(result);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
