package com.smart;

import java.io.*;
import java.util.Scanner;

public class WordCountMain {
    // 程序主入口
    public static void main(String[] args) throws IOException {
        WordCountMain wc = new WordCountMain();
        wc.show();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input=null;
        while ((input = reader.readLine())!=null) {
            if (ParamsUtil.checkInputParams(input)) {
                String params[] = input.split(" ");
                switch (params[1]) {
                    case "-c":
                        System.out.println("指定文件" + params[2] + "的字符数：" + wc.getCharsetsCount(params[2]));
                        break;
                    case "-w":
                        System.out.println("指定文件" + params[2] + "的单词数：" + wc.getWordsCount(params[2]));
                        break;
                    case "-l":
                        System.out.println("指定文件" + params[2] + "的行数目：" + wc.getLinesCount(params[2]));
                        break;
                }
            }
            wc.show();
        }
        // 测试空白文件
//        String blankFilePath = "src/java/com/smart/blank.txt";
//        String test1FilePath = "src/java/com/smart/test1.txt";
//        String test2FilePath = "src/java/com/smart/test2.txt";
//        System.out.println("====================================================");
//        System.out.println("空白文件blank.txt字符数：" + wc.getCharsetsCount(blankFilePath));
//        System.out.println("空白文件blank.txt单词数：" + wc.getWordsCount(blankFilePath));
//        System.out.println("空白文件blank.txt行数：" + wc.getLinesCount(blankFilePath));
//        System.out.println("====================================================");
//        System.out.println("文件test1.txt字符数：" + wc.getCharsetsCount(test1FilePath));
//        System.out.println("文件test1.txt单词数：" + wc.getWordsCount(test1FilePath));
//        System.out.println("文件test1.txt行数：" + wc.getLinesCount(test1FilePath));
//        System.out.println("====================================================");
//        System.out.println("文件test2.txt字符数：" + wc.getCharsetsCount(test2FilePath));
//        System.out.println("文件test2.txt单词数：" + wc.getWordsCount(test2FilePath));
//        System.out.println("文件test2.txt行数：" + wc.getLinesCount(test2FilePath));
    }

    // 传入一个文件路径，返回该文件的字符数
    public int getCharsetsCount(String filePath) throws IOException {
        File file = new File(filePath);
        int charsetsCount = 0;
        String str = null;
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((str = reader.readLine()) != null) {
            str = str.replaceAll(" ", "");
            charsetsCount += str.length();
        }
        return charsetsCount;
    }

    // 传入一个文件路径，返回该文件的词数
    public int getWordsCount(String filePath) throws IOException {
        File file = new File(filePath);
        int wordsCount = 0;
        String str = null;
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((str = reader.readLine()) != null) {
            String[] strArray = str.split(" ");
            for (String s : strArray) {
                if (!s.equals("")) {
                    wordsCount++;
                }
            }
        }
        return wordsCount;
    }

    // 传入一个文件路径，返回该文件的行数
    public int getLinesCount(String filePath) throws IOException {
        File file = new File(filePath);
        int linesCount = 0;
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((reader.readLine()) != null) {
            linesCount++;
        }
        fileReader.close();
        return linesCount;
    }

    /**
     * 提供给用户的GUI界面，如果用户输入不合法，重复提示
     */
    public void show() {
        System.out.println("Word-Count程序可使用的参数：");
        System.out.println("-c filePath =====> [统计指定文件的字符数]");
        System.out.println("-w filePath =====> [统计指定文件的单词数]");
        System.out.println("-l filePath =====> [统计指定文件的行数目]");
        System.out.println("输入示例：wc.exe -c test1.txt =====> 效果：输出test1.txt的字符数");
        System.out.println("请输入合法的操作：");
    }
}
