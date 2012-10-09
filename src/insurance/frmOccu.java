package insurance;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.*;

/**
 * <p>Title:职业代码查询 －－> 快速搜索，一网打尽 </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class frmOccu extends JFrame {
    boolean a = false;
    public frmOccu() {
        try {
            jbInit();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void occu() {
        String str =
                "select distinct BigType  from Occup_form order by BigType ";
        try {
            ResultSet rs = main.stmt.executeQuery(str);
            while (rs.next()) {
                cmbBig.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        occu();
        cmbBig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a = false;
                cmbMiddle.removeAllItems();
                cmbMini.removeAllItems();
                String str =
                        "select distinct MiddleType from Occup_form where BigType='" +
                        cmbBig.getSelectedItem() + "' order by Middletype ";
                System.out.println(str);
                try {
                    ResultSet rs = main.stmt.executeQuery(str);
                    cmbMiddle.addItem("");
                    while (rs.next()) {
                        cmbMiddle.addItem(rs.getString(1));

                    }
                    a = true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cmbMiddle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (a == true) {
                    String str =
                            "select distinct SmallType from Occup_form where MiddleType='" +
                            cmbMiddle.getSelectedItem() +
                            "' order by SmallType ";
                    try {
                        ResultSet rs = main.stmt.executeQuery(str);
                        cmbMini.addItem("");
                        while (rs.next()) {
                            cmbMini.addItem(rs.getString(1));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        lblOccup.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String str = "select * from Occup_form where BigType='" +
                             cmbBig.getSelectedItem() + "'";
                if (cmbMiddle.getSelectedIndex() > 0) {
                    str = str + " and MiddleType='" + cmbMiddle.getSelectedItem() +
                          "'";
                }
                if (cmbMini.getSelectedIndex() > 0) {
                    str = str + " and SmallType='" + cmbMini.getSelectedItem() +
                          "'";
                }

                try {
                    ResultSet rs = main.stmt.executeQuery(str);
                    if (rs.next()) {
                        txtMa.setText(rs.getString(1));
                        if (cmbMiddle.getSelectedIndex() > 0) {
                            txtMa.setText(rs.getString(2));
                        }
                        if (cmbMini.getSelectedIndex() > 0) {
                            txtMa.setText(rs.getString(3));
                        }
                    } else {
                        txtMa.setText("");
                    }
                    rs.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        /*刷新按扭事件*/
        btnfresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                cmbBig.setSelectedIndex(0);
                cmbMiddle.setSelectedIndex(0);
                cmbMini.setSelectedIndex(0);
            }
        });

        getContentPane().setLayout(null);
        this.setTitle("职业代码查询");
        lblOccup.setText("");
        lblOccup.setVerticalAlignment(SwingConstants.BOTTOM);
        lblOccup.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblOccup.setBounds(new Rectangle(0, 0, 516, 362));

        ImageIcon imgOccu = new ImageIcon("image\\Occup.gif");
        lblOccup.setIcon(imgOccu);

        ImageIcon imgOcc = new ImageIcon("image\\OccupWhere.jpg");
        lblOcc.setIcon(imgOcc);
        btnName.setEnabled(true);
        btnName.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        btnName.setForeground(Color.red);
        btnName.setText("从职业名称查代码");
        btnName.setBounds(new Rectangle(20, 11, 184, 40));
        cmbMini.setBounds(new Rectangle(75, 232, 135, 30));
        lblOcc.setBorder(BorderFactory.createLineBorder(Color.black));
        lblOcc.setText("");
        lblOcc.setBounds(new Rectangle(223, 120, 91, 93));
        lblResult.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblResult.setForeground(Color.red);
        lblResult.setText("职业代码：");
        lblResult.setBounds(new Rectangle(357, 119, 101, 30));
        txtMa.setText("");
        txtMa.setBounds(new Rectangle(333, 159, 150, 32));
        jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
        jLabel1.setForeground(Color.red);
        jLabel1.setText("中类");
        jLabel1.setBounds(new Rectangle(14, 162, 49, 28));
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
        jLabel2.setForeground(Color.red);
        jLabel2.setText("小类");
        jLabel2.setBounds(new Rectangle(16, 233, 49, 28));
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
        jLabel3.setForeground(Color.red);
        jLabel3.setText("大类");
        jLabel3.setBounds(new Rectangle(16, 92, 49, 28));
        btnfresh.setBounds(new Rectangle(316, 26, 93, 36));
        btnfresh.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        btnfresh.setForeground(Color.blue);
        btnfresh.setText("刷  新");
        this.getContentPane().add(btnName);
        cmbBig.setBounds(new Rectangle(73, 91, 135, 30));
        cmbMiddle.setBounds(new Rectangle(72, 162, 135, 30));
        this.getContentPane().add(cmbMiddle);
        this.getContentPane().add(cmbBig);
        this.getContentPane().add(cmbMini);
        this.getContentPane().add(lblOcc);
        this.getContentPane().add(txtMa);
        this.getContentPane().add(lblResult);
        this.getContentPane().add(jLabel3);
        this.getContentPane().add(jLabel1);
        this.getContentPane().add(jLabel2);
        this.getContentPane().add(btnfresh);
        this.getContentPane().add(lblOccup);
        bp.add(btnName);
    }

    JLabel lblOccup = new JLabel();
    JRadioButton btnName = new JRadioButton();
    JComboBox cmbMiddle = new JComboBox();
    JComboBox cmbBig = new JComboBox();
    JComboBox cmbMini = new JComboBox();
    JLabel lblOcc = new JLabel();
    JLabel lblResult = new JLabel();
    JTextField txtMa = new JTextField();
    ButtonGroup bp = new ButtonGroup();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JButton btnfresh = new JButton();
}
