package linear;

public class ReversePolishNotation {
    public static void main(String[] args) {
        //中缀表达式3*（17-15）+18/6的逆波兰表达式如下
        String[] notation = {"3", "17", "15", "-", "*","18", "6","/","+"};
        int result = calculate(notation);
        System.out.println("逆波兰表达式的结果为："+result);
    }

    /**
     * @param notation 逆波兰表达式的数组表示方式
     * @return 逆波兰表达式的计算结果
     */
    public static int calculate(String[] notation){
        //1.定义一个栈，用来存储操作数
        Stack<Integer> operands = new Stack<>();
        //2.从左往右遍历每一个元素，得到每一个元素
        for (int i = 0; i < notation.length; i++) {
            String curr = notation[i];
            //3.判断当前元素是运算符还是操作数
            Integer o1;
            Integer o2;
            Integer result;
            switch (curr){
                //4.若是运算符，从栈中弹出两个操作数完成运算，运算完的结果再压入栈中
                case "+":
                    o1 = operands.pop();
                    o2 = operands.pop();
                    result = o2 + o1;
                    operands.push(result);
                    break;
                case "-":
                    o1 = operands.pop();
                    o2 = operands.pop();
                    result = o2 - o1;
                    operands.push(result);
                    break;
                case "*":
                    o1 = operands.pop();
                    o2 = operands.pop();
                    result = o2 * o1;
                    operands.push(result);
                    break;
                case "/":
                    o1 = operands.pop();
                    o2 = operands.pop();
                    result = o2 / o1;
                    operands.push(result);
                    break;
                //5.若是操作数，把该操作数压入到栈中
                default:
                    operands.push(Integer.parseInt(curr));
                    break;
            }
        }

        //6.得到栈中最后一个元素，就是逆波兰表达式的结果
        int result = operands.pop();
        return result;
    }
}
