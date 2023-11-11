import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Integer> aa = new ArrayList<Integer>();
        aa.add(1);
        aa.add(2);
        aa.add(3);
        aa.add(4);

        System.out.println(aa.remove(new Integer(1)));
        System.out.println(aa);
    }
}
