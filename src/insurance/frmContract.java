package insurance;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;


public class frmContract extends JFrame {
    ImageIcon yeji = new ImageIcon("image\\yeji.png");
    ImageIcon yeji2 = new ImageIcon("image\\yeji2.png");
    BorderLayout borderLayout1 = new BorderLayout();
    JTabbedPane jTabbedPane1 = new JTabbedPane();
    JPanel pnlContract = new JPanel();
    JPanel pnlTongji = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable table1 = new JTable();
    JLabel lbl1 = new JLabel();
    JTextField txtNo = new JTextField();
    JLabel lblMoney = new JLabel();
    JLabel lblType = new JLabel();
    JLabel lblName = new JLabel();
    JComboBox cboName = new JComboBox();
    JComboBox cboType = new JComboBox();
    JTextField txtMoney = new JTextField();
    JLabel lblEnd = new JLabel();
    JLabel lblStart = new JLabel();
    JTextField txtStart = new JTextField();
    JTextField txtEnd = new JTextField();
    JLabel lblName1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel lblName2 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel lblRemark = new JLabel();
    JTextField txtName1 = new JTextField();
    JTextField txtCard1 = new JTextField();
    JTextField txtName2 = new JTextField();
    JTextField txtCard2 = new JTextField();
    JComboBox cboRelation = new JComboBox();
    JTextArea txtarea = new JTextArea();
    JButton btnAdd = new JButton();
    JButton jButton2 = new JButton();
    JButton btnDelete = new JButton();
    JButton btnFresh = new JButton();
    JLabel lblCount = new JLabel();
    JLabel lblCount2 = new JLabel();
    JLabel lblAlltxt = new JLabel();
    JLabel lblAll = new JLabel();
    JLabel lblMost = new JLabel();
    JLabel lblAvg = new JLabel();
    JLabel lblLow = new JLabel();
    JLabel lblNew = new JLabel();
    JLabel lblMoney2 = new JLabel();
    JLabel lblMoney1 = new JLabel();
    JLabel lblMoney3 = new JLabel();
    JScrollPane jScrollPane2 = new JScrollPane();
    JTable tableNew = new JTable();
    JLabel lblyeji = new JLabel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel4 = new JLabel();
    Vector x = new Vector();
    Vector y = new Vector();
    Vector xNo2 = new Vector();
    Vector yNo2 = new Vector();
    DefaultTableModel model1;
    DefaultTableModel modelnew;
    JComboBox cboZt = new JComboBox();


