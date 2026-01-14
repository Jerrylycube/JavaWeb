package 链表.链表08环形链表;

import java.util.HashSet;

public class t08 {

    //快慢指针
    //有环的时候,快指针一定会在环内循环,慢指针则一定会与快指针在环内相遇
    //因为一旦慢指针进去了环内,那么不管怎么样,快指针一定会追上慢指针

    //判断出有环之后,再判断环的入口
    //通过公式推导得知,
    // 让一个点从head,和让一个点从刚刚相遇的点同时出发
    //相遇的点就是环的入口!
    public ListNode detectCycle02(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }


    //使用Set
    public ListNode detectCycle(ListNode head) {
        // 使用HashSet来存储访问过的节点
        HashSet<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            // 如果节点已经存在于集合中，说明有环
            if (visited.contains(current)) {
                return current;
            }
            // 将当前节点加入集合
            visited.add(current);
            current = current.next;
        }
        return null;
    }
}
