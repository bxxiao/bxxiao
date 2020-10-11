package expression;

public class DistinctCheck {

    /**
     * 查重
     * 两个式子等价时，返回false，否则true
     * @param exp1
     * @param exp2
     * @return
     */
    public static boolean check(Expression exp1, Expression exp2){
        if(exp1.getOpCounts()!=exp2.getOpCounts()){
            return true;
        }

        //只有一个运算符
        if(exp1.getOpCounts()==1){
            String op1 = exp1.getRoot().getStr();
            String op2 = exp2.getRoot().getStr();
            //两个运算符相同且是加号或-号
            if(op1.equals(op2) && ((op1.equals("+")) || (op1.equals("x"))) ){
                //获取表达式1的两个数
                String[] nums1 = exp1.toString().replace(" ", "").split(op1);
                String exp2Str = exp2.toString();

                //若表达式2中包含表达式1的两个数，则两个式子等价，不符合要求，返回false
                return exp2Str.contains(nums1[0]) && exp2Str.contains(nums1[1]);
            }
            else{
                return true;
            }
        }

        return true;
    }

    /**
     * 先比较运算符个数
     *  若运算符只有一个
     *      比较是否相同，若相同，是否是 + 或 x
     *          若是，获取两个数，判断是否相同
     *  若运算符有两个，先判断是否含 + 或 x
     *      若有，判断另一个运算符是否相同
     *      若相同，
     */
}
