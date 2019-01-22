import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
LANG: JAVA
PROB: wormhole
*/
public class wormhole {
    private static int N, stuckPairs = 0;
    private static long[][] holes;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("wormhole.in"));
        PrintWriter pw = new PrintWriter("wormhole.out", "UTF-8");

        N = Integer.parseInt(sc.nextLine());
        holes = new long[N][2];
        for(int i = 0;i < N; i++){
            String[] tmp = sc.nextLine().split(" ");
            holes[i][0] = Integer.parseInt(tmp[0]);
            holes[i][1] = Integer.parseInt(tmp[1]);
        }
        Arrays.sort(holes, (o1, o2) -> (int)(o1[0] - o2[0]));
        genAllPairs(null, 0, null);
        pw.print(stuckPairs);
        pw.close();

    }
    private static void genAllPairs(int[] currentCfg, int n, int[] used){
        if(n == 0) {
            currentCfg = new int[N];
            currentCfg[0] = 0;
            for(int i = 1; i < N; i++)
                currentCfg[i] = -1;
            used = new int[N/2];
            used[0] = 1;
            for(int i = 1; i < N/2; i++)
                used[i] = 0;

            genAllPairs(currentCfg, 1, used);
        }else if(n == N-1){
            for(int i = 0; i < N/2; i++){
                if(used[i] == 1){
                    currentCfg[N-1] = used[i];
                    detectStuck(currentCfg);
                    return;
                }
            }
        }else{
            for(int i = 0;i < N/2; i++){
                if(used[i] != 2){
                    used[i]++;
                    currentCfg[n] = i;
                    genAllPairs(currentCfg, n+1, used);
                }
            }
        }
    }
    private static void detectStuck(int[] config){//counts 1 config of pairs
        final int iter = 1000;
        for(int i = 0;i < N; i++){//i is current hole idx
            int holeIter = 0;
            int currentHoleIdx = 0;
            while(holeIter < iter){
                currentHoleIdx = findOutWormHole(config, currentHoleIdx);
                if(currentHoleIdx == -1)
                    return;
                holeIter++;
            }
        }
        stuckPairs++;
    }

    private static int findOutWormHole(int[] config, int currentHole){//finds the corresponding hole of WH to the right; if none returns -1
        long[] currentLocation = holes[currentHole];
        for(int i = 0;i < N; i++){
            long[] tmp = holes[i];
            if(tmp[0] > currentLocation[0] && tmp[1] == currentLocation[1]){
                for(int j = 0;j < N; j++){
                    if(j != i && config[j] == config[i]){
                        return j;
                    }
                }
            }
        }
        return -1;
    }

}