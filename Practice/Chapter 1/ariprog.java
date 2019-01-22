import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/*
LANG: JAVA
PROB: ariprog
*/
public class ariprog {
    private static int N, M;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("ariprog.in"));
        PrintWriter pw = new PrintWriter("ariprog.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        M = Integer.parseInt(sc.nextLine());
        sc.close();

    }
}