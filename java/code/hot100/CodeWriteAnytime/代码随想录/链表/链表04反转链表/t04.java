package 链表.链表04反转链表;

public class t04 {

    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode result =null;
        ListNode pre = head;
        while(pre!=null){
            ListNode right = pre.next;//用于暂存下一个节点
            pre.next = result;
            result = pre;
            pre = right;
        }
        return result;
    }
}
