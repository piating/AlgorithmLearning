package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 栈队列
 */
public class StackingStudy {

    public static void main(String[] args) {
        // System.out.println(isValid("()[]{}"));
        // String[] strings = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        // System.out.println(evalRPN(strings));

        int[] ints = {-5,-9,67,-10,4,-57,47,13,-67,-26,-57,63,38,-68,62,-45,-37,95,49,-91,-53,-42,-33,80,78,-30,-36,22,9,-86,79,-1,44,-92,30,-68,-94,58,-51,-26,-38,5,-74,25,71,-93,52,-12,-86,7,-86,53,78,-31,-5,-87,88,-98,-39,9,99,23,96,-90,51,-64,35,-73,9,60,-78,70,99,14,70,71,-78,50,7,46,-89,57,-1,87,-87,-95,48,49,79,-54,31,28,-27,75,31,-76,-12,35,40,-90,-60,-60,-7,67,73,-34,-42,-35,61,51,18,95,16,78,-81,-91,-30,92,57,-79,5,41,29,72,-62,-47,80,29,1,-21,-36,5,82,4,-12,-52,-56,-47,-68,95,85,-87,-7,-12,98,75,-64,-93,11,73,-81,-9,-12,-9,51,17,-94,33,-9,57,-35,10,-17,87,-18,-55,-52,30,-62,73,35,-74,-47,-63,77,-72,-55,5,73,21,14,7,-65,-51,-55,-49,98,-20,-22,-68,34,-20,92,55,47,-20,6,-54,-12,3,75,69,60,15,88,64,2,-27,-50,55,73,46,-15,-64,93,-47,-75,-55,-75,21,-57,91,-12,-99,-68,-56,-14,-4,-77,-94,55,93,-31,68,-12,-23,59,-56,-86,43,83,-93,-78,-11,-7,96,-3,-87,-37,19,-78,67,-29,77,-28,91,-73,-68,-22,18,-7,3,15,77,99,31,-48,-86,-45,-82,52,-39,8,-88,-83,-58,-77,5,87,-61,50,32,-66,-36,60,-53,52,70,-36,-1,83,-56,33,98,-80,28,1,-21,-50,-60,44,99,18,83,-29,83,-36,-55,-6,96,-60,61,75,6,-57,2,82,62,-27,36,60,72,92,61,-65,79,-57,-34,-23,-28,-55,53,36,-80,5,-76,64,-81,-32,-43,-1,50,-16,-72,-74,22,88,28,-79,-99,85,-13,-34,-76,85,6,21,-99,10,-46,79,11,-70,17,47,-22,-62,0,6,75,-19,57,-25,-52,-83,90,21,95,52,68,47,-12,76,-9,-65,86,90,16,74,64,26,84,64,-42,97,-72,53,-76,11,89,-62,67,100,15,53,68,-16,24,11,-77,20,59,-95,-50,-20,27,45,94,13,-93,86,49,12,19,17,-33,-52,-28,71,79,-19,-73,40,-99,83,77,19,-20,98,86,-5,-5,73,18,100,73,-45,33,3,89,32,-53,73,16,-3,-26,-80,49,-78,67,31,1,-85,-44,-91,-68,75,-74,95,23,89,99,-84,54,-93,68,0,-41,66,-15,-27,-23,-9,-68,37,45,-69,57,80,10,-64,35,26,55,-67,31,-76,36,-99,21};
        System.out.println(topKFrequent(ints, 7));
    }


    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = map.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        }).collect(Collectors.toList());
        int[] ints = new int[k];
        for (int i = 0; i < k; i++) {
            ints[i] = list.get(i).getKey();
        }
        return ints;
    }
    public static boolean isValid(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }

        return s.length() == 0;
    }

    /**
     * 1047.删除字符串中的所有相邻重复项
     * @param s
     * @return
     */
    public static String removeDuplicates(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (!deque.isEmpty() && deque.peek().equals(temp)) {
                deque.pop();
            } else {
                deque.push(temp);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            res.append(deque.removeLast());
        }
        return res.toString();
    }

    /**
     * 150.逆波兰表达式求值
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        Deque<Integer> deque = new LinkedList<>();
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }
        int res = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    res = deque.pop() + deque.pop();
                    deque.push(res);
                    break;
                case "-":
                    int tempDel = deque.pop();
                    res = deque.pop() - tempDel;
                    deque.push(res);
                    break;
                case "*":
                    res = deque.pop() * deque.pop();
                    deque.push(res);
                    break;
                case "/":
                    int temp =  deque.pop();
                    res = deque.pop() / temp;
                    deque.push(res );
                    break;
                default:
                    deque.push(Integer.parseInt(token));

            }
        }
        return res;
    }

    /**
     * 239.滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Deque<Integer> deque = new LinkedList<>();
        // int sum = nums[0];
        // for (int i = 1; i < k; i++) {
        //     sum = Math.max(sum, nums[i]);
        // }
        // deque.push(sum);
        // int left = 0;
        // int right = k;
        // for (; right < nums.length; right++) {
        //     int temp = ;
        //     for (int i = left + 1; i < right; i++) {
        //         temp = Math.max(temp)
        //         break;
        //     }
        // }
        return null;
    }

    // public int getBigBest(Deque<Integer> deque){
    //
    // }
}

/**
 * 232.用栈实现队列
 */
class MyQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MyQueue() {
        // 入栈
        stack1 = new Stack<>();
        // 出栈
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        dumpStack1();
        return stack2.pop();
    }

    public int peek() {
        dumpStack1();
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    /**
     * 如果stack2为空，那么将stack1中的元素全部放到stack2中
     */
    private void dumpStack1() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

}

/**
 * 225. 用队列实现栈
 */
class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> queueTemp;
        queueTemp = queue1;
        queue1 = queue2;
        queue2 = queueTemp;
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }

}
