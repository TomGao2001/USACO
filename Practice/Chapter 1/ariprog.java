import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/*
LANG: JAVA
PROB: ariprog
*/
public class ariprog {
    private static int N, M;
    private static ArrayList<int[]> results = new ArrayList<>(10000);

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("ariprog.in"));
        PrintWriter pw = new PrintWriter("ariprog.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        M = Integer.parseInt(sc.nextLine());
        int index = 0;
        int startIndex = 0;
        sc.close();
        int[] allprog = new int[getArrayLength()];
        int[] progMaxK = new int[getArrayLength()];
        for(int i = 0;i <= M; i++){
            for(int j = i;j <= M; j++){
                if(!hasElement(allprog, i*i+j*j)) {
                    allprog[index] = i * i + j * j;
                    index++;
                }
            }
        }
        Arrays.sort(allprog);
        while(allprog[startIndex] == 0)
            startIndex++;
        startIndex--;


        for(int i = startIndex; i < allprog.length; i++){
            int a = allprog[i];
            for(int j = i + 1; j < allprog.length; j++){
                int b = allprog[j] - a;
                searchOne(a,b,allprog);
            }
        }

        printAllSolutions(pw);

    }
    private static boolean hasElement(int[] list, int target){
        for(int i : list)
            if(target == i)
                return true;
        return false;
    }
    private static int getArrayLength(){
        int sum = M+1;
        for(int i = M; i >= 0; i--){
            sum += i;
        }
        return sum;
    }
    private static void printAllSolutions(PrintWriter pw){
        if(results.isEmpty()){
            pw.println("NONE");
            pw.close();
            return;
        }
        Collections.sort(results, Comparator.comparingInt(o -> o[1]));
        for(int[] a : results)
            pw.println(a[0] + " " + a[1]);
        pw.close();
    }
    private static void searchOne(int a, int b, int[] list){
        for(int k = 2; k < N; k++)
            if(Arrays.binarySearch(list, a + k*b) < 0)
                return;
            
        results.add(new int[]{a,b});
    }
}