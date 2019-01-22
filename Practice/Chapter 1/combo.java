import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/*
LANG: JAVA
PROB: combo
*/
public class combo {
    private static int N;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("combo.in"));
        PrintWriter pw = new PrintWriter("combo.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        if(N > 5) {
            int[][] unlocks = new int[2][3];
            for(int i = 0;i < 2; i++){
                String[] s = sc.nextLine().split(" ");
                unlocks[i][0] = Integer.parseInt(s[0]);
                unlocks[i][1] = Integer.parseInt(s[1]);
                unlocks[i][2] = Integer.parseInt(s[2]);
            }
            sc.close();

            ArrayList<int[]> solutions = new ArrayList<>(250);
            for(int i = 0;i < 2; i++){
                for(int i1 : genChoices(unlocks[i][0])){
                    for(int i2 : genChoices(unlocks[i][1])){
                        for(int i3 : genChoices(unlocks[i][2])){
                            addUnique(new int[]{i1, i2, i3}, solutions);
                        }
                    }
                }
            }
            /*
            ArrayList<int[]> test = new ArrayList<>(solutions);
            int[][] a = test.toArray(new int[0][]);
            Arrays.sort(a, Comparator.comparingInt(o -> o[2]));
            Arrays.sort(a, Comparator.comparingInt(o -> o[1]));
            Arrays.sort(a, Comparator.comparingInt(o -> o[0]));
            */



            pw.println(solutions.size());
            pw.close();

        }else{
            sc.close();
            pw.println((int)Math.pow(N, 3));
            pw.close();
        }

    }
    private static int[] genChoices(int target){
        int[] dial = new int[N + 4];//N = 4: 3,4,1,2,3,4,1,2 (2 on each side of everything)
        dial[0] = N - 1;
        dial[1] = N;
        for (int i = 1; i <= N; i++) {
            dial[i+1] = i;
        }
        dial[N+2] = 1;
        dial[N+3] = 2;

        return new int[]{dial[target - 1], dial[target], dial[target + 1], dial[target + 2], dial[target + 3]};
    }
    private static void addUnique(int[] target, ArrayList<int[]> list){
        boolean add = true;
        for(int[] a : list){
             if(Arrays.equals(target, a))
                 add = false;
        }
        if(add)
            list.add(target);
    }
}