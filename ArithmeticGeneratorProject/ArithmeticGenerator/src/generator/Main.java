package generator;

import util.CommandResolver;

public class Main {
    public static void main(String[] args) {
        //命令行参数解析器
        CommandResolver commandResolver = new CommandResolver();
        //解析命令行参数时不符合要求，程序终止
        if(!commandResolver.resolve(args)){
            return;
        }
        //参数输入正确，从解析器中获取解析到的值进行表达式生成
        Generator.generate(commandResolver.getExpCounts(), commandResolver.getMaxValue());
        System.out.println("表达式生成成功，到当前目录下的expFile.txt文件查看题目、answer.txt文件查看答案");
        System.out.println("生成题目数：" + commandResolver.getExpCounts() + " " +
                "运算数的最大值（不包括指定值）：" + commandResolver.getMaxValue());
    }
}