    public frmContract() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void newTable() { //表格更新
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
            ResultSet rsF = main.stmt.executeQuery(
                    "select * from Plicy_form where Avail=1 ");
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
            table1.setModel(model1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void shua() { //刷新方法
        txtNo.setText("");
        cboName.setSelectedIndex(0);
        cboType.setSelectedIndex(0);
        txtarea.setText("");
        txtCard1.setText("");
        txtCard2.setText("");
        txtEnd.setText("");
        txtName1.setText("");
        txtMoney.setText("");
        txtName2.setText("");
        txtStart.setText("");
        //txtZt.setText("");
        cboZt.setSelectedIndex(0);
        cboRelation.setSelectedIndex(0);
    }

    public void No2() { //第二页更新
        try { //第二面 累计保单数目
            ResultSet rsCount = main.stmt.executeQuery(
                    "select count(*) from Plicy_form where Avail=1");
            rsCount.next();
            lblCount2.setText(rsCount.getString(1));
            //累计保险金额
            ResultSet rsAll = main.stmt.executeQuery(
                    "select Pay_Stadard from Plicy_form where Avail=1");
            float All = 0;
            while (rsAll.next()) {
                All += rsAll.getFloat(1);
            }
            lblAll.setText(All + "");
            //最高保险金额
            ResultSet rsMax = main.stmt.executeQuery(
                    "select max(Pay_Stadard) from Plicy_form where Avail=1");
            rsMax.next();
            lblMoney1.setText(rsMax.getString(1));
            //最低保险金额
            ResultSet rsMin = main.stmt.executeQuery(
                    "select min(Pay_Stadard) from Plicy_form where Avail=1");
            rsMin.next();
            lblMoney2.setText(rsMin.getString(1));
            //平均保险金额
            ResultSet rsAvg = main.stmt.executeQuery(
                    "select Avg(Pay_Stadard) from Plicy_form where Avail=1");
            rsAvg.next();
            lblMoney3.setText(rsAvg.getString(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void newTable2() { //第二页表格
        xNo2.removeAllElements();
        yNo2.removeAllElements();
        xNo2.add("保单号");
        xNo2.add("保险名称");
        xNo2.add("投保人姓名");
        xNo2.add("投保人身份证");
        xNo2.add("被保人姓名");
        xNo2.add("被保人身份证");
        xNo2.add("与投保人关系");
        xNo2.add("状态");
        xNo2.add("交费方式");
        xNo2.add("交费标准");
        xNo2.add("起始时间");
        xNo2.add("终止时间");
        xNo2.add("备注");
        try {
            ResultSet rsNo2 = main.stmt.executeQuery(
                    "select top 5 * from Plicy_form where Avail=1 order by ForDate desc");
            while (rsNo2.next()) {
                Vector use = new Vector();
                use.add(rsNo2.getString(1));
                use.add(rsNo2.getString(2));
                use.add(rsNo2.getString(3));
                use.add(rsNo2.getString(4));
                use.add(rsNo2.getString(5));
                use.add(rsNo2.getString(6));
                //use.add(rsF.getInt(7)+"");
                int getR = rsNo2.getInt(7);
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
                use.add(rsNo2.getString(8));
                //use.add(rsF.getInt(9)+"");
                int getType = rsNo2.getInt(9);
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
                use.add(rsNo2.getFloat(10) + "");
                use.add(rsNo2.getString(11));
                use.add(rsNo2.getString(12));
                use.add(rsNo2.getString(13));

                yNo2.add(use);
            }
            modelnew = new DefaultTableModel(yNo2, xNo2);
            tableNew.setModel(modelnew);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //关闭自动平分
        tableNew.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //关闭自动平分
        getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        newTable2();
        this.setTitle("保单管理和业绩统计");
        pnlContract.setLayout(null);
        pnlTongji.setLayout(null);
        jScrollPane1.setBounds(new Rectangle(0, 0, 800, 260));
        lbl1.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lbl1.setForeground(Color.blue);
        lbl1.setText("保 单 号:");
        lbl1.setBounds(new Rectangle(11, 280, 80, 30));
        txtNo.setText("");
        txtNo.setBounds(new Rectangle(100, 282, 245, 27));
        lblMoney.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblMoney.setForeground(Color.blue);
        lblMoney.setText("缴费标准:");
        lblMoney.setBounds(new Rectangle(14, 414, 80, 30));
        lblType.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblType.setForeground(Color.blue);
        lblType.setText("缴费方式:");
        lblType.setBounds(new Rectangle(11, 379, 80, 30));
        lblName.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblName.setForeground(Color.blue);
        lblName.setText("保险名称：");
        lblName.setBounds(new Rectangle(9, 324, 81, 32));
        cboName.setBounds(new Rectangle(101, 330, 243, 27));
        cboType.setBounds(new Rectangle(99, 378, 121, 28));
        txtMoney.setText("");
        txtMoney.setBounds(new Rectangle(99, 416, 122, 22));
        lblEnd.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblEnd.setForeground(Color.blue);
        lblEnd.setText("终止时间：");
        lblEnd.setBounds(new Rectangle(11, 478, 80, 28));
        lblStart.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblStart.setForeground(Color.blue);
        lblStart.setText("起始时间：");
        lblStart.setBounds(new Rectangle(11, 449, 80, 28));
        txtStart.setText("");
        txtStart.setBounds(new Rectangle(99, 448, 122, 26));
        txtEnd.setText("");
        txtEnd.setBounds(new Rectangle(100, 479, 121, 28));
        lblName1.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblName1.setForeground(Color.blue);
        lblName1.setText("投 保 人 姓 名：");
        lblName1.setBounds(new Rectangle(412, 289, 136, 27));
        jLabel2.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        jLabel2.setForeground(Color.blue);
        jLabel2.setText("投保人身份证号：");
        jLabel2.setBounds(new Rectangle(419, 359, 129, 29));
        jLabel3.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        jLabel3.setForeground(Color.blue);
        jLabel3.setText("被保险人姓名 ：");
        jLabel3.setBounds(new Rectangle(419, 324, 128, 24));
        lblName2.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        lblName2.setForeground(Color.blue);
        lblName2.setText("被保险人身份证号：");
        lblName2.setBounds(new Rectangle(408, 402, 150, 26));
        jLabel5.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        jLabel5.setForeground(Color.blue);
        jLabel5.setText("与 投 保 人 关 系：");
        jLabel5.setBounds(new Rectangle(404, 447, 153, 24));
        lblRemark.setFont(new java.awt.Font("隶书", Font.PLAIN, 17));
        lblRemark.setForeground(Color.blue);
        lblRemark.setText("备      注：");
        lblRemark.setBounds(new Rectangle(420, 498, 121, 25));
        txtName1.setText("");
        txtName1.setBounds(new Rectangle(564, 285, 113, 28));
        txtCard1.setBounds(new Rectangle(565, 361, 212, 27));
        txtName2.setBounds(new Rectangle(565, 325, 113, 28));
        txtCard2.setBounds(new Rectangle(565, 403, 213, 28));
        cboRelation.setBounds(new Rectangle(567, 445, 212, 31));
        txtarea.setBorder(BorderFactory.createEtchedBorder());
        txtarea.setText("");
        txtarea.setBounds(new Rectangle(565, 487, 216, 43));
        btnAdd.setBounds(new Rectangle(283, 380, 71, 30));
        btnAdd.setFont(new java.awt.Font("隶书", Font.PLAIN, 18));
        btnAdd.setText("增加");
        jButton2.setBounds(new Rectangle(281, 454, 70, 34));
        jButton2.setFont(new java.awt.Font("隶书", Font.PLAIN, 18));
        jButton2.setText("修改");
        btnDelete.setBounds(new Rectangle(281, 494, 72, 31));
        btnDelete.setFont(new java.awt.Font("隶书", Font.PLAIN, 18));
        btnDelete.setText("删除");
        btnFresh.setBounds(new Rectangle(282, 416, 71, 30));
        btnFresh.setFont(new java.awt.Font("隶书", Font.PLAIN, 18));
        btnFresh.setText("刷新");
        lblCount.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblCount.setForeground(Color.magenta);
        lblCount.setText("累计报单件数：");
        lblCount.setBounds(new Rectangle(8, 16, 148, 35));
        lblCount2.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblCount2.setForeground(Color.magenta);
        lblCount2.setBorder(BorderFactory.createEtchedBorder());
        lblCount2.setText("");
        lblCount2.setBounds(new Rectangle(166, 17, 120, 31));
        lblAlltxt.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblAlltxt.setForeground(Color.magenta);
        lblAlltxt.setText("累计保险金额：");
        lblAlltxt.setBounds(new Rectangle(315, 13, 148, 35));
        lblAll.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblAll.setForeground(Color.magenta);
        lblAll.setBorder(BorderFactory.createEtchedBorder());
        lblAll.setBounds(new Rectangle(476, 16, 120, 31));
        lblMost.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblMost.setForeground(Color.magenta);
        lblMost.setText("最高保额：");
        lblMost.setBounds(new Rectangle(11, 74, 101, 35));
        lblAvg.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblAvg.setForeground(Color.magenta);
        lblAvg.setText("平均保额：");
        lblAvg.setBounds(new Rectangle(260, 74, 100, 35));
        lblLow.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblLow.setForeground(Color.magenta);
        lblLow.setText("最低保额：");
        lblLow.setBounds(new Rectangle(552, 75, 105, 32));
        lblNew.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblNew.setForeground(Color.blue);

        lblNew.setText("新 单 业 绩：");
        lblNew.setBounds(new Rectangle(34, 128, 145, 32));
        lblMoney2.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblMoney2.setForeground(Color.magenta);
        lblMoney2.setBorder(BorderFactory.createEtchedBorder());
        lblMoney2.setText("");
        lblMoney2.setBounds(new Rectangle(665, 78, 120, 31));
        lblMoney1.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblMoney1.setForeground(Color.magenta);
        lblMoney1.setBorder(BorderFactory.createEtchedBorder());
        lblMoney1.setText("");
        lblMoney1.setBounds(new Rectangle(121, 77, 120, 31));
        lblMoney3.setFont(new java.awt.Font("隶书", Font.PLAIN, 20));
        lblMoney3.setForeground(Color.magenta);
        lblMoney3.setBorder(BorderFactory.createEtchedBorder());
        lblMoney3.setText("");
        lblMoney3.setBounds(new Rectangle(360, 76, 152, 31));
        jScrollPane2.setBounds(new Rectangle(0, 188, 795, 205));
        lblyeji.setBorder(null);
        lblyeji.setIcon(yeji);
        lblyeji.setLabelFor(pnlTongji);
        lblyeji.setText("");
        lblyeji.setBounds(new Rectangle(151, 405, 184, 130));
        jLabel1.setIcon(yeji2);
        jLabel1.setLabelFor(pnlTongji);
        jLabel1.setBounds(new Rectangle(419, 401, 184, 130));
        jLabel4.setFont(new java.awt.Font("隶书", Font.PLAIN, 16));
        jLabel4.setForeground(Color.blue);
        jLabel4.setText("状    态：");
        jLabel4.setBounds(new Rectangle(12, 513, 80, 28));
        cboZt.setBounds(new Rectangle(100, 513, 122, 26));
        pnlContract.add(jScrollPane1);
        pnlContract.add(txtNo);
        pnlContract.add(lbl1);
        pnlContract.add(lblName);
        pnlContract.add(cboName);
        pnlContract.add(lblType);
        pnlContract.add(cboType);
        pnlContract.add(lblName2);
        pnlContract.add(jLabel5);
        pnlContract.add(cboRelation);
        pnlContract.add(txtCard2);
        pnlContract.add(txtarea);
        pnlContract.add(txtCard1);
        pnlContract.add(txtName2);
        pnlContract.add(txtName1);
        pnlContract.add(jLabel3);
        pnlContract.add(lblName1);
        pnlContract.add(jLabel2);
        pnlContract.add(lblMoney);
        pnlContract.add(txtMoney);
        pnlContract.add(lblStart);
        pnlContract.add(txtStart);
        pnlContract.add(txtEnd);
        pnlContract.add(jLabel4);
        pnlContract.add(lblEnd);
        pnlContract.add(btnDelete);
        pnlContract.add(jButton2);
        pnlContract.add(btnFresh);
        pnlContract.add(btnAdd);
        pnlContract.add(lblRemark);
        pnlContract.add(cboZt);
        jScrollPane1.getViewport().add(table1);

        pnlTongji.add(lblAlltxt);
        pnlTongji.add(lblAll);
        pnlTongji.add(lblCount2);
        pnlTongji.add(lblMost);
        pnlTongji.add(lblCount);
        pnlTongji.add(lblMoney1);
        pnlTongji.add(lblLow);
        pnlTongji.add(lblMoney2);
        pnlTongji.add(lblNew);
        pnlTongji.add(jScrollPane2);
        pnlTongji.add(lblyeji);
        pnlTongji.add(jLabel1);
        pnlTongji.add(lblAvg);
        pnlTongji.add(lblMoney3);
        jScrollPane2.getViewport().add(tableNew);

        jTabbedPane1.add(pnlTongji, " ※业  绩  统  计 ");
        this.getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.add(pnlContract, "@保单信息管理");
        try { //第二面 累计保单数目
            ResultSet rsCount = main.stmt.executeQuery(
                    "select count(*) from Plicy_form where Avail=1");
            rsCount.next();
            lblCount2.setText(rsCount.getString(1));
            //累计保险金额
            ResultSet rsAll = main.stmt.executeQuery(
                    "select Pay_Stadard from Plicy_form where Avail=1");
            float All = 0;
            while (rsAll.next()) {
                All += rsAll.getFloat(1);
            }
            lblAll.setText(All + "");
            //最高保险金额
            ResultSet rsMax = main.stmt.executeQuery(
                    "select max(Pay_Stadard) from Plicy_form where Avail=1");
            rsMax.next();
            lblMoney1.setText(rsMax.getString(1));
            //最低保险金额
            ResultSet rsMin = main.stmt.executeQuery(
                    "select min(Pay_Stadard) from Plicy_form where Avail=1");
            rsMin.next();
            lblMoney2.setText(rsMin.getString(1));
            //平均保险金额
            ResultSet rsAvg = main.stmt.executeQuery(
                    "select Avg(Pay_Stadard) from Plicy_form where Avail=1");
            rsAvg.next();
            lblMoney3.setText(rsAvg.getString(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            ResultSet rsF = main.stmt.executeQuery(
                    "select * from Plicy_form where Avail=1 ");
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
            table1.setModel(model1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        cboName.addItem("太平如意卡");
        cboName.addItem("太平吉祥卡");
        cboName.addItem("太平顺意卡");
        cboName.addItem("个人住院补贴");
        cboName.addItem("个人住院费用");
        cboName.addItem("附加个人意外伤害医疗");
        cboName.addItem("附加住院医疗");
        cboName.addItem("附加住院医疗补贴");
        cboName.addItem("个人人身意外伤害_98");
        cboName.addItem("附加老年护理费");
        cboName.addItem("附加婚嫁金");
        cboName.addItem("附加终身住院补贴医疗");

        cboType.addItem("月");
        cboType.addItem("年");
        cboType.addItem("10年");
        cboType.addItem("20年");

        cboRelation.addItem("父(母)/子(女)");
        cboRelation.addItem("祖父(母)/孙子(女)");
        cboRelation.addItem("叔父(母)/侄子(女)");
        cboRelation.addItem("兄弟姐妹");
        cboRelation.addItem("朋友");
        cboRelation.addItem("其他");

        cboZt.addItem("有效");
        cboZt.addItem("无效");
        btnAdd.addActionListener(new ActionListener() { //增加按钮
            public void actionPerformed(ActionEvent e) {
                if (txtNo.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "保单号必须输入！");
                    return;
                }
                if (txtNo.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "保单号长度不能超过20字节！");
                    return;
                }
                if (txtName1.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "投保人姓名必须输入！");
                    return;
                }
                if (txtName1.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "投保人姓名不能超过20字节！");
                    return;
                }
                if (txtName2.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "被保人姓名必须输入！");
                    return;
                }
                if (txtName2.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "被保人姓名不能超过20字节！");
                    return;
                }
                if (txtMoney.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "交费标准必须输入！");
                    return;
                }
                String get = txtMoney.getText();
                if (get.matches("[0-9]*") == false) {
                    JOptionPane.showMessageDialog(null, "交费标准必须为整数！");
                    return;
                }
                if (txtStart.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "起始时间必须输入！");
                    return;
                }
                if (txtStart.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "起始时间不能超过20字节！");
                    return;
                }
                if (txtEnd.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "终止时间必须输入！");
                    return;
                }
                if (txtEnd.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "终止时间不能超过20字节！");
                    return;
                }
//                if(txtZt.getText().length()==0)
//                {
//                    JOptionPane.showMessageDialog(null,"状态必须输入！");
//                    return;
//                }
//                if(txtZt.getText().length()>20)
//                {
//                    JOptionPane.showMessageDialog(null,"状态不能超过20字节！");
//                    return;
//                }
                if (txtCard2.getText().length() != 0) {
                    String Card = txtCard2.getText();
                    if (Card.matches("[0-9]*") == false ||
                        txtCard2.getText().length() != 18) {
                        JOptionPane.showMessageDialog(null, "被保人身分证号不合法！");
                        return;
                    }
                }
                if (txtCard1.getText().length() != 0) {
                    String Card = txtCard1.getText();
                    if (Card.matches("[0-9]*") == false ||
                        txtCard1.getText().length() != 18) {
                        JOptionPane.showMessageDialog(null, "投保人身分证号不合法！");
                        return;
                    }
                }
                try {
                    ResultSet rs = main.stmt.executeQuery(
                            "select * from Plicy_form where Plicy_No='" +
                            txtNo.getText() + "' and Avail=1");
                    if (rs.next() == true) {
                        JOptionPane.showMessageDialog(null, "已经存在该保单！");
                        return;
                    }
                    main.stmt.execute("insert into Plicy_form  values('" +
                                      txtNo.getText() + "','" +
                                      cboName.getSelectedItem() + "','" +
                                      txtName1.getText() + "','" +
                                      txtCard1.getText() + "','" +
                                      txtName2.getText() + "','" +
                                      txtCard2.getText() + "'," +
                                      cboRelation.getSelectedIndex() + ",'" +
                                      cboZt.getSelectedItem() + "'," +
                                      cboType.getSelectedIndex() + "," +
                                      txtMoney.getText() + ",'" +
                                      txtStart.getText() + "','" +
                                      txtEnd.getText() + "','" +
                                      txtarea.getText() + "',1)");
                    JOptionPane.showMessageDialog(null, "增加成功！");
                    newTable(); //函数调用
                    shua(); //调用刷新函数
                    No2(); //调用No2方法
                    newTable2(); //调用方法 用于更新第二页表格
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() { //删除按钮
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtNo.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "保单号必须输入！");
                        return;
                    }
                    ResultSet rs = main.stmt.executeQuery(
                            "select * from Plicy_form where Plicy_No='" +
                            txtNo.getText() + "'and Avail=1");
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "不存在该保单！");
                        return;
                    }
                    int yn = JOptionPane.showConfirmDialog(null, "是否真的要删除该数据？",
                            "温馨提示", 2);
                    if (yn == 0) { /////////////////////////////////////////////////漏洞发现
                        main.stmt.execute(
                                "update Plicy_form set Avail=0 where Plicy_No ='" +
                                txtNo.getText() + "'");
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        newTable(); //调用函数
                        shua(); //调用刷新函数
                        No2(); //调用No2方法
                        newTable2(); //调用方法 用于更新第二页表格
                    } else {
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        jButton2.addActionListener(new ActionListener() { //修改按钮
            public void actionPerformed(ActionEvent e) {
                if (txtNo.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "保单号必须输入！");
                    return;
                }
                if (txtNo.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "保单号长度不能超过20字节！");
                    return;
                }
                try {
                    ResultSet rs = main.stmt.executeQuery(
                            "select * from Plicy_form where Plicy_No='" +
                            txtNo.getText() + "'and Avail=1");
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "不经存在该保单！");
                        return;
                    }
                    if (txtName1.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "投保人姓名必须输入！");
                        return;
                    }
                    if (txtName1.getText().length() > 20) {
                        JOptionPane.showMessageDialog(null, "投保人姓名不能超过20字节！");
                        return;
                    }
                    if (txtName2.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "被保人姓名必须输入！");
                        return;
                    }
                    if (txtName2.getText().length() > 20) {
                        JOptionPane.showMessageDialog(null, "被保人姓名不能超过20字节！");
                        return;
                    }
                    if (txtMoney.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "交费标准必须输入！");
                        return;
                    }
                    String get = txtMoney.getText();
                    if (get.matches("[0-9]*") == false) {
                        JOptionPane.showMessageDialog(null, "交费标准必须为整数！");
                        return;
                    }
                    if (txtStart.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "起始时间必须输入！");
                        return;
                    }
                    if (txtStart.getText().length() > 20) {
                        JOptionPane.showMessageDialog(null, "起始时间不能超过20字节！");
                        return;
                    }
                    if (txtEnd.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "终止时间必须输入！");
                        return;
                    }
                    if (txtEnd.getText().length() > 20) {
                        JOptionPane.showMessageDialog(null, "终止时间不能超过20字节！");
                        return;
                    }
