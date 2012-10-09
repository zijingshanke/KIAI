package insurance;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.*;
import java.sql.*;

public class frmManager extends JFrame {
    public frmManager() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /*修改用户设置的方法*/
    public void userSetup() {
        String name = txtName.getText();
        String oldpw = txtOldPW.getText();
        String newpw = txtNew.getText();
        String surepw = txtSurePw.getText();
        String question = txtQuestion.getText();
        String answer = txtAnswer.getText();
        String strsql =
                "update User_form set Login_Name='" + name + "' , Login_Id='" +
                newpw + "' , Question='" + question + "' , Answer='" +
                answer + "',picture=''  where Login_Id='" + oldpw + "'";
        System.out.println();
        if (name.length() == 0 || oldpw.length() == 0 || question.length() == 0 ||
            answer.length() == 0) {
            JOptionPane.showConfirmDialog(null, "请填写完整信息", "用户操作提示", 1);
            return;
        }
        if (newpw.equals(surepw) == false) {
            JOptionPane.showConfirmDialog(null, "确认密码与新密码不一致，请确认新密码", "用户操作提示",
                                          1);
            return;
        } else {
            try {
                main.stmt.execute(strsql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void jbInit() throws Exception {

        /*确认修改,并重新登录*/
        btnSure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userSetup(); //调用修改设置的方法
                frmManager.this.dispose();
                frmLogin login = new frmLogin();
                login.setUndecorated(true); //去掉标题栏
                login.setSize(650, 480);
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
        });
        /*撤消*/
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        ImageIcon mima = new ImageIcon("image\\mima.jpg");
        lblbei.setIcon(mima);
        //lblbei.setVisible(false);
        lblbei.setBounds(new Rectangle(0, 0, 777, 604));
        ImageIcon inter = new ImageIcon("image\\设置.jpg");
        getContentPane().setLayout(null);
        getContentPane().setLayout(null);
        jTabbedPane1.setBounds(new Rectangle( -1, 0, 593, 550));
        pnlChangePW.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 15));
        pnlChangePW.setLayout(null);
        txtName.setText("");
        txtName.setBounds(new Rectangle(114, 67, 131, 29));
        lblAnswer.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        lblAnswer.setForeground(Color.magenta);
        lblAnswer.setText("密保答案：");
        lblAnswer.setBounds(new Rectangle(289, 153, 103, 35));
        lblOldPw.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        lblOldPw.setForeground(Color.magenta);
        lblOldPw.setText("原密码：");
        lblOldPw.setBounds(new Rectangle(19, 107, 81, 42));
        txtOldPW.setBounds(new Rectangle(115, 116, 131, 29));
        lblQuestion.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        lblQuestion.setForeground(Color.magenta);
        lblQuestion.setText("密保问题：");
        lblQuestion.setBounds(new Rectangle(285, 53, 102, 43));
        lblNew.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        lblNew.setForeground(Color.magenta);
        lblNew.setText("新密码：");
        lblNew.setBounds(new Rectangle(17, 153, 82, 40));
        txtAnswer.setText("");
        txtAnswer.setBounds(new Rectangle(289, 213, 251, 26));
        btnSure.setBounds(new Rectangle(98, 279, 152, 34));
        btnSure.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        btnSure.setForeground(Color.green);
        btnSure.setText("确认重新登录");
        btnCancel.setBounds(new Rectangle(334, 280, 99, 32));
        btnCancel.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        btnCancel.setForeground(Color.green);
        btnCancel.setText("撤消");
        lblbei.setText("");
        lblbei.setBounds(new Rectangle( -4, 0, 691, 426));
        lbltxtbg.setLabelFor(jTabbedPane1);
        lbltxtbg.setText("");
        pnltxt.setToolTipText("安全设置温馨提示");
        ImageIcon imgtxtbg = new ImageIcon("image\\changePwtxt.jpg");
        lbltxtbg.setIcon(imgtxtbg);
        txtQuestion.setText("");
        txtQuestion.setBounds(new Rectangle(284, 101, 250, 29));
        lblName.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        lblName.setForeground(Color.magenta);
        lblName.setText("用户名：");
        lblName.setBounds(new Rectangle(15, 53, 81, 41));
        txtNew.setBounds(new Rectangle(116, 167, 131, 29));
        txtSurePw.setBounds(new Rectangle(119, 218, 131, 29));
        lblSurePw.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));
        lblSurePw.setForeground(Color.magenta);
        lblSurePw.setText("确认新密码");
        lblSurePw.setBounds(new Rectangle(1, 202, 111, 43));
        this.setResizable(false);
        this.setTitle("用户设置");
        pnlChangePW.add(btnSure);
        pnlChangePW.add(btnCancel);
        pnlChangePW.add(lblSurePw);
        pnlChangePW.add(lblNew);
        pnlChangePW.add(lblOldPw);
        pnlChangePW.add(txtNew);
        pnlChangePW.add(txtSurePw);
        pnlChangePW.add(txtOldPW);
        pnlChangePW.add(txtName);
        pnlChangePW.add(lblName);
        pnlChangePW.add(txtAnswer);
        pnlChangePW.add(txtQuestion);
        pnlChangePW.add(lblQuestion);
        pnlChangePW.add(lblAnswer);
        pnlChangePW.add(lblbei);
        pnltxt.add(lbltxtbg);
        pnlChangePW.add(lblbei);
        pnlChangePW.add(lblbei);
        this.getContentPane().add(jTabbedPane1);
        jTabbedPane1.add(pnltxt, "用户安全设置温馨提示");
        jTabbedPane1.add(pnlChangePW, "用户设置操作");
    }

    JTabbedPane jTabbedPane1 = new JTabbedPane();
    JPanel pnlChangePW = new JPanel();
    JTextField txtName = new JTextField();
    JLabel lblAnswer = new JLabel();
    JLabel lblOldPw = new JLabel();
    JTextField txtOldPW = new JTextField();
    JLabel lblQuestion = new JLabel();
    JLabel lblNew = new JLabel();
    JTextField txtAnswer = new JTextField();
    JButton btnSure = new JButton();
    JButton btnCancel = new JButton();
    JLabel lblbei = new JLabel();
    JLabel lbltxtbg = new JLabel();
    JPanel pnltxt = new JPanel();
    JTextField txtQuestion = new JTextField();
    JLabel lblName = new JLabel();
    JTextField txtNew = new JTextField();
    JTextField txtSurePw = new JTextField();
    JLabel lblSurePw = new JLabel();
}
