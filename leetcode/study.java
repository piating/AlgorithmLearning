package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class study {

    public static void main(String[] args) {
        int[] ints = {1, 4, 4};
        study study = new study();
        System.out.println(0);
    }

    /**
     * 704：二分法
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int temp = left + ((right - left) / 2);
            if (nums[temp] < target) {
                left = temp + 1;
            } else if (nums[temp] > target) {
                right = temp - 1;
            } else {
                return temp;
            }
        }
        return -1;
    }

    /**
     * 27：移除元素，快慢指针
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] == val) {
                r++;
            } else {
                nums[l] = nums[r];
                r++;
                l++;
            }
        }
        return l;
    }

    /**
     * 977. 有序数组的平方
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1, n = r;
        int[] ints = new int[r + 1];
        while (l <= r) {
            if (Math.abs(nums[l]) > Math.abs(nums[r])) {
                ints[n] = nums[l] * nums[l];
                l++;
                n--;
            } else {
                ints[n] = nums[r] * nums[r];
                r--;
                n--;
            }
        }
        return ints;
    }

    /**
     * 209. 长度最小的子数组
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        //左指针
        int left = 0;
        //右指针
        int right = 0;
        //数据集长度，这里是字符串长度
        int len = nums.length;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        //当右指针没有达到边界时
        while (right < len) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                //左指针收缩
                sum -= nums[left];
                left++;
            }
            //右指针无脑往右滑
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 76. 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    Map<Character, Integer> tString = new HashMap<>();
    Map<Character, Integer> sString = new HashMap<>();
    public String minWindow2(String s, String t) {
        //左指针
        int left = 0, right = 0;
        //数据集长度，这里是字符串长度
        int len = Integer.MAX_VALUE;
        int lLocate = -1, rLocate = -1;
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            tString.put(c, tString.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            if (tString.containsKey(s.charAt(right))) {
                //就将这个字符记录在哈希表sString里并且个数+1
                sString.put(s.charAt(right), sString.getOrDefault(s.charAt(right), 0) + 1);
            }
            while (check()) {
                int temp = right - left + 1;
                if (temp < len) {
                    len = temp;
                    lLocate = left;
                    rLocate = left + len;
                }

                if (sString.containsKey(s.charAt(left))) {
                    sString.put(s.charAt(left), sString.get(s.charAt(left)) - 1);
                }
                left++;
            }
            right++;
        }
        return lLocate == -1 ? "" : s.substring(lLocate, rLocate);
    }

    public boolean check() {
        Iterator iterator = tString.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (sString.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 260. 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     * @param nums
     * @return
     */
    public static int[] singleNumber(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }
        List<Integer> oneList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer).equals(1)) {
                oneList.add(integer);
            }
        }
        return oneList.stream().mapToInt(Integer::valueOf).toArray();
    }


}
