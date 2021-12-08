package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 字符串
 */
public class StrStudy {

    public static void main(String[] args) {
        StrStudy strStudy = new StrStudy();
        char[] chars = {'h','e','l','l','0'};
        strStudy.reverseString(chars);
        System.out.println(strStudy.reverseWords(" hello world "));
    }

    /**
     * 344. 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        int j = s.length - 1;
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            j--;
        }
    }

    /**
     * 541.给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        int len = s.length();
        if (len <= k) {
            return new StringBuilder(s).reverse().toString();
        }
        String res = "";
        for (int i = 0; i < len; i += 2 * k) {
            int t = Math.min(i + k, len);
            res += new StringBuilder(s.substring(i, t)).reverse().toString();
            res += s.substring(Math.min(t, len), Math.min(i + 2 * k, len));
        }
        return res;
    }

    /**
     * 剑指 Offer 05. 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    /**
     * 151. 翻转字符串里的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        int j = arr.length - 1;
        for (int i = 0; i < arr.length / 2; i++) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            j--;
        }
        return Arrays.stream(arr).map(i -> i = i.replace(" ", "")).filter(i -> !i.equals("")).collect(Collectors.joining(" "));
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(n, s.length() - 1);
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        return 0;
    }
}
