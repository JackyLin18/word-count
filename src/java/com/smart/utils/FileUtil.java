package com.smart.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FileUtil {

    // 根据传入的路径，如果表示的是一个文件夹，返回该文件夹下的所有符合通配符的子文件
    public static List<File> getFileList(String filePath,String consistent) {
        File file = new File(filePath);
        // 存放遍历过程中的文件夹
        LinkedList<File> temp_fileList = new LinkedList<>();
        // 存放需要返回的符合条件的子文件
        List<File> fileList = new ArrayList<>();
        // 先将传入的指定文件放入temp_fileList中
        temp_fileList.add(file);
        // 如果该文件不是文件夹，将其放置fileList中并返回
        if (!file.isDirectory()) {
            // 如果通配符为默认的 ".*" 将所有文件加入fileList
            if(consistent.equals(".*")){
                fileList.add(file);
            }else{
                // 如果文件与指定的格式相符，将其加入返回的fileList
                if(filePath.endsWith(consistent)){
                    fileList.add(file);
                }
            }
            return fileList;
        }
        /*
            如果temp_fileList中的每一个元素，如果该元素为文件夹，将整个元素放至temp_fileList中，
            如果该元素为文件，但不符合通配符，不做任何处理
            如果改文件为文件，且符合通配符，将其放至fileList中
         */
        while (!temp_fileList.isEmpty()) {
            // 遍历temp_fileList
            for (File f : Objects.requireNonNull(temp_fileList.removeFirst().listFiles())) {
                // 如果该元素为文件夹，将其放至temp_fileList中
                if (f.isDirectory()) {
                    temp_fileList.add(f);
                } else {
                    // 如果通配符为默认的 ".*" 将所有文件加入fileList
                    if(consistent.equals(".*")){
                        fileList.add(f);
                    }else{
                        // 如果文件与指定的格式相符，将其加入返回的fileList
                        if(f.getName().endsWith(consistent)){
                            fileList.add(f);
                        }
                    }
                }
            }
        }
        return fileList;
    }

    // "-c"操作：传入一个文件路径，返回该文件的字符数
    public static void printCharsetsCount(String filePath) throws IOException {
        File file = new File(filePath);
        int charsetsCount = 0;
        String str = null;
        // 装饰模式，使其获得多功能
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((str = reader.readLine()) != null) {
            // 将所有的空格去除
            str = str.replaceAll(" ", "");
            charsetsCount += str.length();
        }
        fileReader.close();
        // 输出指定文件文件名及其字符数
        System.out.println("指定文件" + filePath + "的字符数：" + charsetsCount);
    }

    // "-w"操作：传入一个文件路径，返回该文件的词数
    public static void printWordsCount(String filePath) throws IOException {
        File file = new File(filePath);
        int wordsCount = 0;
        String str = null;
        // 装饰模式，使其获得多功能
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((str = reader.readLine()) != null) {
            // 两个单词之间使用空格分开
            String[] strArray = str.split(" ");
            for (String s : strArray) {
                if (!s.equals("")) {
                    wordsCount++;
                }
            }
        }
        fileReader.close();
        // 输出指定文件文件名及其单词数
        System.out.println("指定文件" + filePath + "的单词数：" + wordsCount);
    }

    // "-l"操作：传入一个文件路径，返回该文件的行数
    public static void printLinesCount(String filePath) throws IOException {
        File file = new File(filePath);
        int linesCount = 0;
        // 装饰模式，使其获得多功能
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((reader.readLine()) != null) {
            // 每读取一行，行数加一
            linesCount++;
        }
        fileReader.close();
        // 输出指定文件文件名及其行数目
        System.out.println("指定文件" + filePath + "的行数目：" + linesCount);
    }

    // "-a"操作：传入一个文件路径，输出该文件的代码行数、空行数、注释行数

    /**
     * 代码行：本行包括多于一个字符的代码
     * 空行：本行全部是空格或格式控制字符，如果包括代码，则只有不超过一个可显示的字符，例如 "{"
     * 注释行：本行不是代码行，并且本行包括注释。一个有趣的例子是有些程序员会在单字符后面加注释：
     * } //注释
     * 在这种情况下，这一行属于注释行
     */
    public static void printCBNLineCounts(String filePath) throws IOException {
        int codeLineCounts = 0; // 代码行数目
        int blankLineCounts = 0; // 空白行数目
        int noteLineCounts = 0; // 注释行数目
        File file = new File(filePath);
        // 装饰模式，使其获得多功能
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String str = null;
        while ((str = reader.readLine()) != null) {
            // 去掉空格
            str = str.replaceAll(" ", "");
            /*
                如果包含 "//" 但是注释外的字符多于一个，为代码行
                如果包含 "//" 而且注释外的字符少于或等于一个，为注释行
             */
            if (!str.contains("//")) {
                if (str.length() > 1) {
                    codeLineCounts++;
                } else {
                    blankLineCounts++;
                }
            } else {
                if (str.substring(0, str.indexOf("//")).length() > 1) {
                    codeLineCounts++;
                } else {
                    noteLineCounts++;
                }
            }
        }
        // 输出指定文件文件名及其代码行数、空白行数、注释行数
        System.out.println("指定文件" + filePath + "的代码行数目：" + codeLineCounts);
        System.out.println("指定文件" + filePath + "的空白行数目：" + blankLineCounts);
        System.out.println("指定文件" + filePath + "的注释行数目：" + noteLineCounts);
        fileReader.close();
    }
}
