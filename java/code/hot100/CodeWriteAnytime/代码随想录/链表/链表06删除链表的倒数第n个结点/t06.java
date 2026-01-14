package 链表.链表06删除链表的倒数第n个结点;

public class t06 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n==0||head==null)return head;
        ListNode result = new ListNode(-1,head);
        //此处要考虑到可能就是删除头节点
        ListNode slow =result;
        ListNode fast = result;
        for(int i = 0;i<=n;i++){
            fast=fast.next;
        }
        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        if(slow.next!=null){
            slow.next = slow.next.next;
        }
        return result.next;
    }
}
