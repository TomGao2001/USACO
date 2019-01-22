import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.*;

/*
LANG: JAVA
PROB: guess
*/
public class guess {
    private static int N;
    private static ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Scanner sc = new Scanner(new File("guess.in"));
        PrintWriter pw = new PrintWriter("guess.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        TreeSet<String> set = new TreeSet<>();
        String[][] stuff = new String[N][N+2];


        int max = 0, maxind = 0;
        for(int i = 0;i < N; i++){
            String[] s = sc.nextLine().split(" ");
            int temp = Integer.parseInt(s[1]);
            if(temp > max){
                max = temp;
                maxind = i;
            }
            stuff[i] = s;

        }
        sc.close();
        ArrayList<String> champion = new ArrayList<>();
        for(int i = 2; i < stuff[maxind].length; i++){
            if(getOccurrence(stuff, stuff[maxind][i]) > 1)
                champion.add(stuff[maxind][i]);
        }
        Collections.sort(champion, (o1, o2) -> getOccurrence(stuff, o2) - getOccurrence(stuff, o1));

        for(int i = 0;i < N; i++){
            for(int j = 0; j < Integer.parseInt(stuff[i][1]); j++){
                set.add(stuff[i][j+2]);
            }
        }
        ArrayList<Integer> aaa = new ArrayList<Integer>();
        for(int i = 0;i < champion.size(); i++){
            aaa.add(i);
        }
        permute(aaa,0);


        int maxNum = 0;
        for(ArrayList<Integer> a : permutations){
            int maxTemp = 0;
            boolean[] candidates = new boolean[N];
            for(int i = 0;i < N; i++)
                candidates[i] = true;
            while(countSurvivors(candidates) > 1 && maxTemp < 500){
                int idx = 0;
                for(int i = 0;i < a.size(); i++){
                    if(a.get(i) == maxTemp){
                        idx = i;
                    }
                }
                String characteristic = champion.get(idx);
                for(int j = 0;j < N;j++){
                    if(!hasOccurrence(stuff[j], characteristic)){
                        candidates[j] = false;
                    }
                }
                maxTemp++;
            }
            maxNum = Math.max(maxNum, maxTemp);
        }


        pw.print(maxNum);


        pw.close();
    }


    private static int getOccurrence(String[][] stuff, String characteristic){
        int times = 0;
        for(String[] s : stuff){
            for(String str : s){
                if(str.equals(characteristic))
                    times++;
            }
        }
        return times;
    }
    private static boolean hasOccurrence(String[] animal, String characteristics){
        for(String s : animal)
            if (s.equals(characteristics))
                return true;
        return false;
    }
    private static int countSurvivors(boolean[] mat){
        int yes = 0;
        for(boolean b : mat)
            if(b)
                yes++;
        return yes;
    }
    private static void permute(java.util.List<Integer> arr, int k){
        if(arr.size() == 2){
            ArrayList<Integer> a = new ArrayList<>();
            a.add(0);
            a.add(1);
            permutations.add((ArrayList<Integer>) a);
            a.clear();
            a.add(1);
            a.add(0);
            permutations.add((ArrayList<Integer>) a);

        }
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            permutations.add((ArrayList<Integer>) arr);
        }
    }


}