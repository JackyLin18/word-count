package com.smart;

import java.io.File;

public class ParamsUtil {

    // 判断第一个参数是否为合法的"wc.exe"
    private static boolean isExe(String input) {
        if (input.equals("wc.exe")) {
            return true;
        }
        return false;
    }

    // 判断输入的操作参数是否合法
    private static boolean isOperation(String input) {
        switch (input) {
            case "-c":
            case "-w":
            case "-l":
                return true;
            default:
                return false;
        }
    }

    // 判断文件是否存在
    private static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    // 判断输入的语句是否合法，分三部分进行处理
    public static boolean checkInputParams(String input) {
        String[] params = input.split(" ");
        if (isExe(params[0]) && isOperation(params[1]) && isFileExist(params[2])) {
            return true;
        }
        return false;
    }
}
