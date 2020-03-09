package com.smart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCountMain {
    // 程序主入口
    public static void main(String[] args) throws IOException {
        WordCountMain wc = new WordCountMain();
        int linesCount = wc.getLinesCount("src/java/com/smart/test1.txt");
        int charsetsCount = wc.getCharsetsCount("src/java/com/smart/test2.txt");
        System.out.println("文件行数：" + linesCount);
        System.out.println("文件字符数：" + charsetsCount);
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
    public int getWordsCount(String filePath) {
        return -1;
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
