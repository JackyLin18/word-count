package com.smart;

import com.smart.gui.FileFrame;
import com.smart.utils.FileUtil;
import com.smart.utils.ParamsUtil;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class WordCountMain {
    // 程序主入口
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // 提供给用户菜单
        show();
        // 读取用户输入的操作
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        // 创建一个判断输入参数的类实例
        ParamsUtil paramsUtil = new ParamsUtil();
        while ((input = reader.readLine()) != null) {
            if(input.contains("-frame")){
                new FileFrame();
            }
            // 判断用户的输入是否合法，如果合法，按用户要求的操作执行，如果不合法，要求用户重新输入
            // 对输入进行 "添加通配符" 的处理
            if (!input.contains("-s")) {
                // 如果没有输入"-s"，自动添加默认的通配符
                input = input.concat(" .*");
            } else {
                // 如果输入了"-s"，但没有输入通配符，也添加默认的通配符
                String[] params = input.split(" ");
                if (!params[params.length - 1].contains(".")) {
                    input = input.concat(" .*");
                }
            }
            if (paramsUtil.checkInputParams(input)) {
                // 获得用户输入的参数数组
                String[] params = input.split(" ");
                int paramsLength = params.length;
                for (int i = 1; i < paramsLength; i++) {
                    // FileUtil#getFileList()第一个参数为指定的文件路径，第二个参数为指定文件的通配符
                    switch (params[i]) {
                        case "-c":
                            // 输出指定文件的字符数
                            for (File f : FileUtil.getFileList(params[paramsLength - 2],
                                    params[paramsLength - 1])) {
                                FileUtil.printCharsetsCount(f.getPath());
                            }
                            break;
                        case "-w":
                            // 输出指定文件的单词数
                            for (File f : FileUtil.getFileList(params[paramsLength - 2],
                                    params[paramsLength - 1])) {
                                FileUtil.printWordsCount(f.getPath());
                            }
                            break;
                        case "-l":
                            // 输出指定文件的行数
                            for (File f : FileUtil.getFileList(params[paramsLength - 2],
                                    params[paramsLength - 1])) {
                                FileUtil.printLinesCount(f.getPath());
                            }
                            break;
                        case "-a":
                            // 输出指定文件的代码行数、空白行数、注释行数
                            for (File f : FileUtil.getFileList(params[paramsLength - 2],
                                    params[paramsLength - 1])) {
                                FileUtil.printCBNLineCounts(f.getPath());
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            show();
        }
    }


    /**
     * 提供给用户的GUI界面，如果用户输入不合法，重复提示
     */
    public static void show() {
        System.out.println("Word-Count程序可使用的参数：");
        System.out.println("-c filePath =====> [统计指定文件的字符数]");
        System.out.println("-w filePath =====> [统计指定文件的单词数]");
        System.out.println("-l filePath =====> [统计指定文件的行数目]");
        System.out.println("-a filePath =====> [统计指定文件的代码行数目、空白行数目、注释行数目]");
        System.out.println("-s filePath xx.xx =====> [统计指定文件夹中符合通配符的文件的相关数据]");
        System.out.println("-frame =====> [调出图形界面]");
        System.out.println("输入示例：wc.exe -c -w -l -a -s testFile .txt");
        System.out.println("请输入合法的操作：");
    }
}
