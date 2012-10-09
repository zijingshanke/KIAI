package insurance;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;


public class frmspeech extends JFrame {
    public frmspeech() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        btnCleant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "是否真的要抛弃我？",
                        "用户操作提示",
                        JOptionPane.YES_NO_OPTION);
            }
        });

        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmaddSpeech ff = new frmaddSpeech();
                ff.setSize(400, 360);
                ff.setLocationRelativeTo(null);
                ff.setVisible(true);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "是否真的要退出？", "温馨提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    frmspeech.this.dispose();
                }
            }
        });

        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setFont(new java.awt.Font("Arial Unicode MS", Font.PLAIN, 15));
        this.setForeground(Color.black);
        this.setResizable(false);
        this.setTitle("保 险 话 术");
        jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
        jPanel1.setBounds(new Rectangle(6, 3, 440, 322));
        jPanel1.setLayout(null);
        btnExit.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
        btnExit.setForeground(Color.blue);
        jTable1.setMaximumSize(new Dimension(4, 2));
        jTable1.setBounds(new Rectangle(6, 5, 309, 97));
        btnInsert.setBounds(new Rectangle(341, 5, 70, 29));
        btnInsert.setForeground(Color.red);
        btnInsert.setText("增加");
        btnCleant.setBounds(new Rectangle(342, 38, 67, 31));
        btnCleant.setFont(new java.awt.Font("宋体", Font.PLAIN, 13));
        btnCleant.setForeground(Color.red);
        btnCleant.setText("删除 ");
        txtinfo.setBorder(BorderFactory.createLoweredBevelBorder());
        txtinfo.setText("");
        txtinfo.setBounds(new Rectangle(2, 108, 434, 210));
        this.getContentPane().add(jPanel1);
        jPanel1.add(jTable1);
        jPanel1.add(txtinfo);
        jPanel1.add(btnInsert);
        jPanel1.add(btnCleant);
        jPanel1.add(btnExit);
        btnExit.setBounds(new Rectangle(343, 75, 67, 28));
        btnExit.setText("退出");
    }

    JPanel jPanel1 = new JPanel();
    JButton btnExit = new JButton();
    JTable jTable1 = new JTable();
    JButton btnInsert = new JButton();
    JButton btnCleant = new JButton();
    JTextArea txtinfo = new JTextArea();
}
