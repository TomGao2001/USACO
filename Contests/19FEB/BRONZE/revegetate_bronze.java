import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Set;

/*
LANG: JAVA
PROB: revegetate_bronze
*/
public class revegetate_bronze {
    private static int N, M;
    private static int[][] data;
    private static int[] output;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("revegetate.in"));
        PrintWriter pw = new PrintWriter("revegetate.out", "UTF-8");
        String[] s = sc.nextLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        output = new int[N];
        output[0] = 1;
        data = new int[M][2];
        for(int i = 0;i < M; i++){
            s = sc.nextLine().split(" ");
            data[i][0] = Integer.parseInt(s[0]);
            data[i][1] = Integer.parseInt(s[1]);
        }
        sc.close();
        int[][] choiceMat = genChoiceMat(data);
        tryPick(choiceMat);
        for(int i : output)
            pw.print(i);
        pw.println();
        pw.close();

    }
    private static void tryPick(int[][] choiceMat){
        for(int i = 1; i < N; i++){//for all places
            boolean doneFlag = false;
            for(int pick = 1; pick < 5; pick++){
                if(!doneFlag) {
                    if (choiceGood(output, i, choiceMat[i+1], pick)) {
                        output[i] = pick;
                        doneFlag = true;
                    }
                }
            }
        }
    }
    private static int[][] genChoiceMat(int[][] data){
        int[][] result = new int[N+1][3];

        int[] idxs = new int[N+1];
        for(int[] i : data){
            int a = i[0];
            int b = i[1];
            if(idxs[a] < 2 || !int_mat_contain(result[a],b)){
                result[a][idxs[a]] = b;
                idxs[a]++;
            }
            if(idxs[b] < 2 || !int_mat_contain(result[b],a)){
                result[b][idxs[b]] = a;
                idxs[b]++;
            }
        }
        return result;
    }
    private static boolean int_mat_contain(int[] target, int a){
        for(int i : target)
            if(a == i)
                return true;
        return false;
    }
    private static boolean choiceGood(int[] output, int pos, int[] choiceMat, int pick){
        for(int i : choiceMat){
            if(i != 0 && output[i-1] == pick)
                return false;
        }
        return true;
    }
}