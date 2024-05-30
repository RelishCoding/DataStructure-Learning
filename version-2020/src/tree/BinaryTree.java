package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree<Key extends Comparable<Key>, Value> {
    //记录根节点
    private Node root;
    //记录树中元素的个数
    private int N;

    private class Node {
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
    public int size() {
        return N;
    }

    //向树中添加元素key-value
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    //向指定的树x中添加key-value，并返回添元素后的新树
    private Node put(Node x, Key key, Value value) {
        //如果x子树为空
        if (x == null) {
            N++;
            return new Node(key, value, null, null);
        }

        //如果x子树不为空，则比较x结点的键和key的大小
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            //如果key大于x结点的键，则继续找x结点的右子树
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            //如果key小于x结点的键，则继续找x结点的左子树
            x.left = put(x.left, key, value);
        } else {
            //如果key等于x结点的键，则替换x结点的值为value即可
            x.value = value;
        }

        return x;
    }

    //查询树中指定key对应的value
    public Value get(Key key) {
        return get(root, key);
    }

    //从指定的树x中，查找key对应的值
    public Value get(Node x, Key key) {
        //如果x树为空
        if (x == null) {
            return null;
        }

        //如果x树不为空，则比较key和x结点的键的大小
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            //如果key大于x结点的键，则继续找x结点的右子树
            return get(x.right, key);
        } else if (cmp < 0) {
            //如果key小于x结点的键，则继续找x结点的左子树
            return get(x.left, key);
        } else {
            //如果key等于x结点的键，则返回x结点的值即可
            return x.value;
        }
    }

    //删除树中key对应的value
    public void delete(Key key) {
        delete(root, key);
    }

    //删除指定树x中的key对应的value，并返回删除后的新树
    public Node delete(Node x, Key key) {
        //如果x树为null
        if (x == null) {
            return null;
        }

        //如果x树不为null
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            //如果key大于x结点的键，则继续找x结点的右子树
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            //如果key小于x结点的键，则继续找x结点的左子树
            x.left = delete(x.left, key);
        } else {
            //如果key等于x结点的键，则要删除的结点就是x，完成真正的删除结点动作
            N--;

            //如果x右子树为null
            if (x.right == null) {
                return x.left;
            }
            //如果x左子树为null
            if (x.left == null) {
                return x.right;
            }

            //找到右子树中最小的结点
            Node minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            //删除右子树中最小的结点
            Node n = x.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
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
    public Key min() {
        return min(root).key;
    }

    //在指定树x中找出最小键所在的结点
    public Node min(Node x) {
        //如果x还有左子结点，则继续找
        if (x.left != null) {
            return min(x.left);
        } else { //若没有，则x就是最小键所在的结点
            return x;
        }
    }

    //查找整个树中最大的键
    public Key max() {
        return max(root).key;
    }

    //在指定树x中找出最大键所在的结点
    public Node max(Node x) {
        //如果x还有右子结点，则继续找
        if (x.left != null) {
            return max(x.left);
        } else { //若没有，则x就是最大键所在的结点
            return x;
        }
    }

    //使用前序遍历获取整个树中所有的键
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new ArrayDeque<>();
        preErgodic(root, keys);
        return keys;
    }

    //使用前序遍历获取指定树x的所有键，并放到keys队列中
    public void preErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }

        //把x结点的key放入到keys中
        keys.add(x.key);

        //递归遍历x结点的左子树
        if (x.left != null) {
            preErgodic(x.left, keys);
        }

        //递归遍历x结点的右子树
        if (x.right != null) {
            preErgodic(x.right, keys);
        }
    }

    //使用中序遍历获取树中所有的键
    public Queue<Key> midErgodic() {
        Queue<Key> keys = new ArrayDeque<>();
        midErgodic(root, keys);
        return keys;
    }

    //使用中序遍历，获取指定树x中所有的键，并存放到keys队列中
    private void midErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }

        //先递归把左子树中的键方道keys中
        if (x.left != null) {
            midErgodic(x.left, keys);
        }

        //把当前结点x的键方道keys中
        keys.add(x.key);

        //再递归把右子树中的键方道keys中
        if (x.right != null) {
            midErgodic(x.right, keys);
        }
    }

    // 使用后序遍历获取树中所有的键
    public Queue<Key> afterErgodic() {
        Queue<Key> keys = new ArrayDeque<>();
        afterErgodic(root, keys);
        return keys;
    }

    // 使用后序遍历，把指定树x中所有的键放入到keys中
    private void afterErgodic(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }

        // 通过递归把左子树中所有的键放入到keys中
        if (x.left != null) {
            afterErgodic(x.left, keys);
        }
        // 通过递归把右子树中所有的键放入到keys中
        if (x.right != null) {
            afterErgodic(x.right, keys);
        }
        // 把x结点的键放入到keys中
        keys.add(x.key);
    }

    // 使用层序遍历获取整个树中所有的键
    public Queue<Key> layerErgodic() {
        // 定义两个队列，分别存储树中的键和树中的结点
        Queue<Key> keys = new ArrayDeque<>();
        Queue<Node> nodes = new ArrayDeque<>();

        // 默认往队列中放入根节点
        nodes.add(root);

        while (!nodes.isEmpty()) {
            //从队列中弹出一个节点，把它的key放入到keys队列中
            Node node = nodes.poll();
            keys.add(node.key);

            // 判断当前结点有没有左子结点，有则放入到nodes队列中
            if (node.left != null) {
                nodes.add(node.left);
            }

            // 判断当前结点有没有右子结点，有则放入到nodes队列中
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        return keys;
    }

    // 获取整个树的最大深度
    public int maxDepth() {
        return maxDepth(root);
    }

    // 获取指定树x的最大深度
    private int maxDepth(Node x) {
        if (x == null) {
            return 0;
        }

        // 树x的最大深度
        int max;
        // 左子树的最大深度
        int maxLeft = 0;
        // 右子树的最大深度
        int maxRight = 0;

        // 计算x结点左子树的最大深度
        if (x.left != null) {
            maxLeft = maxDepth(x.left);
        }
        // 计算x结点右子树的最大深度
        if (x.right != null) {
            maxRight = maxDepth(x.right);
        }

        // 比较左子树和右子树的最大深度，取最大值 +1 即可
        max = maxLeft > maxRight ? maxLeft + 1 : maxRight + 1;
        return max;
    }
}
