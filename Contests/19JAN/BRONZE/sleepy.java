import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/*
LANG: JAVA
PROB: sleepy
*/
public class sleepy {
    private static int N;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("sleepy.in"));
        PrintWriter pw = new PrintWriter("sleepy.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        int lowest_sort = 99999;
        int[] init_cow = new int[N];
        String[] s = sc.nextLine().split(" ");
        for(int i = 0;i < N; i++){
            init_cow[i] = Integer.parseInt(s[i]);
        }
        pw.print(N - backSorted(init_cow));
        pw.close();
    }

    private static int backSorted(int[] cows){
        int sortedCount = 1;
        for(int i = cows.length - 1; i > 0; i--){
            if(cows[i] > cows[i-1]){
                sortedCount++;
            }else{return sortedCount;}
        }
        return sortedCount;
    }

}