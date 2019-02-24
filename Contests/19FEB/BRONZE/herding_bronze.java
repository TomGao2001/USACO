import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
LANG: JAVA
PROB: herding_bronze
*/
public class herding_bronze {
    private static int min = Integer.MAX_VALUE, max = 0;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("herding.in"));
        PrintWriter pw = new PrintWriter("herding.out", "UTF-8");
        int[] initialPos = new int[3];
        String[] s = sc.nextLine().split(" ");
        sc.close();
        for(int i = 0;i < 3; i++)
            initialPos[i] = Integer.parseInt(s[i]);
        pw.println(herdMin(initialPos));
        pw.println(herdMax(initialPos));
        pw.close();
    }
    private static int herdMax(int[] cows){
        if(isHerded(cows))
            return 0;
        int largeGap = Math.max(cows[2] - cows[1], cows[1] - cows[0]);
        return largeGap-1;
    }
    private static int herdMin(int[] cows){
        if(isHerded(cows))
            return 0;
        if(cows[2] - cows[1] == 2 || cows[1] - cows[0] == 2)
            return 1;
        return 2;
    }
    private static boolean isHerded(int[] cows){
        int[] tmp = Arrays.copyOf(cows,3);
        Arrays.sort(tmp);
        return (tmp[2] - tmp[1] == 1 && tmp[1] - tmp[0] == 1);
    }
}