package test.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class PaperFoldingTest {
    public static void main(String[] args) {
        // 模拟折纸过程，产生树
        Node<String> tree = createTree(3);
        // 遍历树，打印每个结点
        printTree(tree);
    }

    // 模拟对折N次纸产生树
    private static Node<String> createTree(int N) {
        // 定义根节点
        Node<String> root = null;
        for (int i = 0; i < N; i++) {
            // 1.当前是第一次对折
            if (i == 0) {
                root = new Node<>("down", null, null);
                continue;
            }
            // 2.当前不是第一次对折
            // 定义一个辅助队列，通过层序遍历找到叶子结点，给叶子结点添加子结点
            Queue<Node<String>> queue = new ArrayDeque<>();
            queue.add(root);

            // 循环遍历队列
            while (!queue.isEmpty()) {
                // 从队列中弹出一个结点
                Node<String> node = queue.poll();
                // 如果有左子结点，则把左子结点添加到队列中
                if (node.left != null) {
                    queue.add(node.left);
                }
                // 如果有右子结点，则把右子结点添加到队列中
                if (node.right != null) {
                    queue.add(node.right);
                }
                // 如果既没有左子结点也没有右子结点，证明是叶子结点，给它添加左子结点和右子结点即可
                if (node.left == null && node.right == null ) {
                    node.left = new Node<>("down", null, null);
                    node.right = new Node<>("up", null, null);
                }
            }
        }
        return root;
    }

    // 打印树中每个结点
    public static void printTree(Node<String> root) {
        // 需要使用中序遍历完成
        if (root == null) {
            return;
        }
        // 打印左子树的每个结点
        if (root.left != null) {
            printTree(root.left);
        }
        // 打印当前结点
        System.out.println(root.item);
        // 打印右子树的最小结点
        if (root.right != null) {
            printTree(root.right);
        }
    }

    // 结点类
    private static class Node<T> {
        public T item; //存储元素
        public Node<T> left;
        public Node<T> right;

        public Node(T item, Node<T> left, Node<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
}
