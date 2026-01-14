package 链表.链表02移除链表元素;

public class t02 {

//    给你一个链表的头节点 head 和一个整数 val
//    ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。

    //思路就是要先排除头节点是val的情况,再依次查找
    public ListNode removeElements(ListNode head, int val) {
        while(head!=null&&head.val==val){
            head=head.next;
        }
        ListNode temp = head;
        while(temp!=null&&temp.next!=null){
            if(temp.next.val==val){
                temp.next = temp.next.next;
            }
            else{
                temp = temp.next;
            }
        }
        return head;
    }
}