//                if(txtZt.getText().length()==0)
//                {
//                    JOptionPane.showMessageDialog(null,"状态必须输入！");
//                    return;
//                }
//                if(txtZt.getText().length()>20)
//                {
//                    JOptionPane.showMessageDialog(null,"状态不能超过20字节！");
//                    return;
//                }
                    if (txtCard2.getText().length() != 0) {
                        String Card = txtCard2.getText();
                        if (Card.matches("[0-9]*") == false ||
                            txtCard2.getText().length() != 18) {
                            JOptionPane.showMessageDialog(null, "被保人身分证号不合法！");
                            return;
                        }
                    }
                    if (txtCard1.getText().length() != 0) {
                        String Card = txtCard1.getText();
                        if (Card.matches("[0-9]*") == false ||
                            txtCard1.getText().length() != 18) {
                            JOptionPane.showMessageDialog(null, "投保人身分证号不合法！");
                            return;
                        }
                    }

                    int yn = JOptionPane.showConfirmDialog(null, "是否真的要修改该数据？",
                            "温馨提示", 2);
                    if (yn == 0) {
                        main.stmt.execute("update Plicy_form set Plicy_No='" +
                                          txtNo.getText() + "',Plicy_Name='" +
                                          cboName.getSelectedItem() +
                                          "',Holder_Name='" + txtName1.getText() +
                                          "',HCard_Id='" + txtCard1.getText() +
                                          "',Insurant_Name='" +
                                          txtName2.getText() + "',ICard_Id='" +
                                          txtCard2.getText() + "',Relation=" +
                                          cboRelation.getSelectedIndex() +
                                          ",State='" + cboZt.getSelectedItem() +
                                          "',Pay_Type=" +
                                          cboType.getSelectedIndex() +
                                          ",Pay_Stadard=" + txtMoney.getText() +
                                          ",Start_Time='" + txtStart.getText() +
                                          "',End_Time='" + txtEnd.getText() +
                                          "',Remark='" + txtarea.getText() +
                                          "' where Plicy_No='" + txtNo.getText() +
                                          "'");
                        JOptionPane.showMessageDialog(null, "修改成功！");
                        newTable(); //调用函数
                        shua(); //调用刷新函数
                        No2(); //调用No2方法
                        newTable2(); //调用方法 用于更新第二页表格
                    } else {
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnFresh.addActionListener(new ActionListener() { //刷新按钮
            public void actionPerformed(ActionEvent e) {
                txtNo.setText("");
                cboName.setSelectedIndex(0);
                cboType.setSelectedIndex(0);
                txtarea.setText("");
                txtCard1.setText("");
                txtCard2.setText("");
                txtEnd.setText("");
                txtName1.setText("");
                txtMoney.setText("");
                txtName2.setText("");
                txtStart.setText("");
                //txtZt.setText("");
                cboZt.setSelectedIndex(0);
                cboRelation.setSelectedIndex(0);
                No2(); //调用No2方法
                newTable2(); //调用方法 用于更新第二页表格
            }
        });
        table1.addMouseListener(new MouseAdapter() { //表格单击
            public void mousePressed(MouseEvent e) {
                try {
                    ResultSet rsTable = main.stmt.executeQuery(
                            "select * from Plicy_form where Avail=1");
                    for (int i = 0; i <= table1.getSelectedRow(); i++) {
                        rsTable.next();
                    }
                    txtNo.setText(rsTable.getString(1));
                    cboName.setSelectedItem(rsTable.getString(2));
                    txtName1.setText(rsTable.getString(3));
                    txtCard1.setText(rsTable.getString(4));
                    txtName2.setText(rsTable.getString(5));
                    txtCard2.setText(rsTable.getString(6));
                    cboRelation.setSelectedIndex(rsTable.getInt(7));
                    //txtZt.setText(rsTable.getString(8));
                    cboZt.setSelectedItem(rsTable.getString(8));
                    cboType.setSelectedIndex(rsTable.getInt(9));
                    txtMoney.setText(rsTable.getFloat(10) + "");
                    txtStart.setText(rsTable.getString(11));
                    txtEnd.setText(rsTable.getString(12));
                    txtarea.setText(rsTable.getString(13));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
