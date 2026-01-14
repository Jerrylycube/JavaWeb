package 链表.链表04反转链表;

public class t04_1 {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode tempNode =null;
        ListNode firstNode;
        while(head!=null){
            firstNode = head;
            head = head.next;//要注意此处的改变顺序,因为要改变next了,所以要赶紧记录原来的
            firstNode.next = tempNode;
            tempNode = firstNode;

        }
        return tempNode;
    }
}
