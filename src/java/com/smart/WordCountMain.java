package com.smart;

import com.smart.utils.FileUtil;
import com.smart.utils.ParamsUtil;

import java.io.*;

public class WordCountMain {
    // 程序主入口
    public static void main(String[] args) throws IOException {
        // 提供给用户菜单
        show();
        // 读取用户输入的操作
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        // 创建一个判断输入参数的类实例
        ParamsUtil paramsUtil = new ParamsUtil();
        while ((input = reader.readLine()) != null) {
            // 判断用户的输入是否合法，如果合法，按用户要求的操作执行，如果不合法，要求用户重新输入
            if (paramsUtil.checkInputParams(input)) {
                // 获得用户输入的参数
                String params[] = input.split(" ");
                for(int i=1;i<params.length-1;i++){
                    switch (params[i]) {
                        case "-c":
                            System.out.println("指定文件" + params[i] + "的字符数：" +
                                    FileUtil.getCharsetsCount(params[params.length-1]));
                            break;
                        case "-w":
                            System.out.println("指定文件" + params[i] + "的单词数：" +
                                    FileUtil.getWordsCount(params[params.length-1]));
                            break;
                        case "-l":
                            System.out.println("指定文件" + params[i] + "的行数目：" +
                                    FileUtil.getLinesCount(params[params.length-1]));
                            break;
                    }
                }
            }
            show();
        }
        // 测试空白文件
//        String blankFilePath = "testFile/blank.txt";
//        String test1FilePath = "testFile/test1.txt";
//        String test2FilePath = "testFile/test2.txt";
//        System.out.println("====================================================");
//        System.out.println("空白文件blank.txt字符数：" + FileUtil.getCharsetsCount(blankFilePath));
//        System.out.println("空白文件blank.txt单词数：" + FileUtil.getWordsCount(blankFilePath));
//        System.out.println("空白文件blank.txt行数：" + FileUtil.getLinesCount(blankFilePath));
//        System.out.println("====================================================");
//        System.out.println("文件test1.txt字符数：" + FileUtil.getCharsetsCount(test1FilePath));
//        System.out.println("文件test1.txt单词数：" + FileUtil.getWordsCount(test1FilePath));
//        System.out.println("文件test1.txt行数：" + FileUtil.getLinesCount(test1FilePath));
//        System.out.println("====================================================");
//        System.out.println("文件test2.txt字符数：" + FileUtil.getCharsetsCount(test2FilePath));
//        System.out.println("文件test2.txt单词数：" + FileUtil.getWordsCount(test2FilePath));
//        System.out.println("文件test2.txt行数：" + FileUtil.getLinesCount(test2FilePath));
    }


    /**
     * 提供给用户的GUI界面，如果用户输入不合法，重复提示
     */
    public static void show() {
        System.out.println("Word-Count程序可使用的参数：");
        System.out.println("-c filePath =====> [统计指定文件的字符数]");
        System.out.println("-w filePath =====> [统计指定文件的单词数]");
        System.out.println("-l filePath =====> [统计指定文件的行数目]");
        System.out.println("输入示例：wc.exe -c test1.txt =====> 效果：输出test1.txt的字符数");
        System.out.println("请输入合法的操作：");
    }
}
