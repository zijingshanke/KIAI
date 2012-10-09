package insurance;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.ResultSet;
import java.sql.*;

public class frmforget extends JFrame {
    JLabel lblsz = new JLabel();
    JLabel lblname = new JLabel();
    JLabel lblm1 = new JLabel();
    JLabel lblm2 = new JLabel();
    JTextField txtAnswer = new JTextField();
    JTextField txtname = new JTextField();
    JButton btnSure = new JButton();
    JButton btnCancel = new JButton();
    JTextField txtQuestion = new JTextField();
    JList lstQuestion = new JList();
    JLabel lblDown = new JLabel();
    DefaultListModel dlm = new DefaultListModel();
    JLabel lblmima = new JLabel();
    public frmforget() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //确定填入的用户名和密保问题
        btnSure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtname.getText().length() == 0 ||
                    txtQuestion.getText().length() == 0 ||
                    txtAnswer.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(null, "请输入完整信息！",
                                                  "用户操作提示", 1);
                    return;
                }

                String strname =
                        "select Login_name from User_form where Login_Name='" +
                        txtname.getText() + "'";
                try {
                    ResultSet rsname = main.stmt.executeQuery(strname);
                    System.out.println(strname);
                    if (rsname.next() == false) {
                        JOptionPane.showConfirmDialog(null, "不存在此用户名，请重试",
                                "安全系统提示", 2);
                        return;
                    }
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }

                String str = "select * from User_form where Login_name = '" +
                             txtname.getText() + "' and Question='" +
                             txtQuestion.getText() + "' and Answer='" +
                             txtAnswer.getText() + "'";
                try {
                    System.out.println(str);
                    ResultSet re = main.stmt.executeQuery(str);
                    if (re.next()) {
                        lblmima.setText("恭喜你！你的用户名为：" +
                                        txtname.getText() + ",你的密码是：" +
                                        re.getString(2));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lblDown.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        lstQuestion.setVisible(true);
                    }
                });
                lstQuestion.addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        txtQuestion.setText(lstQuestion.getSelectedValue() + "");
                        lstQuestion.setVisible(false);
                    }
                });
                lstQuestion.addMouseListener(new MouseAdapter() {
                    public void mouseExited(MouseEvent e) {
                        lstQuestion.setVisible(false);
                    }
                });
            }
        });

        //撤销按钮事件
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtname.setText("");
                txtQuestion.setText("");
                txtAnswer.setText("");
            }
        });

        lblDown.setVisible(true);

        txtname.setOpaque(false);

        txtname.setForeground(Color.white);

        txtQuestion.setOpaque(false);

        txtQuestion.setForeground(Color.white);

        txtAnswer.setOpaque(false);

        txtAnswer.setForeground(Color.white);

        ImageIcon img = new ImageIcon("image\\Skin_Move.GIF");
        lblDown.setIcon(img);

        lstQuestion.setVisible(false);

        dlm.addElement("你是谁？");

        dlm.addElement("你现在哪个城市？");

        dlm.addElement("你的地址是？");

        dlm.addElement("你来自哪个城市？");

        dlm.addElement("你现就读于哪个班级？");

        lstQuestion.setModel(dlm);

        ImageIcon sz = new ImageIcon("image\\忘记.jpg");
        lblsz.setIcon(sz);

        getContentPane().setLayout(null);

        lblsz.setBounds(new Rectangle(0, 0, 500, 375));

        lblm2.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));

        lblm2.setForeground(Color.red);

        lblm2.setText("密保答案：");

        lblm2.setBounds(new Rectangle(125, 195, 100, 38));

        lblm1.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));

        lblm1.setForeground(Color.red);

        lblm1.setText("密保问题：");

        lblm1.setBounds(new Rectangle(126, 154, 103, 37));

        lblname.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 20));

        lblname.setForeground(Color.red);

        txtAnswer.setBounds(new Rectangle(227, 201, 163, 27));

        txtname.setBounds(new Rectangle(228, 121, 118, 26));

        btnCancel.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 25));

        btnCancel.setForeground(Color.magenta);

        btnSure.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 25));

        btnSure.setForeground(Color.magenta);

        txtQuestion.setBounds(new Rectangle(228, 161, 163, 23));

        lstQuestion.setBounds(new Rectangle(224, 183, 166, 129));

        lblDown.setBounds(new Rectangle(367, 162, 23, 22));

        btnCancel.setBounds(new Rectangle(275, 263, 85, 35));

        btnCancel.setText("撤销");

        btnSure.setBounds(new Rectangle(155, 264, 84, 35));

        btnSure.setText("确定");

        lblmima.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 18));

        lblmima.setForeground(Color.magenta);

        lblmima.setText("");

        lblmima.setBounds(new Rectangle(18, 65, 452, 39));

        this.setResizable(false);

        this.setTitle("用户安全设置－找回密码");

        this.getContentPane().add(lstQuestion);

        this.getContentPane().add(btnCancel);

        this.getContentPane().add(txtAnswer);

        this.getContentPane().add(lblm2);

        this.getContentPane().add(lblm1);

        this.getContentPane().add(lblname);

        this.getContentPane().add(btnSure);

        this.getContentPane().add(lblDown);

        this.getContentPane().add(txtQuestion);

        this.getContentPane().add(txtname);

        this.getContentPane().add(lblmima);

        this.getContentPane().add(lblsz, null);

        lblname.setText("用  户  名：");

        lblname.setBounds(new Rectangle(125, 115, 100, 36));
    }
}
