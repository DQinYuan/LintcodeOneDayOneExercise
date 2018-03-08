package lintcode.longest.common.prefix;

/**
 * Created by 燃烧杯 on 2018/3/8.
 */
public class Solution1 {

    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        // write your code here
        if ( strs.length == 0 ){
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        String minString = "";
        for ( String str : strs ){
            if ( str.length() < minLength ){
                minString = str;
                minLength = str.length();
            }
        }
        int index = 0;
        while (true){
            if ( index == minLength ){
                return minString;
            }
            char current;
            char prefix = strs[0].charAt(index);
            boolean flag = false;
            for ( String str : strs ){
                current = str.charAt(index);
                if ( current != prefix ){
                    flag = true;
                    break;
                }
                prefix = current;
            }
            if ( flag ){
                break;
            }
            index++;
        }
        return strs[0].substring(0, index);
    }

    public static void main(String[] args) {
        String[] strs = {"abc","abcd","a","ab","ac"};
        System.out.println(longestCommonPrefix(strs));
    }
}
