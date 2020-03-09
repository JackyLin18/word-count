package com.smart.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    // 传入一个文件路径，返回该文件的字符数
    public static int getCharsetsCount(String filePath) throws IOException {
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
    public static int getWordsCount(String filePath) throws IOException {
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
    public static int getLinesCount(String filePath) throws IOException {
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
