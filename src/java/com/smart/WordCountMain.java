package com.smart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCountMain {
    // 程序主入口
    public static void main(String[] args) throws IOException {
        WordCountMain wc = new WordCountMain();
        // 测试空白文件
        String blankFilePath = "src/java/com/smart/blank.txt";
        String test1FilePath = "src/java/com/smart/test1.txt";
        String test2FilePath = "src/java/com/smart/test2.txt";
        System.out.println("====================================================");
        System.out.println("空白文件blank.txt字符数：" + wc.getCharsetsCount(blankFilePath));
        System.out.println("空白文件blank.txt单词数：" + wc.getWordsCount(blankFilePath));
        System.out.println("空白文件blank.txt行数：" + wc.getLinesCount(blankFilePath));
        System.out.println("====================================================");
        System.out.println("文件test1.txt字符数：" + wc.getCharsetsCount(test1FilePath));
        System.out.println("文件test1.txt单词数：" + wc.getWordsCount(test1FilePath));
        System.out.println("文件test1.txt行数：" + wc.getLinesCount(test1FilePath));
        System.out.println("====================================================");
        System.out.println("文件test2.txt字符数：" + wc.getCharsetsCount(test2FilePath));
        System.out.println("文件test2.txt单词数：" + wc.getWordsCount(test2FilePath));
        System.out.println("文件test2.txt行数：" + wc.getLinesCount(test2FilePath));
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
}
