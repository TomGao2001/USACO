import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/*
LANG: JAVA
PROB: skidesign
*/
public class skidesign {
    private static int[] slopes;
    private static int N;
    private static int lowestCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("skidesign.in"));
        PrintWriter pw = new PrintWriter("skidesign.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        slopes = new int[N];
        for(int i = 0;i < N; i++)
            slopes[i] = Integer.parseInt(sc.nextLine());
        sc.close();
        findLowestCost();
        pw.println(lowestCost);
        pw.close();
    }

    private static void findLowestCost(){
        for(int i = 0;i < 100; i++){
            int currentCost = 0;
            for(int j = 0; j < N; j++){
                if(slopes[j] < i)
                    currentCost += Math.pow((i - slopes[j]),2);
                if(slopes[j] - i > 17)
                    currentCost += Math.pow((slopes[j] - i - 17),2);
            }
            lowestCost = Math.min(lowestCost, currentCost);
        }
    }
}