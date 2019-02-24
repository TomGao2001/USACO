import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/*
LANG: JAVA
PROB: traffic
*/
public class traffic {
    private static int N;
    private static int[][] sensors;
    private static int[][] output = new int[][]{{0,100000},{0,100000}};

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(new File("traffic.in"));
        PrintWriter pw = new PrintWriter("traffic.out", "UTF-8");
        N = Integer.parseInt(sc.nextLine());
        sensors = new int[N][3];
        for(int i = 0;i < N; i++){
            String[] s = sc.nextLine().split(" ");
            switch (s[0]) {
                case "none":
                    sensors[i][0] = 0;
                    break;
                case "on":
                    sensors[i][0] = 1;
                    break;
                default:
                    sensors[i][0] = 2;
                    break;
            }
            sensors[i][1] = Integer.parseInt(s[1]);
            sensors[i][2] = Integer.parseInt(s[2]);
        }
        sc.close();
        simplify_main_road();

        maxin();
        minin();
        maxout();
        minout();
        pw.println(output[0][0]+" "+output[0][1]);
        pw.println(output[1][0]+" "+output[1][1]);
        pw.close();
    }
    private static void simplify_main_road(){
        ArrayList<int[]> a = new ArrayList<>(33);
        int streak = 0;
        int startidx = -1;
        for(int i = 0;i < N; i++){
            if(sensors[i][0] == 0){
                if(streak == 0)
                    startidx = i;
                streak++;
            }else{
                if(streak > 1){
                    a.add(new int[]{startidx, streak});
                }
                streak = 0;
                startidx = -1;
            }
        }
        if(streak > 1){
            a.add(new int[]{startidx, streak});
        }
        for(int[] target : a){
            int[][] values = new int[target[1]][2];
            for(int i = target[0]; i < target[0] + target[1]; i++){
                values[i-target[0]][0] = sensors[i][1];
                values[i-target[0]][1] = sensors[i][2];
            }
            int min = values[0][0];
            int max = values[0][1];
            for(int[] i : values){
                min = Math.max(min, i[0]);
                max = Math.min(max, i[1]);
            }
            for(int i = target[0]; i < target[0] + target[1]; i++){
                sensors[i][1] = min;
                sensors[i][2] = max;
            }
        }
    }
    private static void maxin(){
        int in_max = 100000;
        while(true){
            int current_max_val = in_max;
            boolean badFlag = false;
            int i = 0;
            while(!badFlag && i<N){
                if(sensors[i][0] == 0) {//main road
                    if(current_max_val > sensors[i][2]){
                        in_max--;
                        badFlag = true;
                    }
                }else if(sensors[i][0] == 1){
                    current_max_val += sensors[i][1];
                }else{
                    current_max_val -= sensors[i][2];
                }
                i++;
            }
            if(!badFlag){
                output[0][1] = in_max;
                return;
            }
        }
    }
    private static void minin(){
        int in_min = 0;
        while(true){
            int current_min_val = in_min;
            boolean badFlag = false;
            int i = 0;
            while(!badFlag && i<N){
                if(sensors[i][0] == 0) {//main road
                    if(current_min_val < sensors[i][1]){
                        in_min++;
                        badFlag = true;
                    }
                }else if(sensors[i][0] == 1){
                    current_min_val += sensors[i][2];
                }else{
                    current_min_val -= sensors[i][1];
                }
                i++;
            }
            if(!badFlag){
                output[0][0] = in_min;
                return;
            }
        }
    }
    private static void maxout(){
        int out_max = 100000;
        while(true){
            int current_min_val = out_max;
            boolean badFlag = false;
            int i = N-1;
            while(!badFlag && i>=0){
                if(sensors[i][0] == 0) {//main road
                    if(current_min_val > sensors[i][2]){
                        out_max--;
                        badFlag = true;
                    }
                }else if(sensors[i][0] == 1){
                    current_min_val -= sensors[i][2];
                }else{
                    current_min_val += sensors[i][1];
                }
                i--;
            }
            if(!badFlag){
                output[1][1] = out_max;
                return;
            }
        }
    }
    private static void minout(){
        int out_min = 0;
        while(true){
            int current_min_val = out_min;
            boolean badFlag = false;
            int i = N-1;
            while(!badFlag && i>=0){
                if(sensors[i][0] == 0) {//main road
                    if(current_min_val < sensors[i][1]){
                        out_min++;
                        badFlag = true;
                    }
                }else if(sensors[i][0] == 1){
                    current_min_val -= sensors[i][1];
                }else{
                    current_min_val += sensors[i][2];
                }
                i--;
            }
            if(!badFlag){
                output[1][0] = out_min;
                return;
            }
        }
    }


}