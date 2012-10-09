package insurance;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class frmDataCenter extends JFrame {
    public frmDataCenter() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

///88888888888888*******Client**********************////
   public void clientinfo() { //表格更新函数
       Vector x = new Vector();
       Vector y = new Vector();
       DefaultTableModel model;

       x.removeAllElements();
       y.removeAllElements();
       try {
           x.add("序号");
           x.add("客户分组");
           x.add("客户姓名");
           x.add("别名/昵称");
           x.add("性别");
           x.add("出生日期");
           x.add("年龄");
           x.add("客户类型");
           x.add("工作单位");
           x.add("爱好/特长");
           x.add("家庭住址");
           x.add("住所电话");
           x.add("联系电话Ⅰ");
           x.add("联系电话Ⅱ");
           x.add("电子邮件");
           x.add("配偶姓名");
           x.add("备注");
           ResultSet rs = main.stmt.executeQuery(
                   "select * from Client_form where Avail=0");
           while (rs.next() == true) {
               Vector lin = new Vector();
               lin.add(rs.getInt(1) + "");
               lin.add(rs.getString(2));
               lin.add(rs.getString(3));
               lin.add(rs.getString(4));
               if (rs.getInt(5) == 0) {
                   lin.add("男");
               } else {
                   lin.add("女");
               }
               //lin.add(rs.getInt(5)+"");
               lin.add(rs.getString(6));
               lin.add(rs.getInt(7) + "");
               int leixing = rs.getInt(8);
               if (leixing == 1) {
                   lin.add("准用户");
               }
               if (leixing == 2) {
                   lin.add("一般用户");
               }
               if (leixing == 3) {
                   lin.add("VIP用户");
               }
               //lin.add(rs.getInt(8)+"");
               lin.add(rs.getString(9));
               lin.add(rs.getString(10));
               lin.add(rs.getString(11));
               lin.add(rs.getString(12));
               lin.add(rs.getString(13));
               lin.add(rs.getString(14));
               lin.add(rs.getString(15));
               lin.add(rs.getString(16));
               lin.add(rs.getString(17));
               y.add(lin);
           }
           model = new DefaultTableModel(y, x);
           tableInfo.setModel(model);

       } catch (Exception ex) {
           ex.printStackTrace();
       }
   }


    //显示被删除客户的方法
    public void Client() {
        clientinfo();
    }

////--------------------------------------------------------------------------
    //-------------------------------------------------------
    //Contract

    public void ContractInfo() {
        Vector x = new Vector();
        Vector y = new Vector();
        Vector xNo2 = new Vector();
        Vector yNo2 = new Vector();
        DefaultTableModel model1;
        DefaultTableModel modelnew;
        ResultSet rsF;

        x.removeAllElements();
        y.removeAllElements();
        try {
            x.add("保单号");
            x.add("保险名称");
            x.add("投保人姓名");
            x.add("投保人身份证");
            x.add("被保人姓名");
            x.add("被保人身份证");
            x.add("与投保人关系");
            x.add("状态");
            x.add("交费方式");
            x.add("交费标准");
            x.add("起始时间");
            x.add("终止时间");
            x.add("备注");
            rsF = main.stmt.executeQuery(
                    "select * from Plicy_form where Avail=0 ");
            while (rsF.next() == true) {
                Vector use = new Vector();
                use.add(rsF.getString(1));
                use.add(rsF.getString(2));
                use.add(rsF.getString(3));
                use.add(rsF.getString(4));
                use.add(rsF.getString(5));
                use.add(rsF.getString(6));
                //use.add(rsF.getInt(7)+"");
                int getR = rsF.getInt(7);
                if (getR == 0) {
                    use.add("父(母)/子(女)");
                }
                if (getR == 1) {
                    use.add("祖父(母)/孙子(女)");
                }
                if (getR == 2) {
                    use.add("叔父(母)/侄子(女)");
                }
                if (getR == 3) {
                    use.add("兄弟姐妹");
                }
                if (getR == 4) {
                    use.add("朋友");
                }
                if (getR == 5) {
                    use.add("其他");
                }
                use.add(rsF.getString(8));
                //use.add(rsF.getInt(9)+"");
                int getType = rsF.getInt(9);
                if (getType == 0) {
                    use.add("月");
                }
                if (getType == 1) {
                    use.add("年");
                }
                if (getType == 2) {
                    use.add("10年");
                }
                if (getType == 3) {
                    use.add("20年");
                }
                use.add(rsF.getFloat(10) + "");
                use.add(rsF.getString(11));
                use.add(rsF.getString(12));
                use.add(rsF.getString(13));

                y.add(use);
            }
            model1 = new DefaultTableModel(y, x);
            tableInfo.setModel(model1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Contract() {
        ContractInfo();
    }

    /////////////////------------------------------------------------------
///////////////-------------------产品
    public void productInfo() {
        Vector Productx = new Vector();
        Vector Producty = new Vector();
        DefaultTableModel dtm;
        DefaultTableModel dtmt;

        Productx.add("产品代号");
        Productx.add("产品名称");
        Productx.add("交费方式");
        Productx.add("年龄下限");
        Productx.add("年龄上限");
        Productx.add("备注");

        String str =
                "select * from InsureInfo where Avail=0 order by InsureID";
        Producty.removeAllElements();
        try {
            ResultSet rs = main.stmt.executeQuery(str);
            while (rs.next() == true) {
                Vector v = new Vector();
                v.add(rs.getString(1) + "");
                v.add(rs.getString(2) + "");
                v.add(rs.getString(3) + "");
                v.add(rs.getString(4) + "");
                v.add(rs.getString(5) + "");
                v.add(rs.getString(6) + "");
                Producty.add(v);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dtm = new DefaultTableModel(Producty, Productx);
        tableInfo.setModel(dtm);
    }


    public void Product() {
        productInfo();
    }


///////////////-------------------产品



    /*-----==================团   队=======================-----*/
    public void teamInfo() {
        DefaultTableModel dtm;
        Vector Teamx = new Vector();
        Vector Teamy = new Vector();
        Vector my = new Vector();
        dtm = new DefaultTableModel(Teamy, Teamx);
        tableInfo.setModel(dtm);
        Teamx.add("组名");
        Teamx.add("员工代号");
        Teamx.add("职 级");
        Teamx.add("姓名");
        Teamx.add("生日");
        Teamx.add("婚姻状况");
        Teamx.add("学历");
        Teamx.add("入司前职业");
        Teamx.add("入司时间");
        Teamx.add("特长");
        Teamx.add(" 住所电话");
        Teamx.add("移动电话1");
        Teamx.add("移动电话2");
        Teamx.add("QQ号");
        Teamx.add("EMail地址");
        Teamx.add("备注");

        String str = "select * from Team_form where Avail=0 order by Team_Name";
        try {
            ResultSet rs = main.stmt.executeQuery(str);
            ResultSetMetaData rsmd = rs.getMetaData(); // 获得数据库表的原形
            int count = rsmd.getColumnCount(); //取得表中的列的数量
            while (rs.next()) {
                Vector v = new Vector(); //将表的第一行数据对象实例
                for (int i = 1; i <= count; i++) {
                    v.add(rs.getObject(i).toString()); //通过循环将第一列的值添加到行对象中去
                }
                Teamy.add(v); //将每一行的对象添加到表对象中去
            }
            my = (Vector) Teamy.clone();
            dtm = new DefaultTableModel(Teamy, Teamx);
            tableInfo.setModel(dtm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Team() {
        teamInfo();
    }

    /*-----==================团   队=======================-----*/
    //＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    private void jbInit() throws Exception {
        //找回删除的数据
        lblGo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (rdoClient.isSelected()) {
                    Client();
                }
                if (rdoContract.isSelected()) {
                    ContractInfo();
                }
                if (rdoProduct.isSelected()) {
                    Product();
                }
                if (rdoTeam.isSelected()) {
                    Team();
                }
            }
        });


        getContentPane().setLayout(null);
        this.setTitle("数据处理中心");
        rdoClient.setFont(new java.awt.Font("隶书", Font.PLAIN, 24));
        rdoClient.setForeground(Color.red);
        rdoClient.setText("找回客户信息");
        rdoClient.setBounds(new Rectangle(24, 14, 181, 44));
        rdoContract.setFont(new java.awt.Font("隶书", Font.PLAIN, 24));
        rdoContract.setForeground(Color.blue);
        rdoContract.setText("找回丢失的保单");
        rdoContract.setBounds(new Rectangle(74, 79, 222, 30));
        rdoTeam.setFont(new java.awt.Font("隶书", Font.PLAIN, 24));
        rdoTeam.setForeground(Color.blue);
        rdoTeam.setText("找回团队成员信息");
        rdoTeam.setBounds(new Rectangle(279, 17, 229, 37));
        rdoProduct.setFont(new java.awt.Font("隶书", Font.PLAIN, 24));
        rdoProduct.setText("删除的保险产品");
        rdoProduct.setBounds(new Rectangle(331, 81, 201, 33));
        lblGo.setBorder(BorderFactory.createEtchedBorder());
        lblGo.setIcon(imgComeback);
        lblGo.setText("");
        lblGo.setBounds(new Rectangle(554, 14, 134, 101));
        jScrollPane1.setBounds(new Rectangle(31, 154, 743, 420));
        this.getContentPane().add(rdoClient);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().add(rdoTeam);
        this.getContentPane().add(rdoProduct);
        this.getContentPane().add(lblGo);
        this.getContentPane().add(rdoContract);
        jScrollPane1.getViewport().add(tableInfo);
        bg.add(rdoClient);
        bg.add(rdoContract);
        bg.add(rdoProduct);
        bg.add(rdoTeam);
    }
    ImageIcon imgComeback = new ImageIcon("image\\comeback2.png");
    JRadioButton rdoClient = new JRadioButton();
    JRadioButton rdoContract = new JRadioButton();
    JRadioButton rdoTeam = new JRadioButton();
    JRadioButton rdoProduct = new JRadioButton();
    JLabel lblGo = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable tableInfo = new JTable();
    ButtonGroup bg = new ButtonGroup();
}
