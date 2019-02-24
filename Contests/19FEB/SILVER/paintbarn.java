import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/*
LANG: JAVA
PROB: paintbarn
*/
public class paintbarn {
    private static int N, K;
    private static ArrayList<int[]>[] paints;
    private static ArrayList<int[]> combinations;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("paintbarn.in"));
        PrintWriter pw = new PrintWriter("paintbarn.out", "UTF-8");
        String[] s = sc.nextLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        paints = new ArrayList[N];
        for(int i = 0;i < N; i++){
            s = sc.nextLine().split(" ");
            paints[i] = getSet(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]));
        }
        sc.close();
        genCombinationHelper(N, K);
        int sum = 0;
        for(int[] i : combinations){
            ArrayList<int[]> tmp = (ArrayList<int[]>) paints[i[0]].clone();
            for(int j : i){
                keepIntersections(tmp, paints[j]);
            }
            sum += tmp.size();
        }
        pw.println(sum);
        pw.close();
    }
    private static ArrayList<int[]> getSet(int x1, int y1, int x2, int y2){
        ArrayList<int[]> points = new ArrayList<int[]>((x2-x1)*(y2-y1));
        for(int x = x1; x < x2; x++)
            for(int y = y1; y < y2; y++)
                points.add(new int[]{x,y});
        return points;
    }
    private static void genCombinationHelper(int n, int c) {
        combinations = new ArrayList<>(10000);
        int[] a = new int[n];
        for(int i = 0;i < n; i++)
            a[i] = i;
        genCombinations(a,c,0,new int[c]);
    }

    private static void genCombinations(int[] arr, int len, int startPosition, int[] result){
        if (len == 0){
            combinations.add(result.clone());
            return;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
                result[result.length - len] = arr[i];
                genCombinations(arr, len-1, i+1, result);
        }
    }
    private static void keepIntersections(ArrayList<int[]> original, ArrayList<int[]> compare){
        boolean[] removeFlag = new boolean[original.size()];
        for(int i = 0;i < original.size(); i++){
            boolean hasit = false;
            for(int[] j : compare){
                if(Arrays.equals(j, original.get(i)))
                    hasit = true;
            }
            if(!hasit){
                removeFlag[i] = true;
            }
        }
        for(int i = original.size()-1;i > -1; i--){
            if(removeFlag[i])
                original.remove(i);
        }
    }
}