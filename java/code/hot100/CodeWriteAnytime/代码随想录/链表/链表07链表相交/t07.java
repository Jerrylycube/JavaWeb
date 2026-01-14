package 链表.链表07链表相交;

public class t07 {

//合并链表
    public ListNode getIntersectionNode02(ListNode headA, ListNode headB) {
        //合并链表,一起走
        //当链表有相交的时候,当他们走相同步数的时候必然相交到交汇点
        //当无相交时,必然都走向对方的null区域
        ListNode pA = headA;
        ListNode pB  = headB;
        while(pA!=pB){
            pA = pA==null?headB:pA.next;
            pB = pB==null?headA:pB.next;
        }
        return pA;
    }


//先对齐尾部,再从一起从短链表的头部位置一起遍历
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null)return null;
        ListNode preA = headA;
        ListNode preB = headB;
        int sizeA = 0;
        int sizeB = 0;
        //找出长度大小
        while(preA!=null){
            sizeA++;
            preA = preA.next;
        }
        while(preB!=null){
            sizeB++;
            preB = preB.next;
        }
        preA = headA;
        preB = headB;
        //对齐
        if(sizeA>sizeB){
            for(int i = 0;i<sizeA-sizeB;i++){
                preA = preA.next;
            }
        }else{
            for(int i = 0;i<sizeB-sizeA;i++){
                preB = preB.next;
            }
        }
        //依次往后找,是否有相交
        while(preA!=null&&preB!=null){
            if(preA==preB)return preA;
            preA = preA.next;
            preB = preB.next;
        }
        return null;
    }
}
