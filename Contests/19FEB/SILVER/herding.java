import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/*
LANG: JAVA
PROB: herding
*/
public class herding {
    private static int N;
    private static int[] initialPos;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("herding.in"));
        PrintWriter pw = new PrintWriter("herding.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        initialPos = new int[N];
        for(int i = 0;i < N; i++){
            initialPos[i] = Integer.parseInt(sc.nextLine());
        }
        sc.close();
        Arrays.sort(initialPos);
        int[] spcmat = genSpcMat(initialPos);
        pw.println(getMinMoves(initialPos, spcmat));
        pw.println(getMaxMoves(initialPos, spcmat));
        pw.close();

    }
    private static int[] genSpcMat(int[] positions){
        int[] tmp = Arrays.copyOf(positions,N);
        int[] result = new int[N-1];
        int tmpidx = 0;
        Arrays.sort(tmp);
        for(int i = 0;i < N-1; i++){
            int spcs = tmp[i+1] - tmp[i] - 1;
            if(spcs > 0){
                result[tmpidx] = spcs;
                tmpidx++;
            }
        }
        return Arrays.copyOfRange(result, 0, tmpidx);
    }
    private static int getMaxMoves(int[] positions, int[] spcMat){
        if(isHerded(positions))
            return 0;
        if(N == 3)
            return getMax(spcMat);
        return sum(spcMat)-1;
    }
    private static int getMinMoves(int[] positions, int[] spcMat){
        if(isHerded(positions))
            return 0;
        if(N == 3){
            if(positions[2] - positions[1] == 2 || positions[1] - positions[0] == 2)
                return 1;
            return 2;
        }else{
            return N/2;
        }
    }
    private static int getMax(int[] target){
        int max = 0;
        for(int i : target)
            max = Math.max(max, i);
        return max;
    }
    private static boolean isHerded(int[] positions){
        int[] tmp = Arrays.copyOf(positions, N);
        for(int i = 0;i < N-1; i++)
            if(tmp[i+1]-tmp[i] != 1)
                return false;
        return true;
    }
    private static int sum(int[] target){
        int sum = 0;
        for(int i : target)
            sum+=i;
        return sum;
    }
}