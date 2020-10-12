package util;

import java.util.Random;

/**
 * 随机值生成类
 * 包括运算符、整数、分数以及子节点位置
 */
public class Ran {

    /**
     * 获取随机的符号
     *
     * @return operator
     */

    public static String getOperator(){
        String operator = "";
        Random ran = new Random();
        int i = ran.nextInt(4);
        switch(i){
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "-";
                break;
            case 2:
                operator = "x";
                break;
            case 3:
                operator = "÷";
                break;
        }
        return operator;
    }


    /**
     * 根据输入的范围获取随机数
     *
     * @param max
     * @return number
     */
    public static String getNumber(int max){
        Random ran = new Random();
        //随机获取一个整数或分数
        if(ran.nextBoolean()) {
            return String.valueOf(ran.nextInt(max));
        }
        else {
            return getFraction(max);
        }
    }


    /**
     * 随机获取分数（一半几率假分数一半几率真分数）
     * @param max
     * @return
     */
    public static String getFraction(int max){
        Random random = new Random();
        int denominator = 0;//分母
        int numerator = 0;//分子
        String[] result = new String[2];

        //获取1-20的分母随机数（不能为0或1）
        while (denominator==0 || denominator==1){
            denominator = random.nextInt(10+1);
        }
        //获取1-分母的分子随机数（不能为0）
        while (numerator==0){
            numerator = random.nextInt(denominator);
        }

        result[0] = String.valueOf(numerator);
        result[1] = String.valueOf(denominator);

        //随机决定是否返回带分数
        if(random.nextBoolean()){
            //带分数的整数部分是1~max-2，分数部分不超过1，所以生成的随机带分数小于 最大值-1
            Integer intPart = random.nextInt(max-1);
            while (intPart==0){
                intPart = random.nextInt(max-1);
            }
            return "" + intPart + "'" + Calculator.getSimplestFraction(result);
        }

        return Calculator.getSimplestFraction(result);
    }

    /**
     * @param num
     * @return childPlace
     */
    public static boolean[] getChildPlace(int num){
        //注释的代码针对任意个运算符的情况
        // int d = 0;//要生成的false元素的个数
        // int size = 0;//数组长度
        // int j=1;
        //
        // while(num >= (int)Math.pow(2, j)){
        //     j++;
        // }
        // d = (int)Math.pow(2, j) - 1 - num;
        // size = (int)Math.pow(2, j-1);
        // boolean[] k = new boolean[size];
        //
        // for(int i = 0; i < size; i++){
        //     k[i] = true;
        // }
        // for(int i = 0; i < d; i++){
        //     Random ran = new Random();
        //     int f = ran.nextInt(size);
        //     while(k[f] == false) {
        //         f = ran.nextInt(size);
        //     }
        //     k[f] = false;
        // }
        // return k;

        boolean[] result = new boolean[]{true, true};

        if(num==2){
            Random random = new Random();
            if(random.nextBoolean()){
                result[0] = false;
            }else {
                result[1] = false;
            }
        }

        return result;
    }
}
