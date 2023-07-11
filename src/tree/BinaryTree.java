package tree;

public class BinaryTree<Key extends Comparable<Key>,Value> {
    //记录根节点
    private Node root;
    //记录树中元素的个数
    private int N;

    private class Node{
        //存储键
        public Key key;
        //存储值
        private Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    //获取树中元素的个数
    public int size(){
        return N;
    }

    //向树中添加元素key-value
    public void put(Key key,Value value){
        root = put(root,key,value);
    }

    //向指定的树x中添加key-value，并返回添元素后的新树
    private Node put(Node x,Key key,Value value){
        //如果x子树为空
        if (x==null){
            N++;
            return new Node(key,value,null,null);
        }

        //如果x子树不为空，则比较x结点的键和key的大小
        int cmp = key.compareTo(x.key);
        if (cmp>0){
            //如果key大于x结点的键，则继续找x结点的右子树
            x.right = put(x.right,key,value);
        }else if (cmp<0){
            //如果key小于x结点的键，则继续找x结点的左子树
            x.left = put(x.left,key,value);
        }else {
            //如果key等于x结点的键，则替换x结点的值为value即可
            x.value = value;
        }

        return x;
    }

    //查询树中指定key对应的value
    public Value get(Key key){
        return get(root,key);
    }

    //从指定的树x中，查找key对应的值
    public Value get(Node x,Key key){
        //如果x树为空
        if (x==null){
            return null;
        }

        //如果x树不为空，则毕节key和x结点的键的大小
        int cmp = key.compareTo(x.key);
        if (cmp>0){
            //如果key大于x结点的键，则继续找x结点的右子树
            return get(x.right,key);
        }else if (cmp<0){
            //如果key小于x结点的键，则继续找x结点的左子树
            return get(x.left,key);
        }else {
            //如果key等于x结点的键，则返回x结点的值即可
            return x.value;
        }
    }

    //删除树中key对应的value
    public void delete(Key key){
        delete(root,key);
    }

    //删除指定树x中的key对应的value，并返回删除后的新树
    public Node delete(Node x,Key key){
        //如果x树为null
        if (x==null){
            return null;
        }

        //如果x树不为null
        int cmp = key.compareTo(x.key);
        if (cmp>0){
            //如果key大于x结点的键，则继续找x结点的右子树
            x.right = delete(x.right,key);
        }else if (cmp<0){
            //如果key小于x结点的键，则继续找x结点的左子树
            x.left = delete(x.left,key);
        }else {
            //如果key等于x结点的键，则要删除的结点就是x，完成真正的删除结点动作
            N--;

            //如果x右子树为null
            if (x.right==null){
                return x.left;
            }
            //如果x左子树为null
            if (x.left==null){
                return x.right;
            }

            //找到右子树中最小的结点
            Node minNode = x.right;
            while (minNode.left!=null){
                minNode = minNode.left;
            }

            //删除右子树中最小的结点
            Node n = x.right;
            while (n.left!=null){
                if (n.left.left==null){
                    n.left = null;
                }else {
                    n = n.left;
                }
            }

            //让x结点的左右子树分别成为minNode的左右子树
            minNode.left = x.left;
            minNode.right = x.right;
            //让x结点的父结点指向minNode
            x = minNode;
        }

        return x;
    }

    //查找整个树中最小的键
    public Key min(){
        return min(root).key;
    }

    //在指定树x中找出最小键所在的结点
    public Node min(Node x){
        //如果x还有左子结点，则继续找
        if (x.left!=null){
            return min(x.left);
        }else {//若没有，则x就是最小键所在的结点
            return x;
        }
    }

    //查找整个树中最大的键
    public Key max(){
        return max(root).key;
    }

    //在指定树x中找出最大键所在的结点
    public Node max(Node x){
        //如果x还有右子结点，则继续找
        if (x.left!=null){
            return max(x.left);
        }else {//若没有，则x就是最大键所在的结点
            return x;
        }
    }
}
