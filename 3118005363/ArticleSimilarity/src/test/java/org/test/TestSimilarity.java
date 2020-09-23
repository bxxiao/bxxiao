package org.test;

import org.junit.Test;
import org.similarity.Main;

public class TestSimilarity {

    /**
     * 根据3个文件名，通过类加载器获取classes目录下对应文件的路径，并调用main方法进行测试
     * @param source 原文件名
     * @param plagiarize 抄袭文件名
     * @param answer 答案文件名
     */
    private void test(String source, String plagiarize, String answer){
        //使用类加载器获取target目录下对应的文件
        ClassLoader classLoader = TestSimilarity.class.getClassLoader();

        source = classLoader.getResource(source).getPath();
        plagiarize = classLoader.getResource(plagiarize).getPath();
        answer = classLoader.getResource(answer).getPath();

        Main.main(new String[]{source, plagiarize, answer});
    }

    /**
     * 测试抄袭文本在原文基础上添加文本的情况
     */
    @Test
    public void testAdd(){
        System.out.println("测试抄袭文本在原文基础上添加文本的情况：");
        test("orig.txt", "orig_add.txt", "answer.txt");
        System.out.println();
    }


    /**
     * 测试抄袭文本在原文基础上删除文本的情况
     */
    @Test
    public void testDelete(){
        System.out.println("测试抄袭文本在原文基础上删除文本的情况：");
        test("orig.txt", "orig_del.txt", "answer.txt");
        System.out.println();
    }

    /**
     * 测试文章为空文本的情况
     */
    @Test
    public void testEmpty(){
        System.out.println("测试文章为空文本的情况：");
        test("orig.txt", "empty.txt", "answer.txt");
        System.out.println();
    }

    /**
     * 测试文章完全相同的情况
     */
    @Test
    public void testSame(){
        System.out.println("测试文章完全相同的情况：");
        test("orig.txt", "same.txt", "answer.txt");
        System.out.println();
    }

    /**
     * 测试文章中含有干扰文本的情况
     */
    @Test
    public void testDis(){
        System.out.println("测试文章中含有干扰文本的情况：");
        test("orig.txt", "orig_dis.txt", "answer.txt");
        System.out.println();
    }

    /**
     * 测试main方法参数传入错误(输入个数少于3个)的情况
     */
    @Test
    public void testMainException(){
        System.out.println("测试main方法参数传入错误(输入个数少于3个)的情况：");
        Main.main(new String[]{"testPath1", "testPath2"});
        System.out.println();
    }

    /**
     * 测试文件路径输入错误的情况
     */
    @Test
    public void testPathError(){
        System.out.println("测试文件路径输入错误的情况：");
        Main.main(new String[]{"errorPath",
                "errorPath", "E:\\AAAFrequently-used\\something\\test\\answer.txt"});
        System.out.println();
    }

    /**
     * 测试删除第一段的情况
     */
    @Test
    public void testSubOrig(){
        System.out.println("测试删除第一段的情况：");
        test("orig.txt", "suborig.txt", "answer.txt");
        System.out.println();
    }


}
