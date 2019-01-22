import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
LANG: JAVA
PROB: barn1
*/
public class barn1 {
    private static int M, S, C, result;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("barn1.in"));
        PrintWriter pw = new PrintWriter("barn1.out", "UTF-8");
        String[] s = sc.nextLine().split(" ");
        M = Integer.parseInt(s[0]);//max. purchase number
        S = Integer.parseInt(s[1]);//total num. stalls
        C = Integer.parseInt(s[2]);//num. cows in stalls
        boolean[] occupado = new boolean[S];
        for(int i = 0;i < C; i++){
            occupado[Integer.parseInt(sc.nextLine()) - 1] = true;
        }
        sc.close();
        int[] gaps = toGapLenArray(occupado);
        Arrays.sort(gaps);
        int result = C;
        for(int i = 0;i < (gaps.length + 1 - M); i++)
            result += gaps[i];
        pw.println(result);
        pw.close();

    }

    private static int[] toGapLenArray(boolean[] occupado){
        ArrayList<Integer> gapLens = new ArrayList<>();
        int i = 0, ctr = 0;
        while(!occupado[i])
            i++;
        while(occupado[i])
            i++;
        while(i < occupado.length){
            while(i < occupado.length && !occupado[i]){
                ctr++;
                i++;
            }
            gapLens.add(ctr);
            ctr = 0;
            while(++i < occupado.length && occupado[i]);
        }
        if(!occupado[occupado.length - 1]){
            gapLens.remove(gapLens.size() - 1);
        }
        int[] result = new int[gapLens.size()];
        for(i = 0;i < result.length; i++){
            result[i] = gapLens.get(i);
        }
        return result;
    }
}