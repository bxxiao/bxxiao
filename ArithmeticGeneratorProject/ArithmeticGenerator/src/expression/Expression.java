package expression;

import util.Ran;

import java.util.ArrayList;

/**
 * 表达式的类，封装了一个二叉树
 */
public class Expression {
    private TreeNode root;//根节点
    private int opCounts;//运算符个数
    private int max;//所允许运算数的最大值
    private ArrayList<TreeNode> opeList = new ArrayList<>();//按顺序存放运算符的集合

    public TreeNode getRoot() {
        return root;
    }

    public Expression(int opCounts, int max){
        this.opCounts = opCounts;
        this.max = max;
    }

    public int getOpCounts() {
        return opCounts;
    }

    /**
     * 获取最终的表达式
     * @return str
     */
    public String toString(){
        String str = root.toString();
        str = str.substring(1, str.length()-1);
        return str;
    }

    /**
     * 计算并验证表达式
     * @return result
     */
    public String CalAndVal(){
        return root.getResult();
    }

    /**
     * 以字符串形式返回表达式的计算结果
     * @return
     */
    public String getResult(){
        return root.getResult();
    }

    /**
     * 计算（完全）二叉树的深度(层数)
     *
     * @return deep
     */
    public int getDeep(){
        int i = this.opCounts;
        int deep = 2;
        while(i/2 > 0){
            deep++;
            i /= 2;
        }
        return deep;
    }

    /**
     * 生成二叉树
     */
    public void createExpression(){
        TreeNode lchild, rchild, lnode, rnode;

        //只有一个运算符
        if(opCounts == 1){
            lchild = new TreeNode(Ran.getNumber(max), null, null);
            rchild = new TreeNode(Ran.getNumber(max), null, null);
            root = new TreeNode(Ran.getOperator(), lchild, rchild);
        }
        else{
            int num1 = 0;
            // int n = getDeep() - 3;
            boolean[] place = Ran.getChildPlace(opCounts);
            root = new TreeNode(Ran.getOperator(), null, null);
            opeList.add(root);

            //以下是针对运算符大于3个的情况
            // for(int i = 0; i < n; i++){
            //     for(int j = 0; j < (int)Math.pow(2, i); j++, num1++){
            //         lchild = new TreeNode(Ran.getOperator(), null, null);
            //         rchild = new TreeNode(Ran.getOperator(), null, null);
            //         opeList.get(j + num1).setChild(lchild, rchild);
            //         opeList.add(lchild);
            //         opeList.add(rchild);
            //     }
            // }

            for(int i = 0; i < place.length; i++){
                //place[i]为true，生成一个运算符，并为该运算符生成两个左右孩子运算数
                if(place[i]){
                    lnode  = new TreeNode(Ran.getNumber(max), null, null);
                    rnode  = new TreeNode(Ran.getNumber(max), null, null);
                    //i为偶数（从0开始的），将新创建的运算符作为当前结点（运算符）的左孩子；为奇，则作为有孩子
                    if(i%2 == 0){
                        lchild = new TreeNode(Ran.getOperator(), lnode, rnode);
                        opeList.add(lchild);
                        opeList.get(num1).setLchild(lchild);
                    }
                    else{
                        rchild = new TreeNode(Ran.getOperator(), lnode, rnode);
                        opeList.add(rchild);
                        opeList.get(num1).setRchild(rchild);
                    }
                }
                //place[i]为false，则只生成一个运算数，组装到当前运算符的左右孩子
                else{
                    if(i%2 == 0){
                        lchild = new TreeNode(Ran.getNumber(max), null, null);
                        opeList.get(num1).setLchild(lchild);
                    }
                    else{

                        rchild = new TreeNode(Ran.getNumber(max), null, null);
                        opeList.get(num1).setRchild(rchild);
                    }
                }
                //num1为偶数，说明当前结点还未生成有孩子，num1不变；若是奇数，则+1
                num1 = num1 + i%2;
            }
        }
        CalAndVal();//生成完二叉树进行校验
    }
}

