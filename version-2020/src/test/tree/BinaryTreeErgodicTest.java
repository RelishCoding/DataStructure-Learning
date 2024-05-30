package test.tree;

import tree.BinaryTree;

import java.util.Queue;

public class BinaryTreeErgodicTest {
    public static void main(String[] args) {
        //创建树对象
        BinaryTree<String, String> tree = new BinaryTree<>();

        //往树中添加数据
        tree.put("E", "5");
        tree.put("B", "2");
        tree.put("G", "7");
        tree.put("A", "1");
        tree.put("D", "4");
        tree.put("F", "6");
        tree.put("H", "8");
        tree.put("C", "3");
        
        // 测试前序遍历
        Queue<String> keys_pre = tree.preErgodic();
        System.out.println("前序遍历的结果：");
        for (String key : keys_pre) {
            String value = tree.get(key);
            System.out.println(key + "-----" + value);
        }

        // 测试中序遍历
        Queue<String> keys_mid = tree.midErgodic();
        System.out.println("中序遍历的结果：");
        for (String key : keys_mid) {
            String value = tree.get(key);
            System.out.println(key + "-----" + value);
        }

        // 测试后序遍历
        Queue<String> keys_after = tree.afterErgodic();
        System.out.println("后序遍历的结果：");
        for (String key : keys_after) {
            String value = tree.get(key);
            System.out.println(key + "-----" + value);
        }

        // 测试层序遍历
        Queue<String> keys_layer = tree.layerErgodic();
        System.out.println("层序遍历的结果：");
        for (String key : keys_layer) {
            String value = tree.get(key);
            System.out.println(key + "-----" + value);
        }
    }
}