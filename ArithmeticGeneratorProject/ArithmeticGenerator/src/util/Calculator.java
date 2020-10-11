package util;

/**
 * 提供了分数形式的加减乘除运算，返回的运算结果是最简形式
 * 也提供了根据字符串获取其分子分母（String[]）以及获取指定分数的最简形式的方法
 */
public class Calculator {

    /**
     * 加法
     * 其运算结果是最简形式
     * @param num1
     * @param num2
     * @return
     */
    public static String add(String num1, String num2){

        String[] result = new String[2];

        Integer[] fractions = getIntFractions(num1, num2);
        //分母的最小公倍数
        Integer lcm = getLcm(fractions[1], fractions[3]);

        //分母化成最小公倍数，分子乘以对应倍数并相加
        result[0] = String.valueOf(fractions[0]*(lcm/fractions[1]) + fractions[2]*(lcm/fractions[3]));
        result[1] = String.valueOf(lcm);

        //返回最简形式
        return getSimplestFraction(result);
    }

    /**
     * 减法
     * 对于被减数小于减数的情况，不作运算，返回-1
     * @param num1
     * @param num2
     * @return
     */
    public static String sub(String num1, String num2){
        Integer[] fractions = getIntFractions(num1, num2);
        String[] result = new String[2];

        //分母的最小公倍数
        Integer lcm = getLcm(fractions[1], fractions[3]);

        if(fractions[0]*(lcm/fractions[1]) < fractions[2]*(lcm/fractions[3])){
            return "-1";
        }

        result[0] = String.valueOf(fractions[0]*(lcm/fractions[1]) - fractions[2]*(lcm/fractions[3]));
        result[1] = String.valueOf(lcm);

        return getSimplestFraction(result);
    }

    /**
     * 乘法
     * @param num1
     * @param num2
     * @return
     */
    public static String mul(String num1, String num2){
        Integer[] fractions = getIntFractions(num1, num2);
        String[] result = new String[2];

        //分子分母相乘
        result[0] = String.valueOf(fractions[0]*fractions[2]);
        result[1] = String.valueOf(fractions[1]*fractions[3]);

        return getSimplestFraction(result);
    }

    /**
     * 除法
     * 对于除数为0的情况，返回 "-1"
     * @param num1
     * @param num2
     * @return
     */
    public static String division(String num1, String num2){
        Integer[] fractions = getIntFractions(num1, num2);

        if(fractions[0]==0){
            return "0";
        }
        if(fractions[2]==0){
            return "-1";
        }

        String[] result = new String[2];

        //除法结果等于被除数乘以除数的倒数
        result[0] = String.valueOf(fractions[0]*fractions[3]);
        result[1] = String.valueOf(fractions[1]*fractions[2]);

        return getSimplestFraction(result);
    }

    /**
     * 获取字符串对应的分数（String[]数组，[0]存放分子，[1]存放分母）
     * 这里针对number是: 带分数形式（如 2'1/3 ） 、 普通分数形式（如 8/3 , 1/8）和 整数形式 3种情况
     */
    public static String[] getFraction(String number){
        String[] result = null;//result[0]是分子、result[1]是分母
        //number是带分数
        if(number.contains("'")){
            String[] temp = number.split("'");
            result = temp[1].split("/");

            Integer a = Integer.valueOf(temp[0]);//带分数的整数部分
            Integer b = Integer.valueOf(result[0]);//带分数分数部分的分子
            Integer c = Integer.valueOf(result[1]);//带分数分数部分的分母

            result[0] = String.valueOf(a*c+b);
        }
        //number是普通分数
        else if(number.contains("/")){
            result = number.split("/");
        }
        //number是整数
        else {
            result = new String[2];
            result[0] = number;
            result[1] = "" + 1;
        }
        return result;
    }

    // 最大公约数
    public static int getGcd(int a, int b) {
        int max, min;
        max = (a > b) ? a : b;
        min = (a < b) ? a : b;

        if (max % min != 0) {
            return getGcd(min, max % min);
        } else
            return min;

    }

    //最小公倍数
    private static int getLcm(int a, int b) {
        return a * b / getGcd(a, b);
    }

    /**
     * 获取分数的最简形式
     * @param fraction 指定分数
     * @return
     */
    public static String getSimplestFraction(String[] fraction){
        //分母整除分子，直接返回整数形式
        if(Integer.parseInt(fraction[0])%Integer.parseInt(fraction[1])==0){
            return "" + Integer.parseInt(fraction[0])/Integer.parseInt(fraction[1]);
        }

        //分子分母转换为Integer类型
        Integer numerator = Integer.parseInt(fraction[0]);
        Integer denominator = Integer.parseInt(fraction[1]);

        //获取最大公约数
        Integer gcd = getGcd(numerator, denominator);

        //化简
        numerator = numerator/gcd;
        denominator = denominator/gcd;

        //分子大于分母，化为带分数
        if(numerator>denominator){
            Integer intPart = numerator/denominator;
            numerator = numerator-intPart*denominator;
            return "" + intPart + "'" + numerator + "/" + denominator;
        }else {
            return "" + numerator + "/" + denominator;
        }
    }

    /**
     * 根据两个数的字符串，获取他们的分数形式
     * 返回的Integer数组中，
     * [0]存放第一个数的分子；[1]存放第一个数的分母；[2]存放第二个数的分子；[3]存放第二个数的分母
     * @param num1
     * @param num2
     * @return
     */
    private static Integer[] getIntFractions(String num1, String num2){
        Integer[] result = new Integer[4];
        String[] fraction1 = getFraction(num1);
        String[] fraction2 = getFraction(num2);

        //两个分子
        result[0] = Integer.valueOf(fraction1[0]);
        result[2] = Integer.valueOf(fraction2[0]);
        //两个分母
        result[1] = Integer.valueOf(fraction1[1]);
        result[3] = Integer.valueOf(fraction2[1]);

        return result;
    }
}
