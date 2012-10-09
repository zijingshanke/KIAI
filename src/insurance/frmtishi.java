package insurance;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class frmtishi extends JFrame {
    public frmtishi() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                frmtishi.this.dispose();
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmtishi.this.dispose();
            }
        });
        ImageIcon ph = new ImageIcon("image\\提示.jpg");
        lbltu.setIcon(ph);
        getContentPane().setLayout(null);
        lbltu.setText("");
        lbltu.setBounds(new Rectangle( 0, 0, 300, 225));
        lblcuo.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 25));
        lblcuo.setForeground(Color.red);

        lblcuo.setBounds(new Rectangle(95, 43, 106, 32));
        lblcuo2.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 25));
        lblcuo2.setForeground(Color.red);
        lblcuo2.setText("是否真的要退出？");
        lblcuo2.setBounds(new Rectangle(64, 54, 213, 34));
        btn2.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 15));
        btn1.setFont(new java.awt.Font("华文新魏", Font.PLAIN, 15));
        btn2.setBounds(new Rectangle(177, 113, 75, 29));
        btn2.setText("撤消");
        btn1.setBounds(new Rectangle(72, 112, 75, 29));
        btn1.setText("确定");
        this.setTitle("提示");
        this.getContentPane().add(lblcuo);
        this.getContentPane().add(btn1);
        this.getContentPane().add(btn2);
        this.getContentPane().add(lblcuo2);
        this.getContentPane().add(lbltu);
    }

    JLabel lbltu = new JLabel();
    JLabel lblcuo = new JLabel();
    JLabel lblcuo2 = new JLabel();
    JButton btn1 = new JButton();
    JButton btn2 = new JButton();
}
