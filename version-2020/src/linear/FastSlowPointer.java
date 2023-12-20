package linear;

public class FastSlowPointer {
    public static void main(String[] args) throws Exception {
        Node<String> first = new Node<>("aa", null);
        Node<String> second = new Node<>("bb", null);
        Node<String> third = new Node<>("cc", null);
        Node<String> fourth = new Node<>("dd", null);
        Node<String> fifth = new Node<>("ee", null);
        Node<String> six = new Node<>("ff", null);
        //Node<String> seven = new Node<>("gg", null);

        //完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        //six.next = seven;

        //查找中间值
        String mid = getMid(first);
        System.out.println("中间值为："+mid);
    }

    /**
     * @param first 链表的首结点
     * @return 链表的中间结点的值
     */
    public static String getMid(Node<String> first) {
        //定义两个指针
        Node<String> fast=first;
        Node<String> slow=first;
        //使用两个指针遍历链表，当快指针指向的结点没有下一个结点了就可以结束
        //结束后满指针指向的结点就是中间值
        //偶数个结点最后fast==null，奇数个结点最后fast.next==null
        while (fast!=null && fast.next!=null){
            //变化fast和slow的值
            fast=fast.next.next;
            slow=slow.next;
        }

        return slow.item;
    }

    //结点类
    private static class Node<T> {
        //存储数据
        T item;
        //下一个结点
        Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
