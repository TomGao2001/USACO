import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/*
LANG: JAVA
PROB: namenum
*/
public class namenum {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("namenum.in"));
        String s = sc.nextLine();
        ArrayList<String> result = new ArrayList<>();
        sc.close();
        int[] orig = new int[s.length()];
        for(int i = 0;i < s.length(); i++){
            orig[i] = s.charAt(i) - '0';
        }
        Scanner dictsc = new Scanner(new File("dict.txt"));
        String[] dict = new String[4617];
        for(int i = 0; i < 4617; i++){
            dict[i] = dictsc.nextLine();
        }
        dictsc.close();

        for(String str : dict){
            if(str.length() == s.length()){
                result.add(str);
            }
        }
        Predicate<String> removeIt = str -> (str.contains("Q")||str.contains("Z"));
        result.removeIf(removeIt);

        for(int i = 0;i < s.length(); i++){
            char[] choices = getLetter(orig[i]);
            for(int j = 0;j < result.size(); j++){
                String str = result.get(j);
                char c = str.charAt(i);
                if(c != choices[0] && c != choices[1] && c != choices[2]){
                    result.remove(str);
                    j--;
                }
            }
        }


        PrintWriter pw = new PrintWriter("namenum.out", "UTF-8");
        if(result.isEmpty()){
            pw.println("NONE");
        }else{
            for(String str : result){
                pw.println(str);
            }
        }
        pw.close();
    }
    private static char[] getLetter(int num) throws Exception {
        switch (num){
            case 2:
                return new char[]{'A','B','C'};
            case 3:
                return new char[]{'D','E','F'};
            case 4:
                return new char[]{'G','H','I'};
            case 5:
                return new char[]{'J','K','L'};
            case 6:
                return new char[]{'M','N','O'};
            case 7:
                return new char[]{'P','R','S'};
            case 8:
                return new char[]{'T','U','V'};
            case 9:
                return new char[]{'W','X','Y'};
        }
        throw new Exception();
    }
}