package FileClean.UI;

import FileClean.Service.CleanService;
import FileClean.Service.api.NotifyLog;
import FileClean.entry.CleanEntry;
import FileClean.entry.SearchType;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainForm implements NotifyLog {
    final int MAX_LOG_LEN = 65535;
    private JPanel pnlRoot;
    private JTextField txtRootDir;
    private JButton cmdSelectDir;
    private JCheckBox cbExtendName;
    private JTextField txtFileExtend;
    private JCheckBox cbName;
    private JTextField txtFileName;
    private JCheckBox cbRegual;
    private JTextField txtRegular;
    private JButton cmdStartExecute;
    private JLabel lbRoot;
    private JTextArea txtLog;

    public MainForm() {
        init();
    }

    public void init() {
        cmdSelectDir.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                FileDialog fileDialog = new FileDialog((JFrame) pnlRoot.getParent().getParent().getParent());
//                fileDialog.setMode(FileDialog.LOAD);
//                fileDialog.setVisible(true);
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int i = jFileChooser.showOpenDialog(pnlRoot);
                if (i == JFileChooser.APPROVE_OPTION) {
                    String directory = jFileChooser.getSelectedFile().getAbsolutePath(); //fileDialog.getDirectory();
                    if (null != directory && "".equals(directory) == false) {
                        txtRootDir.setText(directory);
                    }
                }
            }
        });
        cmdStartExecute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String rootPath = "";
                if (null != txtRootDir.getText()) {
                    rootPath = txtRootDir.getText().trim();
                }
                if ("".equals(rootPath)) {
                    showLog("please select root director");
                    return;
                }
                java.util.List<CleanEntry> lst = new ArrayList<CleanEntry>();
                if (cbExtendName.isSelected()) {
                    if (null == txtFileExtend.getText() || "".equals(txtFileExtend.getText())) {
                        showLog("please input file extention!");
                        txtFileExtend.setFocusable(true);
                        return;
                    }
                    lst.add(new CleanEntry(SearchType.EXTENTION, txtFileExtend.getText().trim()));
                }
                if (cbName.isSelected()) {
                    if (null == txtFileName.getText() || "".equals(txtFileName.getText())) {
                        showLog("please input file or director name!");
                        txtFileName.setFocusable(true);
                        return;
                    }
                    lst.add(new CleanEntry(SearchType.NAME, txtFileName.getText().trim()));
                }
                if (cbRegual.isSelected()) {
                    if (null == txtRegular.getText() || "".equals(txtRegular.getText())) {
                        showLog("please input regular!");
                        txtRegular.setFocusable(true);
                        return;
                    }
                    lst.add(new CleanEntry(SearchType.REGULAR, txtRegular.getText().trim()));
                }
                CleanService.getInstance().StartClean(lst, rootPath);
            }
        });
        CleanService.getInstance().setNotifyLog(this);
    }

    public JPanel getPnlRoot() {
        return pnlRoot;
    }

    public void showLog(String log) {
        if (txtLog.getText().length() + log.length() > MAX_LOG_LEN) {
            if (txtLog.getText().length() >= log.length()) {
                txtLog.setText(txtLog.getText().substring(log.length()));// + "\r\n" + log);
            } else {
                txtLog.setText("");
            }
        }
        txtLog.setText(txtLog.getText() + "\r\n" + log);
    }
}
