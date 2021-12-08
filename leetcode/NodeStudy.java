package leetcode;


public class NodeStudy {

    public static void main(String[] args) {

    }

    /**
     * 203：给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode nodeStar = new ListNode(0, head);
        ListNode pre = nodeStar;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return nodeStar.next;
    }


}

/**
 * 707.
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 */
class MyLinkedList {
    /**
     * size存储链表元素的个数
     */
    int size;
    /**
     * 虚拟头结点
     */
    ListNode head;
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        ListNode pre = head;
        for (int i = 0; i <= index; i++) {
            pre = head.next;
            if (pre == null) {
                return -1;
            }
        }
        return pre.val;
    }

    public void addAtHead(int val) {
        ListNode listNode = new ListNode(val);
        listNode.next = head;
    }

    public void addAtTail(int val) {
        ListNode pre = head;
        while (pre != null) {
            pre = pre.next;
        }
        ListNode listNode = new ListNode(val);
        pre.next = listNode;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0) {
            addAtHead(val);
            return;
        }
        if (get(index) == -1) {
            addAtTail(val);
            return;
        }
        ListNode nodePre = getNode(index - 1);
        ListNode nodeCur = nodePre.next;
        ListNode node = new ListNode(val);
        nodePre.next = node;
        node.next = nodeCur;
    }

    public void deleteAtIndex(int index) {
        if (get(index) == -1) {
            return;
        }
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;

        ListNode nodePre = getNode(index - 1);
        ListNode node = nodePre.next.next;
        nodePre.next = node;
    }

    public ListNode getNode(int index) {
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = head.next;
            if (pre == null) {
                return null;
            }
        }
        return pre;
    }
}


class ListNode{
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
