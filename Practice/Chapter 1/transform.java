import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Scanner;

/*
LANG: JAVA
PROB: transform
*/
public class transform {
    private static int N;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("transform.in"));
        PrintWriter pw = new PrintWriter("transform.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        char[][] before = new char[N][N];
        char[][] after = new char[N][N];
        for(int i = 0;i < N; i++){
            String s = sc.nextLine();
            for(int j = 0;j < N; j++){
                before[i][j] = s.charAt(j);
            }
        }
        for(int i = 0;i < N; i++){
            String s = sc.nextLine();
            for(int j = 0;j < N; j++){
                after[i][j] = s.charAt(j);
            }
        }
        sc.close();
        pw.println(testTransform(before,after));
        pw.close();



    }
    private static int testTransform(char[][] before, char[][] after){
        if(arrayEqual(cw90(before), after))
            return 1;
        if(arrayEqual(cw180(before), after))
            return 2;
        if(arrayEqual(cw270(before), after))
            return 3;
        if(arrayEqual(reflect(before), after))
            return 4;
        char[][] ref = reflect(before);
        if(arrayEqual(cw90(ref), after) || arrayEqual(cw180(ref), after) || arrayEqual(cw270(ref), after))
            return 5;
        if(arrayEqual(before, after))
            return 6;
        return 7;
    }
    private static char[][] cw90(char[][] original){
        char[][] result = new char[N][N];
        for(int i = 0;i < N; i++){
            for(int j = 0;j < N; j++){
                result[j][N-1-i] = original[i][j];
            }
        }
        return result;
    }
    private static char[][] cw180(char[][] original){
        char[][] result = new char[N][N];
        for(int i = 0;i < N; i++){
            for(int j = 0;j < N; j++){
                result[N-1-i][N-1-j] = original[i][j];
            }
        }
        return result;
    }
    private static char[][] cw270(char[][] original){
        return cw90(cw180(original));
    }
    private static char[][] reflect(char[][] original){
        char[][] result = new char[N][N];
        for(int i = 0;i < N; i++){
            for(int j = 0;j < N; j++){
                result[i][N-1-j] = original[i][j];
            }
        }
        return result;
    }
    private static boolean arrayEqual(char[][] a, char[][] b){
        for(int i = 0;i < a.length; i++){
            for(int j = 0;j < a.length; j++){
                if(a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
}