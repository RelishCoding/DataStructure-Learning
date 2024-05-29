package symbol;

public class SymbolTable<Key, Value> {
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

    public SymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    //获取符号表中键值对的个数
    public int size() {
        return N;
    }

    //往符号表中插入键值对
    public void put(Key key, Value value) {
        //若符号表中已经存在键为key的键值对，只需找到该结点，替换值为value即可
        Node n = head;
        while (n.next != null) {
            n = n.next;
            //判断n结点存储的键是否为key，若是则替换value的值
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        //若不存在，需要创建新的结点，保存要插入的键值对，再把新结点插入到链表的头部
        Node newNode = new Node(key, value, head.next);
        head.next = newNode;
        //元素个数+1
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
