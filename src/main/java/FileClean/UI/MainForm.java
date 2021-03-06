package FileClean.UI;

import FileClean.Service.CleanService;
import FileClean.Service.api.NotifyLog;
import FileClean.entry.CleanEntry;
import FileClean.entry.SearchType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
    private JButton cmdStartFind;
    private JButton cmdClearLog;

    public MainForm() {
        init();

    }

    public JPanel getPnlRoot() {
        return pnlRoot;
    }

    private void init() {
        cmdStartFind.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<CleanEntry> input = getInput();
                String rootPath = txtRootDir.getText().trim();
                CleanService.getInstance().startFind(input, rootPath);
            }
        });
        cmdSelectDir.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int i = jFileChooser.showOpenDialog(pnlRoot);
                if (i == JFileChooser.APPROVE_OPTION) {
                    String directory = jFileChooser.getSelectedFile().getAbsolutePath();
                    if (null != directory && "".equals(directory) == false) {
                        txtRootDir.setText(directory);
                    }
                }
            }
        });
        cmdStartExecute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(new JFrame().getContentPane(),
                        "删除后将无法恢复,确定要删除吗?", "警告提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    List<CleanEntry> input = getInput();
                    String rootPath = txtRootDir.getText().trim();
                    CleanService.getInstance().startClean(input, rootPath);
                }
            }
        });
        cmdClearLog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLog.setText("");
            }
        });
        CleanService.getInstance().setNotifyLog(this);
    }

    private List<CleanEntry> getInput() {
        String rootPath = "";
        if (null != txtRootDir.getText()) {
            rootPath = txtRootDir.getText().trim();
        }
        if ("".equals(rootPath)) {
            showLog("please select root director");
            return null;
        }
        List<CleanEntry> lst = new ArrayList<CleanEntry>(3);
        if (cbExtendName.isSelected()) {
            if (null == txtFileExtend.getText() || "".equals(txtFileExtend.getText())) {
                showLog("please input file extention!");
                txtFileExtend.setFocusable(true);
                return null;
            }
            lst.add(new CleanEntry(SearchType.EXTENTION, txtFileExtend.getText().trim()));
        }
        if (cbName.isSelected()) {
            if (null == txtFileName.getText() || "".equals(txtFileName.getText())) {
                showLog("please input file or director name!");
                txtFileName.setFocusable(true);
                return null;
            }
            lst.add(new CleanEntry(SearchType.NAME, txtFileName.getText().trim()));
        }
        if (cbRegual.isSelected()) {
            if (null == txtRegular.getText() || "".equals(txtRegular.getText())) {
                showLog("please input regular!");
                txtRegular.setFocusable(true);
                return null;
            }
            lst.add(new CleanEntry(SearchType.REGULAR, txtRegular.getText().trim()));
        }
        return lst;
    }

    public void showLog(String log) {

        SwingUtilities.invokeLater(() -> {
//            if (txtLog.getText().length() + log.length() > MAX_LOG_LEN) {
//                if (txtLog.getText().length() >= log.length()) {
//                    txtLog.setText(txtLog.getText().substring(log.length()));// + "\r\n" + log);
//                } else {
//                    txtLog.setText("");
//                }
//            }
            if (null == txtLog.getText() || "".equals(txtLog.getText())) {
                txtLog.setText(log);
            } else {
                txtLog.setText(txtLog.getText() + "\r\n" + log);
            }
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        pnlRoot = new JPanel();
        pnlRoot.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 4, new Insets(10, 10, 10, 10), -1, -1));
        lbRoot = new JLabel();
        lbRoot.setText("根目录");
        pnlRoot.add(lbRoot, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtRootDir = new JTextField();
        pnlRoot.add(txtRootDir, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cmdSelectDir = new JButton();
        cmdSelectDir.setText("选择目录");
        pnlRoot.add(cmdSelectDir, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbExtendName = new JCheckBox();
        cbExtendName.setText("按扩展名");
        pnlRoot.add(cbExtendName, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFileExtend = new JTextField();
        pnlRoot.add(txtFileExtend, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbName = new JCheckBox();
        cbName.setText("按名称");
        pnlRoot.add(cbName, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFileName = new JTextField();
        pnlRoot.add(txtFileName, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbRegual = new JCheckBox();
        cbRegual.setText("名称正则");
        pnlRoot.add(cbRegual, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtRegular = new JTextField();
        pnlRoot.add(txtRegular, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cmdStartExecute = new JButton();
        cmdStartExecute.setText("开始删除");
        pnlRoot.add(cmdStartExecute, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBarPolicy(22);
        pnlRoot.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtLog = new JTextArea();
        txtLog.setText("");
        scrollPane1.setViewportView(txtLog);
        cmdStartFind = new JButton();
        cmdStartFind.setText("开始查找");
        pnlRoot.add(cmdStartFind, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlRoot.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cmdClearLog = new JButton();
        cmdClearLog.setText("清除日志");
        pnlRoot.add(cmdClearLog, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlRoot;
    }
}
