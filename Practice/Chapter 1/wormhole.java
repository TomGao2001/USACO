import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
LANG: JAVA
PROB: wormhole
*/
public class wormhole {
    private static int N;
    /*public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("wormhole.in"));
        PrintWriter pw = new PrintWriter("wormhole.out", "UTF-8");

        N = Integer.parseInt(sc.nextLine());
        int[][] holes = new int[N][2];//each 2 are paired

        for(int i = 0;i < N; i++){
            String[] s = sc.nextLine().split(" ");
            holes[i] = new int[] {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
        }
        sc.close();//imported all WH locations
        int result = 0;

        ArrayList<int[][]> allFarms = new ArrayList<>(10395);


        for(int[][] farm : allFarms){
            //one layout
            for(int i = 0;i < N; i++){
                //one case (1 cow position, 1 layout)
                int[] bessie = new int[2];
                int[] temp = farm[1];
                bessie[0] = temp[0];
                bessie[1] = temp[1];
                int pos;
                for(int j = 0;j < 100;j++){
                    pos = WHToRight(farm, bessie[0], bessie[1]);
                    if(pos == -1)
                        break;
                    pos = findExit(pos);
                    if(j == 99)
                        result++;
                }
            }
        }



    }

    private static int WHToRight(int[][] holes, int locX, int locY){
        int[] dists = new int[N];

        for(int i = 0;i < N; i++){
            int[] wh = holes[i];
            if(wh[1] == locY && wh[0] >= locX){
                dists[i] = Math.abs(wh[0] - locX);
            }
        }
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0;i < N; i++){
            if(dists[i] != 0){
                if(min > dists[i]){
                    min = dists[i];
                    minIndex = i;
                }
            }
        }
        if(minIndex != -1)
            return minIndex;
        return -1;
    }

    private static int findExit(int index){
        if(index % 2 == 0)
            return index - 1;
        else
            return index + 1;
    }

    private static ArrayList<int[][]> getPermutations(int[][] holes){
        if(N == 2){
            ArrayList<int[][]> a = new ArrayList<>(1);
            a.set(0, holes);
            return a;
        }
        int possibilities = (factorial(N))/(factorial(N/2) * (int)Math.pow(2, N/2));
        int[][][] result = new int[possibilities][N][2];
        for(int i = 0;i < N - 2;i++){

        }
    }

    private static boolean checkNotRepSolution(int[][] solution, ArrayList<int[][]> set){

    }

    private static int factorial(int target){
        int product = 1;
        for(int i = 2;i <= target; i++){
            product *= i;
        }
        return product;
    }
*/
}