package symbol;

public class OrderSymbolTable<Key extends Comparable<Key>, Value> {
    //记录首结点
    private Node head;
    //记录符号表中元素的个数
    private int N;

    private class Node {
        //键
        private Key key;
        //值
        private Value value;
        //下一个结点
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public OrderSymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    //获取符号表中键值对的个数
    public int size() {
        return N;
    }

    //往符号表中插入键值对
    public void put(Key key, Value value) {
        //定义两个Node变量，分别记录当前结点和其上一个结点
        Node cur = head.next;
        Node pre = head;
        while (cur != null && key.compareTo(cur.key) > 0) {
            pre = cur;
            cur = cur.next;
        }

        //如果当前结点cur的键和要插入的key一样，则替换
        if (cur != null && key.compareTo(cur.key) == 0) {
            cur.value = value;
            return;
        }

        //如果不一样，则把新结点插入到cur之前
        Node newNode = new Node(key, value, cur);
        pre.next = newNode;
        N++;
    }

    //删除符号表中键为key的键值对
    public void delete(Key key) {
        //找到键为key的结点，把该结点从链表中删除
        Node n = head;
        while (n.next != null) {
            //判断n结点的下一个结点的键是否为key，若是则删除该结点
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                N--;
                return;
            }

            n = n.next;
        }
    }

    //从符号表中获取key对应的值
    public Value get(Key key) {
        //找到键为key的结点
        Node n = head;
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }
}
