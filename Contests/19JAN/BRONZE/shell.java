import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/*
LANG: JAVA
PROB: shell
*/
public class shell {
    private static int N;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("shell.in"));
        PrintWriter pw = new PrintWriter("shell.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        int[][] aaa = new int[N][3];
        for(int i = 0;i < N;i++){
            String[] s = sc.nextLine().split(" ");
            for(int j = 0;j < 3;j++){
                aaa[i][j] = Integer.parseInt(s[j]);
            }
        }

        int maxguess = 0;

        for(int i = 0;i < 3; i++){
            boolean[] shells = {false,false,false};
            int currentMax = 0;
            shells[i] = true;
            for(int j = 0;j < N; j++){
                int indexA = aaa[j][0] - 1, indexB = aaa[j][1] - 1;
                if(shells[indexA] || shells[indexB]){//has to swap
                    shells[indexA] = !shells[indexA];
                    shells[indexB] = !shells[indexB];
                }
                if(shells[aaa[j][2] - 1])currentMax++;
            }
            //run simu
            maxguess = Math.max(maxguess,currentMax);
            //store max
        }
        pw.print(maxguess);
        pw.close();
    }
}