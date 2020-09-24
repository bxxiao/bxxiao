package org.similarity;

public class Main {
    public static void main(String[] args){
        CosSimilarity cosSimilarity = new CosSimilarity();
        try {
            cosSimilarity.work(args[0], args[1], args[2]);
        }catch (ArrayIndexOutOfBoundsException e){
            //抛出数组越界异常说明输入参数个数不足
            System.out.println("参数输入错误，请按以下形式输入参数：【原文文件路径 抄袭文件路径 答案文件路径】");
            return;
        }
    }
}
