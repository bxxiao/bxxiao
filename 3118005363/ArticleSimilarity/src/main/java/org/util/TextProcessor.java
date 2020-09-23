package org.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对文章进行分词，根据分词结果计算词频向量的处理类
 */
public class TextProcessor {

    /**
     * 根据两个文件的输入流，对文章进行分词，计算各分词的词频，并返回两个词频向量。
     * 词频向量存储在一个Collection中，集合元素是长度为2的int数组，int[0]是第一个向量的一个坐标值；int[1]是
     * 第二个向量同位置的坐标值。
     * 即每个元素的int[0]依次组合起来就是第一个词频向量；所有int[1]组合起来就是第二个词频向量。
     * @param reader1 文件1的输入流
     * @param reader2 文件2的输入流
     * @return 两个词频向量。
     */
    public static Collection<int[]> getVectors(Reader reader1, Reader reader2){
        //map的key值表示一个分词，value值为一个长度为2的int数组，表示该分词在两个向量中的词频
        Map<String, int[]> vectorMap = new HashMap<>();
        //分词
        List<String> words1 = segmentStr(reader1);
        List<String> words2 = segmentStr(reader2);


        for (int i = 0; i < words1.size(); i++) {
            //若map中已有该分词，对应词频加1
            if (vectorMap.containsKey(words1.get(i))) {
                vectorMap.get(words1.get(i))[0]++;
            //否则创建新的分词
            } else {
                int[] tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(words1.get(i), tempArray);
            }
        }

        for (int i = 0; i < words2.size(); i++) {
            if (vectorMap.containsKey(words2.get(i))) {
                vectorMap.get(words2.get(i))[1]++;
            } else {
                int[] tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(words2.get(i), tempArray);
            }
        }

        //返回map中的词频向量
        return vectorMap.values();
    }

    /**
     * 根据输入流，使用IK分词器将输入流中的内容进行分词，将有用的分词（中文）放在一个集合
     * 并把集合返回
     * @param reader 文件的输入流
     * @return 分词结果
     */
    private static List<String> segmentStr(Reader reader){
        //创建IK分词器
        IKSegmenter iks = new IKSegmenter(reader, true);
        Lexeme lexeme;

        List<String> list = new ArrayList<>();

        try {
            //依次从分词器中获取分词字段，判断是否要过滤掉
            while ((lexeme = iks.next()) != null) {
                String text = lexeme.getLexemeText();
                if(!isAlphaNumeric(text)){
                    list.add(text);
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断字符串是否包含数字、英文。用于过滤掉没用的分词
     * @param str
     * @return
     */
    private static boolean isAlphaNumeric(String str){
        Pattern p = Pattern.compile(".*[a-zA-Z0-9]+.*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
