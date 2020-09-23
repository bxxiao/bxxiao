package org.similarity;

import org.util.CosCalculator;
import org.util.TextProcessor;

import java.io.*;
import java.util.Collection;

public class CosSimilarity {
    private FileReader sourceReader;
    private FileReader plagiarizeReader;
    private FileWriter answerWriter;

    /**
     * 根据传进的路径参数，加载3个流。根据原文件、抄袭文件获取词频向量，计算余弦相似度
     * 将结果输出到指定的答案文件
     * @param source 原文件路径
     * @param plagiarize 抄袭文件路径
     * @param answer 答案文件路径
     */
    public void work(String source, String plagiarize, String answer){
        //初始化3个流
        initFileIO(source, plagiarize, answer);

        //获取词频向量
        Collection<int[]> vectors = TextProcessor.getVectors(sourceReader, plagiarizeReader);

        //计算结果
        String result = CosCalculator.getCos(vectors);
        System.out.println("结果：" + result + "%");
        try {
            //将结果写入答案文件
            answerWriter.write("结果：" + result + "%");
            answerWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化3个文件对应的流
     * @param source 原文文件路径
     * @param plagiarize 抄袭文件路径
     * @param answer 答案文件
     */
    public void initFileIO(String source, String plagiarize, String answer){
        File sourceFile = new File(source);
        File plagiarizeFile = new File(plagiarize);
        File answerFile = new File(answer);

        try{
            sourceReader = new FileReader(sourceFile);
            plagiarizeReader = new FileReader(plagiarizeFile);
            answerWriter = new FileWriter(answerFile);
        }catch (IOException e) {
            //捕获异常。此处出现异常说明输入的路径出错
            System.out.println("文件未找到，请正确输入路径");
            System.exit(0);
        }

    }
}
