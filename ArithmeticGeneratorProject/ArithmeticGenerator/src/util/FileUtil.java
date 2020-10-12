package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    /**
     * 将算术表达式跟答案分别写入对应文件
     * @param expressions
     * @param answers
     */
    public static void write(List<String> expressions, List<String> answers){
        String currentPath = new File("").getAbsolutePath();//当前绝对路径
        File expFile = new File(currentPath + File.separator + "expFile.txt");
        File answersFile = new File(currentPath + File.separator + "answers.txt");

        FileWriter expWriter;
        FileWriter answerWriter;

        //若对应文件存在，删除并重新创建
        if(expFile.exists()){
            expFile.delete();
        }
        if(answersFile.exists()){
            answersFile.delete();
        }

        try {
            //创建文件
            expFile.createNewFile();
            answersFile.createNewFile();
            //创建文件对应的输出流
            expWriter = new FileWriter(expFile);
            answerWriter = new FileWriter(answersFile);

            writeToFile(expressions, expWriter);
            writeToFile(answers, answerWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将List中的内容写入输出流中，且以下述格式：
     * 1. ---
     * 2. ---
     * 3. ---
     * ...
     * @param content
     * @param writer
     */
    private static void writeToFile(List<String> content, FileWriter writer){
        for(int i=1; i<content.size()+1; i++){
            try {
                writer.write(i + ". " + content.get(i-1) + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
