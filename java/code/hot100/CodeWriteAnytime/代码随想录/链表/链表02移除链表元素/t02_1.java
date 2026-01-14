package 链表.链表02移除链表元素;

public class t02_1 {
    public ListNode removeElements(ListNode head, int val) {
        if(head==null)return head;
        //首先要考虑目标值在头节点的情况
        while(head!=null&&head.val==val){
            head=head.next;
        }
        ListNode pre = head;
        while(pre!=null&&pre.next!=null){
            if(pre.next.val == val){
                pre.next = pre.next.next;
            }//只有当下一个节点的值不是val的时候才移动指针
            //防止当前节点又出现val的情况
            else{
                pre = pre.next;
            }
        }
        return head;
    }
}
