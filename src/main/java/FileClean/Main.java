package FileClean;

import FileClean.Service.CleanService;
import FileClean.UI.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        CleanService.getInstance().init();
        JFrame frame = new JFrame("文件清理工具");
        frame.setSize(800, 500);
        frame.setContentPane(new MainForm().getPnlRoot());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
