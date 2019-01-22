import java.util.ArrayList;
import java.util.HashSet;

public class Sandbox {
    public static void main(String[] args){
        HashSet<int[]> test = new HashSet<>();
        test.add(new int[]{1,2,3});
        boolean a = test.contains(new int[]{1,2,3});
        test.size();
    }
}
