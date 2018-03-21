package lintcode.a.plus.b;

/**
 * Created by 燃烧杯 on 2018/3/9.
 * 给出两个整数a和b, 求他们的和, 但不能使用 + 等数学运算符
 */
public class Solution1 {

    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public static int aplusb(int a, int b) {
        // write your code here
        int bit[] = new int[32];
        for ( int i = 0; i < bit.length; i++ ){
            bit[i] = 0x00000001 << i;
        }

        boolean flag = false;
        int result = 0x00000000;
        final int _0 = 0x00000000;
        final int _1 = 0x00000001;
        for ( int i = 0; i < bit.length; i++ ){
            int ruler = bit[i];
            int a_i = (a & ruler) >>> i;
            int b_i = (b & ruler) >>> i;

            if ( a_i == _1 && b_i == _1 ){
                if ( flag ){
                    result = result | ruler;
                }
                flag = true;
                continue;
            }
            if ( a_i == _1 || b_i == _1 ){
                if ( !flag ){
                    result = result | ruler;
                    flag = false;
                } else {
                    flag = true;
                }
                continue;
            }
            if ( a_i == _0 && b_i == _0 ){
                if (flag){
                    result = result | ruler;
                }
                flag = false;
                continue;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(aplusb(-1,-1));
    }

}
