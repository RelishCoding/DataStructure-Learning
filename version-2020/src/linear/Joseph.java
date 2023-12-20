package linear;

public class Joseph {
    //解决约瑟夫问题
    public static void main(String[] args) {
        //构建循环链表，包含41个结点，分别存储1~41之间的值
        //用来记录首结点
        Node<Integer> first = null;
        //用来记录前一个结点
        Node<Integer> pre = null;

        for (int i = 1; i <= 41; i++) {
            //如果是第一个结点
            if (i==1){
                first = new Node<>(i,null);
                pre = first;
                continue;
            }

            //如果不是第一个结点
            Node<Integer> newNode = new Node<>(i, null);
            pre.next=newNode;
            pre=newNode;

            //如果是最后一个结点，需要让最后一个结点的下一个结点变为first
            if (i==41)
                pre.next=first;
        }

        //用count计数器模拟报数
        int count = 0;

        //遍历循环链表
        //记录每次遍历拿到的结点，默认从首结点开始
        Node<Integer> cur = first;
        //记录当前结点的上一个结点
        Node<Integer> before = null;
        while (cur!=cur.next){
            //模拟报数
            count++;

            //判断当前报数是不是为3
            if (count==3){
                //如果是3，把当前结点删除，打印当前节点，重置count=0，让当前结点n后移
                before.next=cur.next;
                System.out.print(cur.item+", ");
                count=0;
                cur=cur.next;
            }else {
                //如果不是3，让before变为当前结点，让当前节点后移
                before=cur;
                cur=cur.next;
            }
        }

        //打印最后一个元素
        System.out.println("\n剩余的元素为：" + cur.item);
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
