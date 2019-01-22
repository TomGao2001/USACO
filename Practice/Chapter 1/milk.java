import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
LANG: JAVA
PROB: milk
*/
public class milk {
    private static int N, M;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("milk.in"));
        PrintWriter pw = new PrintWriter("milk.out", "UTF-8");
        String s = sc.nextLine();
        N = Integer.parseInt(s.split(" ")[0]);
        M = Integer.parseInt(s.split(" ")[1]);
        int[][] source = new int[M][3];//price, #available, #bought
        for(int i = 0;i < M; i++){
            s = sc.nextLine();
            source[i][0] = Integer.parseInt(s.split(" ")[0]);
            source[i][1] = Integer.parseInt(s.split(" ")[1]);
        }
        sc.close();
        Arrays.sort(source, (o1, o2) -> (o1[0] - o2[0]));
        int totalMilk = 0, totalPrice = 0, index = 0;

        while(totalMilk < N){
            if(source[index][2] == source[index][1]){
                index++;
            }else{
                source[index][2]++;
                totalMilk++;
                totalPrice += source[index][0];
            }
        }
        while(totalMilk > N){

        }

        pw.println(totalPrice);
        pw.close();

    }
}