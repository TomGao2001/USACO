import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
LANG: JAVA
PROB: wormhole
*/
public class wormhole {
    private static int N, stuckPairs = 0;
    private static long[][] holes;
    private static ArrayList<int[]> configs = new ArrayList<>(100000);
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
        int[] currentCfg = new int[N];
        for(int i = 0; i < N; i++)
            currentCfg[i] = -1;
        boolean[] used = new boolean[N/2];
        for(int i = 0; i < N/2; i++)
            used[i] = false;
        genAllPairs(currentCfg, 0, used);
        pw.println(stuckPairs);
        /*for(int[] i : configs){
            for(int j : i){
                pw.print(j + " ");
            }
            pw.println();
        }*/
        pw.close();

    }
    private static void genAllPairs(final int[] currentCfg, final int nPairs, final boolean[] used){
        if(nPairs == 0){
            currentCfg[0] = 0;
            used[0] = true;
            for(int i = 1; i < N; i++){
                boolean[] tempUsed = used.clone();
                int[] tmpCfg = currentCfg.clone();
                tmpCfg[i] = 0;
                genAllPairs(tmpCfg, 1, tempUsed);
            }
        }else if(nPairs == N/2-1){
            detectStuck(currentCfg);
        }else{
            int nextUp = findNextUnused(used);
            currentCfg[findNextNullLoc(currentCfg)] = nextUp;
            used[nextUp] = true;
            int[] nextIdxs = findNullLocs(currentCfg, nPairs);
            for(int i : nextIdxs){
                int[] tmp = currentCfg.clone();
                tmp[i] = nextUp;
                boolean[] tempUsed = used.clone();
                genAllPairs(tmp, 1 + nPairs, tempUsed);
            }
        }

        /*if(n == N - 1){
            for(int i = 0; i < N/2; i++){
                if(used[i] == 1){
                    currentCfg[N-1] = i;
                    if(cfgListHasNoEntry(currentCfg, configs)){
                        configs.add(currentCfg.clone());
                        detectStuck(currentCfg);
                    }
                    return;
                }
            }
        }else{
            for(int i = 0;i < N/2; i++){
                if(used[i] != 2){
                    int[] usedTmp = used.clone();
                    usedTmp[i]++;
                    int[] cfgTmp = currentCfg.clone();
                    cfgTmp[n] = i;
                    genAllPairs(cfgTmp, n+1, usedTmp);
                }
            }
        }*/
    }
    private static void detectStuck(int[] config){//counts 1 config of pairs
        final int iter = 1000;
        for(int i = 0;i < N; i++){//i is current hole idx
            int holeIter = 0;
            int currentHoleIdx = findOutWormHole(config, i);
            while(holeIter < iter){
                if(currentHoleIdx == -1)
                    break;
                currentHoleIdx = findOutWormHole(config, currentHoleIdx);
                holeIter++;
            }
            if(currentHoleIdx != -1) {
                stuckPairs++;
                return;
            }
        }
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

    private static int findNextNullLoc(int[] config){
        for(int i = 0;i < config.length; i++){
            if(config[i] == -1)
                return i;
        }
        return -1;
    }

    private static int findNextUnused(boolean[] used){
        for(int i = 0;i < used.length; i++){
            if(!used[i])
                return i;
        }
        return -1;
    }

    private static int[] findNullLocs(int[] config, int nPairs){
        int[] stuff = new int[N - 1 - 2 * nPairs];
        int stuffIdx = 0;
        for(int i = 0;i < config.length; i++)
            if(config[i] == -1){
                stuff[stuffIdx] = i;
                stuffIdx++;
            }

        return stuff;
    }
}