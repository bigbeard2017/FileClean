package FileClean;

import FileClean.UI.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("文件清理工具");
        frame.setSize(800, 500);
        MainForm mainForm = new MainForm();
        frame.setContentPane(mainForm.$$$getRootComponent$$$());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
