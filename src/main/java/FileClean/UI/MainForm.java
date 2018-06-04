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

    public MainForm() {
        init();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public JPanel getPnlRoot() {
        return pnlRoot;
    }

    private void init() {
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

                String rootPath = "";
                if (null != txtRootDir.getText()) {
                    rootPath = txtRootDir.getText().trim();
                }
                if ("".equals(rootPath)) {
                    showLog("please select root director");
                    return;
                }
                List<CleanEntry> lst = new ArrayList<CleanEntry>();
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        pnlRoot = new JPanel();
        pnlRoot.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(10, 10, 10, 10), -1, -1));
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
        cmdStartExecute.setText("开始执行");
        pnlRoot.add(cmdStartExecute, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBarPolicy(22);
        pnlRoot.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txtLog = new JTextArea();
        txtLog.setText("");
        scrollPane1.setViewportView(txtLog);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlRoot;
    }
}
