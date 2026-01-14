package 链表.链表05俩俩交换链表中的节点;

public class t05 {
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null)return head;
        //虚拟头节点
        ListNode headFare = new ListNode(-1,head);
        ListNode cur = headFare;//操作节点,不动虚拟头节点,虚拟头节点后面要返回的
        ListNode firstNode;//头节点的下一个
        ListNode secondNode;//头节点的下俩个
        ListNode temp;//头节点的下俩个的下一个
        while(cur.next!=null&&cur.next.next!=null){
            temp = cur.next.next.next;
            firstNode=cur.next;
            secondNode=cur.next.next;
            //交换开始
            cur.next=secondNode;
            secondNode.next=firstNode;
            firstNode.next=temp;
            //移动操作指针进行下一次交换
            cur=firstNode;
        }
        return headFare.next;

    }
}
