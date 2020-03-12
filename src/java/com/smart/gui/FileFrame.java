package com.smart.gui;

import com.smart.utils.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFrame implements ActionListener {
    JFrame frame = new JFrame("文件选择");
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
    Container container = new Container();
    JLabel directoryLabel = new JLabel("文件目录");
    JLabel fileLabel = new JLabel("选择文件");
    // 显示选择的文件夹的路径
    JTextField text1 = new JTextField();
    // 显示选择的文件的路径
    JTextField text2 = new JTextField();
    // 文件夹选择按钮
    JButton directoryButton = new JButton("选择");
    // 文件选择按钮
    JButton fileButton = new JButton("选择");
    JFileChooser fileChooser = new JFileChooser();// 文件选择器
    JButton submitButton = new JButton("确定");// 提交按钮
    List<String> messages = new ArrayList<>(); // 存放查询结果

    {
        // 设置按钮格式
        directoryButton.setBorder(BorderFactory.createRaisedBevelBorder());
        directoryButton.setFocusPainted(false);
        fileButton.setBorder(BorderFactory.createRaisedBevelBorder());
        fileButton.setFocusPainted(false);
    }

    public FileFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);

        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
        frame.setSize(400, 200);// 设定窗口大小
        frame.setContentPane(tabPane);// 设置布局

        directoryLabel.setBounds(30, 20, 70, 20);
        text1.setBounds(100, 20, 150, 20);
        text1.setEditable(false);
        directoryButton.setBounds(260, 20, 80, 20);

        fileLabel.setBounds(30, 50, 70, 20);
        text2.setBounds(100, 50, 150, 20);
        text2.setEditable(false);
        fileButton.setBounds(260, 50, 80, 20);

        submitButton.setBounds(30, 80, 60, 20);

        // 为三个按钮添加事件处理
        directoryButton.addActionListener(this);
        fileButton.addActionListener(this);
        submitButton.addActionListener(this);

        // 将组件全部添加到container中
        container.add(directoryLabel);
        container.add(text1);
        container.add(directoryButton);
        container.add(fileLabel);
        container.add(text2);
        container.add(fileButton);
        container.add(submitButton);

        // 设置窗口可见
        frame.setVisible(true);
        // 设置关闭窗口结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabPane.add(container);
    }

    public void actionPerformed(ActionEvent e) {
        File directoryChecked;
        File fileChecked;
        // 点击文件夹选择按钮，选择文件夹
        if (e.getSource().equals(directoryButton)) {
            // 设置其只能选择到文件夹
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // 打开文件选择器
            int state = fileChooser.showOpenDialog(null);
            // 如果关闭文件选择，直接返回；否则将选择的文件的文件名显示在面板上
            if (state == 1) {
                return;
            } else {
                directoryChecked = fileChooser.getSelectedFile();
                text1.setText(directoryChecked.getAbsolutePath());
                System.out.println(directoryChecked.getPath());
                for (File f : FileUtil.getFileList(directoryChecked.getPath(), ".*")) {
                    addMessages(messages, f);
                }
                System.out.println(messages.size());
            }
        }
        // 点击文件选择按钮，选择文件
        if (e.getSource().equals(fileButton)) {
            // 设置其只能选择到文件
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // 打开文件选择器
            int state = fileChooser.showOpenDialog(null);
            // 如果关闭文件选择，直接返回；否则将选择的文件的文件名显示在面板上
            if (state == 1) {
                return;
            } else {
                fileChecked = fileChooser.getSelectedFile();
                text2.setText(fileChecked.getAbsolutePath());
                addMessages(messages, fileChecked);
            }
        }
        // 点击提交按钮后显示查询结果
        if (e.getSource().equals(submitButton)) {
            JOptionPane.showMessageDialog(null, messages.toArray(),
                    "选择的文件的查询结果", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // 将指定的文件进行查询，并将结果放置messages中
    private void addMessages(List<String> messages, File f) {
        try {
            messages.add(FileUtil.printCharsetsCount(f.getPath()) + "\n");
            messages.add(FileUtil.printWordsCount(f.getPath()) + "\n");
            messages.add(FileUtil.printLinesCount(f.getPath()) + "\n");
            messages.addAll(FileUtil.printCBNLineCounts(f.getPath()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
