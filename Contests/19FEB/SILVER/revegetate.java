import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/*
LANG: JAVA
PROB: revegetate
*/
public class revegetate {
    private static int N, M;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("revegetate.in"));
        PrintWriter pw = new PrintWriter("revegetate.out", "UTF-8");
        String[] s = sc.nextLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);


    }
}