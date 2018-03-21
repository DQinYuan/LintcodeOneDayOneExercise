package lintcode.a.plus.b;

/**
 * Created by 燃烧杯 on 2018/3/9.
 */
public class Solution2 {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public static int aplusb(int a, int b) {
        // write your code here
        while ( b != 0 ){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(aplusb(1,100));
    }

}
