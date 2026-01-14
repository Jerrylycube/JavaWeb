package t2俩数相加;

public class t2_1 {
    public static void main(String[] args) {
        //给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
//请你将两个数相加，并以相同形式返回一个表示和的链表。
//
//你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.

    }
    public static ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        ListNode head =null,temp=null;
        int carry=0;
        while(l1!=null||l2!=null)
        {
            int a1 = l1==null?0:l1.val;
            int a2 = l2==null?0:l2.val;
            int sum =a1+a2+carry;
            if(head==null){
                temp=head=new ListNode(sum%10);
            }
            else {
                temp.next=new ListNode(sum%10);
                temp = temp.next;
            }
            carry=sum/10;
            //此处写错了,要注意下一个是不是空,因为是不等长的
            l1=l1==null?l1:l1.next;
            l2=l2==null?l2:l2.next;
        }
        if(carry!=0){
            temp.next=new ListNode(carry);
            temp=temp.next;
        }


        return  head;
    }
}
