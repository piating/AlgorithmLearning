package leetcode;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeNodeTest {

    public static void main(String[] args) {

        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode20 = new TreeNode(20, treeNode15, treeNode7);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(3, treeNode9, treeNode20);
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        System.out.println(treeNodeTest.maxDepth(treeNode3));
    }

    /**
     * 222.完全二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left + right + 1;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 && right != 0) {
            return right + 1;
        }
        if (left != 0 && right == 0) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftdepth = maxDepth(root.left);
        int rightdepth = maxDepth(root.right);
        return Math.max(leftdepth, rightdepth) + 1;
    }

    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return judge(root.left, root.right);
    }
    public boolean judge(TreeNode left, TreeNode right) {
        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }
        return judge(left.left, right.right) && judge(left.right, right.left);
    }


    /**
     * 589 前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        backFirst(root);
        return resList;
    }
    List<Integer> resList = new ArrayList<>();
    public void backFirst(Node root) {
        if (root == null) {
            return;
        }
        // 中间的放到最前面就是-前序遍历
        resList.add(root.val);
        List<Node> nodeList = root.children;
        for (Node node : nodeList) {
            backFirst(node);
        }
    }
    /**
     * 509 后序遍历
     * @param root
     */
    public void backBe(Node root) {
        if (root == null) {
            return;
        }
        List<Node> nodeList = root.children;
        for (Node node : nodeList) {
            backBe(node);
        }
        // 中间的放到最后面就是-后序遍历
        resList.add(root.val);
    }
    /**
     * 226 .翻转二叉树
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            int len = deque.size();
            while (len > 0) {
                TreeNode treeNode = deque.poll();
                if (treeNode != null) {
                    TreeNode tempLeft = null;
                    TreeNode tempRight = null;
                    if (treeNode.left != null) {
                        tempLeft = treeNode.left;
                        deque.offer(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        tempRight = treeNode.right;
                        deque.offer(treeNode.right);
                    }
                    treeNode.right = tempLeft;
                    treeNode.left = tempRight;
                    len--;
                }
            }
        }
        return root;
    }

    /**
     * 层序遍历，队列
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = deque.size();
            while (len > 0) {
                TreeNode treeNode = deque.poll();
                if (treeNode != null) {
                    list.add(treeNode.val);
                    if (treeNode.left != null) {
                        deque.offer(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        deque.offer(treeNode.right);
                    }
                    len--;
                }
            }
            res.add(list);
        }
        return res;
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int len = deque.size();
            while (len > 0) {
                TreeNode treeNode = deque.poll();
                if (treeNode != null) {
                    // if (treeNode.left != null) {
                    //     deque.offer(treeNode.left);
                    // }
                    if (treeNode.right != null) {
                        deque.offer(treeNode.right);
                    }
                    len--;
                }
            }
            res.add(deque.getLast().val);
        }
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode () {

    }
    TreeNode (int val) {
        this.val = val;
    }
    TreeNode (int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {

    }
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
