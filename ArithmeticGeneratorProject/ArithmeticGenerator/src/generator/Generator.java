package generator;

import expression.Expression;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    /**
     * 根据表达式数量和最大值随机生成题目和答案
     * 并把题目写入expFile.txt，把答案写入answer.txt
     * @param expCounts
     * @param max
     */
    public static void generate(int expCounts, int max){
        List<String> expressions = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        Random random = new Random();
        Expression expression;

        for(int i=0;i<expCounts;i++){
            //随机获取一个1-3的数字，用于指定运算符个数
            int opCounts = random.nextInt(4);
            while (opCounts==0){
                opCounts = random.nextInt(4);
            }

            //生成表达式
            expression = new Expression(opCounts, max);
            expression.createBTree();
            //计算运算结果
            String answer = expression.getResult();

            //存入对应集合
            expressions.add(expression.toString() + " = ");
            answers.add(answer);
        }

        FileUtil.write(expressions, answers);
    }
}
