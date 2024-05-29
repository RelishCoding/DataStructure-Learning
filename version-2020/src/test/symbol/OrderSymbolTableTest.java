package test.symbol;

import symbol.OrderSymbolTable;

public class OrderSymbolTableTest {
    public static void main(String[] args) {
        OrderSymbolTable<Integer, String> orderSymbolTable = new OrderSymbolTable<>();

        orderSymbolTable.put(1, "张三");
        orderSymbolTable.put(2, "李四");
        orderSymbolTable.put(4, "赵六");
        orderSymbolTable.put(7, "田七");

        orderSymbolTable.put(3, "王五");
    }
}
