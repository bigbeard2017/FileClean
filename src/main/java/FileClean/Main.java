package FileClean;

import FileClean.UI.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("文件清理工具");
        frame.setSize(800, 500);
        MainForm mainForm = new MainForm();
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("timg.jpeg"));  //xxx代表图片存放路径，2.png图片名称及格式
        frame.setIconImage(icon.getImage());
//        try {
//            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.gtk.GTKLookAndFeel());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        frame.setContentPane(mainForm.getPnlRoot());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
