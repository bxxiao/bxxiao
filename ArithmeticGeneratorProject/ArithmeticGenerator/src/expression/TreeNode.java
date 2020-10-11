package expression;

import util.Calculator;
import util.Ran;

public class TreeNode {
    private String str;
    private TreeNode rchild = null;
    private TreeNode lchild = null;

    public TreeNode(String str, TreeNode lchild, TreeNode rchild){
        this.str = str;
        this.rchild = rchild;
        this.lchild = lchild;
    }

    public String getStr() {
        return str;
    }

    public void setChild(TreeNode lchild, TreeNode rchild){
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public TreeNode getRchild() {
        return rchild;
    }

    public void setRchild(TreeNode rchild) {
        this.rchild = rchild;
    }

    public TreeNode getLchild() {
        return lchild;
    }
    public void setLchild(TreeNode lchild) {
        this.lchild = lchild;
    }

    private void swapChild(){
        TreeNode temp = getLchild();
        setLchild(getRchild());
        setRchild(temp);
    }

    /**
     * 获取每个节点的运算结果，并检验：
     * - 减法中被减数是否小于减数，若是，交换两者
     * - 除法中除数是否为0，若是，替换当前结点的运算符为其他运算符
     * @return result
     */
    public String getResult(){
        //若有子节点，说明当前结点是运算符
        if(hasChild()){
            switch(str){
                case "+":
                    return Calculator.add(getLchild().getResult(), getRchild().getResult());
                case "-":
                    String subResult = Calculator.sub(getLchild().getResult(), getRchild().getResult());
                    //若返回值为-1，交换左右孩子
                    if(subResult.contains("-")){
                        swapChild();
                        return Calculator.sub(getLchild().getResult(), getRchild().getResult());
                    }
                    else {
                        return subResult;
                    }
                case "x":
                    return Calculator.mul(getLchild().getResult(), getRchild().getResult());
                case "÷":
                    String divResult = Calculator.division(getLchild().getResult(), getRchild().getResult());
                    //返回结果为-1，表示除数为0，替换运算符
                    if(divResult.contains("-")){
                        while(str.equals("÷")){
                            //将当前运算符转换为其他运算符
                            str = Ran.getOperator();
                        }
                        return this.getResult();
                    }
                    else {
                        return divResult;
                    }
            }
        }
        //无子节点，说明当前结点是叶子结点，直接返回结点值
        return str;
    }

    /**
     * 先对每个运算式添加括号，然后根据去括号法则，去掉多余的子式的括号
     * @return string
     */
    public String toString(){
        String leftExp = "";//左表达式
        String rightExp = "";//右表达式
        String result = "";//运算符

        if(hasChild()){
            //右子树如果有孩子，说明右子树是一个表达式，而不是数字节点。
            if(getRchild().hasChild()){
                //判断左邻括号的运算符是否为'/'
                if(str.equals("÷")){
                    //获取右子树的表达式，保留括号
                    rightExp = getRchild().toString();
                }
                //判断左邻括号的运算符是否为'x'或'-'
                else if(str.equals("x") || str.equals("-")){
                    //判断op是否为'+'或'-'
                    if(getRchild().str.equals("+") || getRchild().str.equals("-")){
                        rightExp = getRchild().toString();
                    }
                    else{
                        //获取右子树的表达式，并且去括号
                        rightExp = getRchild().toString().substring(1, getRchild().toString().length()-1);
                    }
                }
                else{
                    //右子树除此之外都是可以去括号的。
                    rightExp = getRchild().toString().substring(1, getRchild().toString().length()-1);
                }
            }
            else{
                rightExp = getRchild().str;
            }

            //左子树的情况同右子树类似
            if(getLchild().hasChild()){
                if(str.equals("x") || str.equals("÷")){
                    if(getLchild().str.equals("+") || getLchild().str.equals("-")){
                        leftExp = getLchild().toString();
                    }
                    else{
                        leftExp = getLchild().toString().substring(1, getLchild().toString().length()-1);
                    }
                }
                else{
                    leftExp = getLchild().toString().substring(1, getLchild().toString().length()-1);
                }
            }
            else{
                leftExp = getLchild().str;
            }
            //获取当前的运算式，并加上括号
            result = "(" + leftExp + " " + str + " " + rightExp + ")";
        }
        else{
            //若没有孩子，说明是数字节点，直接返回数字
            result = str;
        }
        return result;
    }

    public boolean hasChild(){
        return !(lchild == null && rchild == null);
    }
}
