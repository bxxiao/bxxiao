package org.util;

import java.util.*;

//余弦相似性相关计算的工具类
public class CosCalculator {

    /**
     * 计算两个向量各自的平方和，并计算两个平方和的乘积
     * @param vectors 存储两个词频向量的集合，每个元素的int[0]表示第一个向量的一个坐标值；int[1]表
     *                示第二个向量同位置的坐标值
     * @return 返回两个向量各自的平方和的乘积
     */
    private static double squares(Collection<int[]> vectors) {
        double result1 = 0;
        double result2 = 0;

        for (int[] temp : vectors) {
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    /**
     * 求两个坐标的点乘
     * @param vectors 存储两个词频向量的集合，每个元素的int[0]表示第一个向量的一个坐标值；int[1]表
     *                示第二个向量同位置的坐标值
     * @return 返回两个词频向量的点乘结果
     */
    private static double pointMulti(Collection<int[]> vectors) {
        double result = 0;
        //分别将两个词频向量中对应的两个坐标值相乘，并相加
        for (int[] temp : vectors) {

            result += (temp[0] * temp[1]);
        }

        return result;
    }

    /**
     * 求余弦相似度，根据传入的向量，先计算两个向量的点乘，再计算平方和乘积的开方，最后点乘除以开方
     * @param vectors 词频向量
     * @return 以字符串形式返回保留两位数的结果
     */
    public static String  getCos(Collection<int[]> vectors) {
        double result = 0;
        double sqrt = Math.sqrt(squares(vectors));

        //若分母为0，表示相似度为0，返回0的字符串
        if (sqrt==0){
            return "0";
        }

        result = pointMulti(vectors) / sqrt;

        //添加额外的0，防止在下面保留两位数操作中数组索引越界（resultStr.indexOf(".")+3）
        String resultStr = String.valueOf(result*100) + "0000";
        //保留两位小数：截取小数点后第3位之前的子字符串
        resultStr = resultStr.substring(0 ,resultStr.indexOf(".")+3);

        return resultStr;
    }


}
