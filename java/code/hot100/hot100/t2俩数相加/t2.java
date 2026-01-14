package t2俩数相加;

import java.util.ArrayList;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
//请你将两个数相加，并以相同形式返回一个表示和的链表。
//
//你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
public class t2 {
    public static void main(String[] args) {

    }
    //暴力遍历
    //此方法会有溢出风险!!!所以不能用这种办法
    //sum2=a2.get(i)+sum2*10;如果链表表示的数非常大,就会有问题!
    //除非使用大数
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head =null;
        ListNode temp =null;
        if(l1.val==0&&l2.val==0)return new ListNode(0);
        ArrayList<Integer>a1=new ArrayList<>();
        ArrayList<Integer>a2=new ArrayList<>();
        int sum1=0;
        int sum2=0;
        while (l1!=null){
            a1.add(l1.val);
            l1=l1.next;
        }
        while (l2!=null){
            a2.add(l2.val);
            l2=l2.next;
        }
        for (int i = a1.size()-1; i >=0; i--) {
            sum1=a1.get(i)+sum1*10;
        }

        for (int i = a2.size()-1; i >=0; i--) {
            sum2=a2.get(i)+sum2*10;
        }

        int sum =sum1+sum2;
        while(sum!=0){
            if(head==null){
                head=temp=new ListNode(sum%10);
                sum/=10;
            }
            else {
                temp.next = new ListNode(sum%10);
                sum/=10;
                temp=temp.next;
            }
        }
        return head;
    }
//    由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
//
//    我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2，
//    进位值为 carry，则它们的和为 n1+n2+carry；其中，答案链表处相应位置的数字为 (n1+n2+carry)mod10
//    ，而新的进位值为 (n1+n2+carry)/10
//    如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 0 。
//    此外，如果链表遍历结束后，有 carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry。
    //同时要主要保留头节点!用临时节点去添加,头节点记录开始位置
    public static ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        ListNode head=null,tail =null;
        int carry =0;
        while(l1!=null||l2!=null){
            int a1 = l1!=null?l1.val:0;
            int a2 = l2!=null?l2.val:0;
            int sum =a1+a2+carry;
            if(head==null){
                head=tail=new ListNode(sum%10);
            }else {
                tail.next=new ListNode(sum%10);
                tail=tail.next;
            }
            carry=sum/10;
            l1=l1!=null?l1.next:l1;
            l2=l2!=null?l2.next:l2;
        }
        if(carry>0){
            tail.next=new ListNode(carry);
            tail=tail.next;
        }
        return head;

    }
//精简版本
    ListNode addTwoNumbers03(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while(l1!=null || l2!=null || carry!=0) {
            int a = l1!=null ? l1.val : 0;
            int b = l2!=null ? l2.val : 0;
            int sum = a + b + carry;
            carry = sum >= 10 ? 1 : 0;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
        }
        return dummy.next;
    }
}
