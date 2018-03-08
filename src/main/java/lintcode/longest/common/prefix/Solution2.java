package lintcode.longest.common.prefix;

/**
 * Created by 燃烧杯 on 2018/3/8.
 * 字典树，进步不大
 */
public class Solution2 {

    public static class Trie {
        Trie[] next = new Trie[52];
        int count = 0;

        private int calIndex(char ch) {
            int index = 0;
            if (ch >= 'a' && ch <= 'z') {
                index = ch - 'a';
            }
            if (ch >= 'A' && ch <= 'Z') {
                index = 26 + ch - 'A';
            }
            return index;
        }

        public void insert(String in_str, int chi) {
            count++;
            //字符串方面的问题要注意空字符串
            if ( chi > in_str.length() - 1 ){
                return;
            }
            char current = in_str.charAt(chi);

            int index = calIndex(current);

            if (next[index] == null) {
                next[index] = new Trie();
            }

            next[index].insert(in_str, chi + 1);
        }

        public int getSuffix(String example, int chi, int strNums) {
            if ( count != strNums ) {
                return chi - 1;
            }
            if ( chi > example.length() - 1 ){
                return example.length();
            }
            char current = example.charAt(chi);
            int index = calIndex(current);
            if ( next[index] == null ){
                return chi;
            }
            return next[index].getSuffix(example, chi + 1, strNums);
        }
    }

    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        // write your code here
        //防止传空数组
        if ( strs.length == 0  ){
            return "";
        }

        Trie trie = new Trie();
        for (String str : strs) {
            trie.insert(str, 0);
        }
        int index = trie.getSuffix(strs[0], 0, strs.length);
        return strs[0].substring(0, index);
    }

    public static void main(String[] args) {
        String[] strs = {"ABC"};
        System.out.println(longestCommonPrefix(strs));
    }
}
