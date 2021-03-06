package com.smart.utils;

import java.io.File;

public class ParamsUtil {

    // 判断第一个参数是否为合法的"wc.exe"
    private boolean isExe(String input) {
        return input.equals("wc.exe");
    }

    // 判断输入的操作参数是否合法
    private boolean isOperation(String input) {
        switch (input) {
            case "-c":
            case "-w":
            case "-l":
            case "-a":
            case "-s":
            case "-frame":
                return true;
            default:
                return false;
        }
    }

    // 判断文件是否存在
    private boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    // 判断输入的语句是否合法，分三部分进行处理
    public boolean checkInputParams(String input) {
        String[] params = input.split(" ");
        if (!isExe(params[0])) {
            System.out.println("isExe false with " + params[0]);
            return false;
        }
        int filePathIndex = params.length - 2;
        for (int i = 1; i < filePathIndex; i++) {
            if (!isOperation(params[i])) {
                System.out.println("isOperation false with " + params[i]);
                return false;
            }
        }
        if (!isFileExist(params[filePathIndex])) {
            System.out.println("isFileExist false with " + params[filePathIndex]);
            return false;
        }
        return true;
    }
}
