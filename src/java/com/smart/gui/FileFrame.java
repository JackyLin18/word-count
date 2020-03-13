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
    JPanel panel = new JPanel();
    JLabel directoryLabel = new JLabel("文件目录");
    JLabel fileLabel = new JLabel("选择文件");
    JLabel consistentLabel = new JLabel("指定后缀");
    JLabel operationLabel = new JLabel("选择操作");
    // 显示选择的文件夹的路径
    JTextField text1 = new JTextField();
    // 显示选择的文件的路径
    JTextField text2 = new JTextField();
    // 文件夹选择按钮
    JButton directoryButton = new JButton("选择");
    // 文件选择按钮
    JButton fileButton = new JButton("选择");
    // 后缀名下拉框
    JComboBox<String> comboBox = new JComboBox<>();
    // 操作复选框
    JCheckBox jCheckBoxC = new JCheckBox("-c");
    JCheckBox jCheckBoxW = new JCheckBox("-w");
    JCheckBox jCheckBoxL = new JCheckBox("-l");
    JCheckBox jCheckBoxA = new JCheckBox("-a");
    // 文件选择器
    JFileChooser fileChooser = new JFileChooser();
    // 提交按钮
    JButton submitButton = new JButton("确定");
    // 用一个List存放查询结果
    List<String> messages = new ArrayList<>();
    // 用一个List存放选择的操作
    List<JCheckBox> checkBoxList = new ArrayList<>();

    {
        // 设置按钮格式
        directoryButton.setBorder(BorderFactory.createRaisedBevelBorder());
        directoryButton.setFocusPainted(false);
        fileButton.setBorder(BorderFactory.createRaisedBevelBorder());
        fileButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        submitButton.setFocusPainted(false);
    }

    public FileFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);

        // 获得显示屏幕的宽度
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        // 获得显示屏幕的高度
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // 设置窗口出现的位置
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));
        // 设置窗口的大小
        frame.setSize(400, 220);
        // 设置布局
        frame.setContentPane(tabPane);

        // 设置标签、按钮的位置和大小
        fileLabel.setBounds(30, 20, 70, 20);
        text2.setBounds(100, 20, 160, 20);
        text2.setEditable(false);
        fileButton.setBounds(270, 20, 80, 20);

        directoryLabel.setBounds(30, 50, 70, 20);
        text1.setBounds(100, 50, 160, 20);
        text1.setEditable(false);
        directoryButton.setBounds(270, 50, 80, 20);

        consistentLabel.setBounds(30, 80, 70, 20);
        comboBox.setBounds(100, 80, 160, 20);

        operationLabel.setBounds(30, 110, 70, 20);
        jCheckBoxC.setBounds(100, 110, 40, 20);
        jCheckBoxW.setBounds(140, 110, 40, 20);
        jCheckBoxL.setBounds(180, 110, 40, 20);
        jCheckBoxA.setBounds(220, 110, 40, 20);

        submitButton.setBounds(270, 110, 80, 20);

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
        container.add(consistentLabel);
        container.add(comboBox);
        container.add(operationLabel);
        container.add(jCheckBoxC);
        container.add(jCheckBoxW);
        container.add(jCheckBoxL);
        container.add(jCheckBoxA);

        checkBoxList.add(jCheckBoxC);
        checkBoxList.add(jCheckBoxW);
        checkBoxList.add(jCheckBoxL);
        checkBoxList.add(jCheckBoxA);

        // 设置窗口可见
        frame.setVisible(true);
        // 设置关闭窗口结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabPane.add(container);
    }

    public void actionPerformed(ActionEvent e) {
        File directoryChecked = null;
        File fileChecked = null;
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
                comboBox.removeAllItems();
                for (String s : getConsistentList()) {
                    comboBox.addItem(s);
                }
                container.add(comboBox);
                tabPane.add(container);
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
                comboBox.removeAllItems();
                for (String s : getConsistentList()) {
                    comboBox.addItem(s);
                }
                container.add(comboBox);
                tabPane.add(container);
            }
        }
        // 点击提交按钮后弹出窗口，显示查询结果
        if (e.getSource().equals(submitButton)) {
            if (!text1.getText().equals("")) {
                for (File f : FileUtil.getFileList(text1.getText(), (String) comboBox.getSelectedItem())) {
                    addMessages(messages, f, checkBoxList);
                }
            }
            if (!text2.getText().equals("")) {
                for (File f : FileUtil.getFileList(text2.getText(), (String) comboBox.getSelectedItem())) {
                    addMessages(messages, f, checkBoxList);
                }
            }
            JOptionPane.showMessageDialog(null, messages.toArray(),
                    "选择的文件的查询结果", JOptionPane.PLAIN_MESSAGE);
            messages = new ArrayList<>();
        }
    }

    // 将指定的文件进行指定操作的查询，并将结果放置messages中
    private void addMessages(List<String> messages, File f, List<JCheckBox> checkBoxList) {
        try {
            if (checkBoxList.get(0).isSelected()) {
                messages.add(FileUtil.getCharsetsCount(f.getPath()) + "\n");
            }
            if (checkBoxList.get(1).isSelected()) {
                messages.add(FileUtil.getWordsCount(f.getPath()) + "\n");
            }
            if (checkBoxList.get(2).isSelected()) {
                messages.add(FileUtil.getLinesCount(f.getPath()) + "\n");
            }
            if (checkBoxList.get(3).isSelected()) {
                messages.addAll(FileUtil.getCBNLinesCount(f.getPath()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 获取选择的后缀
    private List<String> getConsistentList() {
        // 先获取选择的文件
        String directoryPath = text1.getText();
        String filePath = text2.getText();
        // 创建一个List存放文件的后缀
        List<String> consistentList = new ArrayList<>();
        consistentList.add(".*");
        // 遍历所有文件，获得选择的文件、文件夹中的子文件的所有后缀名
        if (!filePath.equals("")) {
            String s = filePath.substring(filePath.lastIndexOf("."));
            // 如果List中不存在这个后缀名，将其加入List
            if (!consistentList.contains(s)) {
                consistentList.add(s);
            }
        }
        if (!directoryPath.equals("")) {
            for (File f : FileUtil.getFileList(directoryPath, ".*")) {
                String s = f.getPath().substring(f.getPath().lastIndexOf("."));
                // 如果List中不存在这个后缀名，将其加入List
                if (!consistentList.contains(s)) {
                    consistentList.add(s);
                }
            }
        }
        return consistentList;
    }
}
