package leetcode;

import java.util.Arrays;

/**
 * 双指针
 */
public class DoublePointerStudy {
    public static void main(String[] args) {
        DoublePointerStudy doublePointerStudy = new DoublePointerStudy();
        int[] ints = {0,1,2,2,3,0,4,2};
        System.out.println(doublePointerStudy.removeElement(ints, 2));
    }
    /**
     * 27
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res++] = nums[i];
            }
        }
        return res;
    }
    /**
     * 344, 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        int len = s.length - 1;
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[len];
            s[len--] = s[i];
            s[i] = temp;
        }
    }
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
}
