package insurance;

import java.awt.*;

import javax.swing.*;
import javax.swing.JTable;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;
import java.awt.FileDialog;


public class frmTeam extends JFrame {
    public frmTeam() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //刷新的方法
    public void fresh() {
        txtTeam.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtBirth.setText("");
        txtCode.setText("");
        txtJoin.setText("");
        txtHome.setText("");
        txtQQ.setText("");
        txtPhone1.setText("");
        txtPhone2.setText("");
        txtAreaRe.setText("");

        cmbGrade.setSelectedIndex(0);
        cmbEducation.setSelectedIndex(0);
        cmbMarriage.setSelectedIndex(0);
        cmbBefore.setSelectedIndex(0);
        cmbStrong.setSelectedIndex(0);
        ImageIcon imgPic = new ImageIcon("image\\NoPicture.png");
        lblTou.setIcon(imgPic);
    }

    public void tableDemo1() {
        //************表  格  模  块**************//
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
    }

    public void tableDemo2() {
        //Teamx.removeAllElements();
        Teamy.removeAllElements();
        String str = "select * from Team_form where Avail=1 order by Team_Name";
        try {
            ResultSet rs = main.stmt.executeQuery(str);
            Teamy.removeAllElements(); //清空前面的数据
            ResultSetMetaData rsmd = rs.getMetaData(); // 获得数据库表的原形
            int count = rsmd.getColumnCount(); //取得表中的列的数量
            System.out.println("count:" + count);
//            for (int i = 1; i <=count; i++) {
//                Teamx.add(rsmd.getCatalogName(i));             //产生一个行对象
//            }
//
            System.out.println(str);
            while (rs.next()) {
                Vector v = new Vector(); //将表的第一行数据对象实例
                for (int i = 1; i <= count; i++) {
                    v.add(rs.getObject(i).toString()); //通过循环将第一列的值添加到行对象中去
                }
                Teamy.add(v); //将每一行的对象添加到表对象中去
            }
            my = (Vector) Teamy.clone();
            System.out.println("============" + my.get(0).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //显示头像的方法
    public void picture() {
        String picture =
                "select Team_Img from Team_form where Colleague_Name='" +
                txtName.getText() + "'";

        try {
            ResultSet rs = main.stmt.executeQuery(picture);
            if (rs.next()) {
                dir = rs.getString("Team_Img");
            }
            System.out.println(dir);
            ImageIcon imgPic = new ImageIcon(dir);
            lblTou.setIcon(imgPic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //上传头像
        btnUpPic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fd.setMode(FileDialog.LOAD);
                fd.show();
                dir = fd.getDirectory() + fd.getFile();
                ImageIcon imgPic = new ImageIcon(dir);
                lblTou.setIcon(imgPic);
                String dirPic = "update Team_form set Team_Img='" + dir +
                                "' where Colleague_Name='" + txtName.getText() +
                                "'";
                try {
                    main.stmt.execute(dirPic);
                    JOptionPane.showMessageDialog(null, "上传成功！");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(dirPic);
            }
        });

        //*********************调用方法，设置表格属性*************//
        tableDemo1();
        tableDemo2();
        //*********************调用方法，设置表格属性*************//

        /*刷新按钮事件*/
        btnFresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fresh(); //调用清空控件的方法
                tableDemo2(); //再次调用查询
            }
        });

        //增加团队成员
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String strsql = "insert into Team_form values ('" +
                                txtTeam.getText() + "','" + txtCode.getText() +
                                "','" + cmbGrade.getSelectedItem() + "','" +
                                txtName.getText() + "','" + txtBirth.getText() +
                                "','" + cmbMarriage.getSelectedItem() +
                                "','" + cmbEducation.getSelectedItem() + "','" +
                                cmbBefore.getSelectedItem() + "','" +
                                txtJoin.getText() + "','" +
                                cmbStrong.getSelectedItem() + "','" +
                                txtHome.getText() + "','" + txtPhone1.getText() +
                                "','" + txtPhone2.getText() + "','" +
                                txtQQ.getText() + "','" + txtEmail.getText() +
                                "','','" + txtAreaRe.getText() + "',1)";
                try {
                    if (txtName.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "姓名不能为空，请重新输入");
                    } else {
                        main.stmt.execute(strsql);
                        JOptionPane.showMessageDialog(null, "添加新同事成功", " 添加成功",
                                1);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                fresh(); //调用清空控件的方法
                tableDemo2(); //再次调用查询
            }
        });

        //查询按钮事件
        btnQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String code = txtCode.getText();
                String strsql =
                        "select * from Team_form where Colleague_Name like '%" +
                        name + "%' or Code_Name like '%" +
                        code + "%' and Avail = 1";
                System.out.println(strsql);
                try {
                    ResultSet rs = main.stmt.executeQuery(strsql);
//                    ResultSetMetaData rsmd = rs.getMetaData(); // 获得数据库表的原形
//                    int count = rsmd.getColumnCount();
                    if (rs.next()) {
//                        Vector v = new Vector(); //将表的第一行数据对象实例
//                        for (int i = 1; i <= count; i++) {
//                            v.add(rs.getObject(i).toString()); //通过循环将第一列的值添加到行对象中去
//                        }
//                        Teamy.add(v);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "不存在这位同事，请重新审核查询条件或者添加同事");
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //删除按钮的事件（更新）
        btnShang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String strsql =
                        "update Team_form  set Avail=0 where Avail = 1";

            }
        });
        //保存修改
        btnSaveAmend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String strsql = "update Team_form set Team_Name='" +
                                txtTeam.getText() + "', Code_Name='" +
                                txtCode.getText() +
                                "',Grade_Type='" + cmbGrade.getSelectedItem() +
                                "',Colleague_Name='" +
                                txtName.getText() + "',Birth='" +
                                txtBirth.getText() +
                                "',Marriage='" + cmbMarriage.getSelectedItem() +
                                "',Education='" + cmbEducation.getSelectedItem() +
                                "',Before_occup='" +
                                cmbBefore.getSelectedItem() + "',Join_Time='" +
                                txtJoin.getText() + "',Strong='" +
                                cmbStrong.getSelectedItem() + "',Home_No='" +
                                txtHome.getText() + "',Tele_No1='" +
                                txtPhone1.getText() +
                                "',Tele_No1='" + txtPhone2.getText() +
                                "',QQ_No='" +
                                txtQQ.getText() + "',E_mail='" +
                                txtEmail.getText() +
                                "','',Remark='" + txtAreaRe.getText() +
                                "',Avail=1 where Colleague_Name='" +
                                txtName.getText() + "'";
                System.out.println(strsql);
                try {
                    main.stmt.execute(strsql);
                    JOptionPane.showMessageDialog(null, "保存修改成功");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("团队管理－我们是最优秀的");
        tableTeamInfo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tableTeamInfo_mouseClicked(e);
            }
        });
        fd.setTitle("请选择图片");
        this.getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(new Rectangle(0, 0, 800, 600));
        jTabbedPane1.add(pnlFamlily,
                         "\u6211\u4eec\u7684\u56e2\u961f\u5927\u5bb6\u5ead");
        lblFamily.setBorder(BorderFactory.createEtchedBorder());
        lblFamily.setText("");
        lblFamily.setBounds(new Rectangle(0, 0, 651, 502));
        btnHuo.setFont(new java.awt.Font("隶书", Font.PLAIN, 22));
        btnHuo.setForeground(Color.BLUE);
        btnHuo.setBounds(new Rectangle(648, 0, 148, 34));
        btnHuo.setText("闪亮的日子");
        jTabbedPane1.add(pnlInfo, "详细信息");
        lblBefor.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblBefor.setForeground(SystemColor.activeCaption);
        lblBefor.setText("入司前职业");
        lblBefor.setBounds(new Rectangle(214, 42, 96, 29));
        cmbBefore.addItem("");
        cmbBefore.addItem("公司职员");
        cmbBefore.addItem("公务员");
        cmbBefore.addItem("个体户");
        cmbBefore.addItem("农业/工业");
        cmbBefore.addItem("教育/培训");
        cmbBefore.addItem("其 它");
        lblJoin.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblJoin.setForeground(SystemColor.activeCaption);
        lblJoin.setText("入司时间");
        lblJoin.setBounds(new Rectangle(224, 77, 73, 29));
        lblName.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblName.setForeground(SystemColor.activeCaption);
        lblName.setText("姓   名");
        lblName.setBounds(new Rectangle(0, 41, 68, 29));
        lblGrade.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblGrade.setForeground(SystemColor.activeCaption);
        lblGrade.setText("职   级");
        lblGrade.setBounds(new Rectangle(0, 75, 68, 29));
        cmbGrade.addItem("");
        cmbGrade.addItem("业 务 员");
        cmbGrade.addItem("分公司经理");
        cmbGrade.addItem("业务经理");
        cmbGrade.addItem("培训处长");
        cmbGrade.addItem("产品策划");
        cmbGrade.addItem("高级讲师");
        cmbGrade.addItem("实习生");
        lblCode.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblCode.setForeground(SystemColor.activeCaption);
        lblCode.setText("员工代号 ");
        lblCode.setBounds(new Rectangle(0, 110, 81, 29));
        lblMarriage.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblMarriage.setForeground(SystemColor.activeCaption);
        lblMarriage.setText("婚姻情况");
        lblMarriage.setBounds(new Rectangle(222, 4, 73, 29));
        cmbMarriage.addItem("");
        cmbMarriage.addItem("未婚");
        cmbMarriage.addItem("已婚");
        cmbMarriage.addItem("丧夫");
        cmbMarriage.addItem("丧妻");
        lblBirth.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblBirth.setForeground(SystemColor.activeCaption);
        lblBirth.setText("出生日期");
        lblBirth.setBounds(new Rectangle(0, 148, 73, 29));
        lblEducation.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblEducation.setForeground(SystemColor.activeCaption);
        lblEducation.setText("教育程度");
        lblEducation.setBounds(new Rectangle(4, 187, 75, 29));
        cmbEducation.setBounds(new Rectangle(81, 191, 115, 27));
        cmbEducation.addItem("");
        cmbEducation.addItem("大 学");
        cmbEducation.addItem("高 中");
        cmbEducation.addItem("初 中");
        cmbEducation.addItem("研究生以上");
        cmbEducation.addItem("其 它");
        lblStrong.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblStrong.setForeground(SystemColor.activeCaption);
        lblStrong.setText("特   长");
        lblStrong.setBounds(new Rectangle(228, 112, 68, 29));
        cmbStrong.addItem("");
        cmbStrong.addItem("器乐");
        cmbStrong.addItem("篮球/足球");
        cmbStrong.addItem("舞蹈");
        cmbStrong.addItem("书画");
        cmbStrong.addItem("其  它");
        lblTeam.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblTeam.setForeground(SystemColor.activeCaption);
        lblTeam.setText("分组名称");
        lblTeam.setBounds(new Rectangle(0, 5, 72, 29));
        txtTeam.setBorder(BorderFactory.createEtchedBorder());
        txtTeam.setText("");
        txtTeam.setBounds(new Rectangle(77, 6, 114, 28));
        txtName.setBounds(new Rectangle(79, 41, 114, 28));
        txtCode.setBounds(new Rectangle(80, 111, 116, 30));
        txtJoin.setBounds(new Rectangle(324, 76, 114, 30));
        txtBirth.setBounds(new Rectangle(81, 151, 114, 30));
        cmbStrong.setBounds(new Rectangle(326, 113, 115, 27));
        cmbGrade.setBounds(new Rectangle(79, 77, 115, 27));
        cmbBefore.setBounds(new Rectangle(322, 40, 115, 27));
        cmbMarriage.setBounds(new Rectangle(319, 5, 115, 27));
        txtPhone1.setText("");
        txtPhone1.setBounds(new Rectangle(547, 44, 111, 30));
        txtEmail.setText("");
        txtEmail.setBounds(new Rectangle(469, 182, 196, 36));
        lblRemark.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblRemark.setForeground(SystemColor.activeCaption);
        lblRemark.setText("备   注");
        lblRemark.setBounds(new Rectangle(231, 146, 68, 29));
        txtAreaRe.setBorder(BorderFactory.createLoweredBevelBorder());
        txtAreaRe.setText("");
        txtAreaRe.setBounds(new Rectangle(235, 174, 214, 46));
        lblHomeNo.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblHomeNo.setForeground(SystemColor.activeCaption);
        lblHomeNo.setText("住所电话");
        lblHomeNo.setBounds(new Rectangle(455, 5, 73, 29));
        lblPhone2.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblPhone2.setForeground(SystemColor.activeCaption);
        lblPhone2.setText("移动电话2");
        lblPhone2.setBounds(new Rectangle(458, 79, 84, 29));
        txtPhone2.setText("");
        txtPhone2.setBounds(new Rectangle(548, 83, 109, 30));
        lblPhone1.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblPhone1.setForeground(SystemColor.activeCaption);
        lblPhone1.setText("移动电话1");
        lblPhone1.setBounds(new Rectangle(457, 41, 85, 29));
        lblQQ.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblQQ.setForeground(SystemColor.activeCaption);
        lblQQ.setText("QQ  号");
        lblQQ.setBounds(new Rectangle(466, 119, 70, 29));
        txtQQ.setText("");
        txtQQ.setBounds(new Rectangle(547, 120, 110, 30));
        btnUpPic.setText("上传头像");
        btnUpPic.setBounds(new Rectangle(678, 150, 90, 30));
        txtHome.setText("");
        txtHome.setBounds(new Rectangle(546, 3, 111, 30));
        lblEmail.setFont(new java.awt.Font("楷体_GB2312", Font.PLAIN, 18));
        lblEmail.setForeground(SystemColor.activeCaption);
        lblEmail.setText("电子邮件地址");
        lblEmail.setBounds(new Rectangle(476, 155, 115, 27));
        btnAdd.setBounds(new Rectangle(500, 228, 73, 30));
        btnAdd.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 18));
        btnAdd.setForeground(Color.BLUE);
        btnAdd.setText("增加");

        btnSaveAmend.setBounds(new Rectangle(620, 228, 110, 30));
        btnSaveAmend.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 18));
        btnSaveAmend.setForeground(Color.BLUE);
        btnSaveAmend.setText("保存修改");

        btnQuery.setBounds(new Rectangle(195, 228, 73, 30));
        btnQuery.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 18));
        btnQuery.setForeground(Color.BLUE);
        btnQuery.setText("查询");
        btnShang.setBounds(new Rectangle(350, 228, 73, 30));
        btnShang.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 18));
        btnShang.setForeground(Color.blue);
        btnShang.setText("删除");
        btnFresh.setBounds(new Rectangle(44, 228, 73, 30));
        btnFresh.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 18));
        btnFresh.setForeground(Color.blue);
        btnFresh.setText("刷新");
        lblTou.setEnabled(true);
        lblTou.setBorder(BorderFactory.createLineBorder(Color.black));
        lblTou.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION);
        lblTou.setDisabledIcon(null);
        lblTou.setLabelFor(this);
        lblTou.setText("");
        lblTou.setBounds(new Rectangle(675, 13, 103, 130));
        pnlInfo.setEnabled(false);
        pnlInfo.setLayout(null);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.
                                                  HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.
                                                VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane1.setBounds(new Rectangle(0, 266, 789, 275));
        pnlFamlily.setLayout(null);
        pnlFamlily.add(lblFamily);
        pnlFamlily.add(btnHuo);
        pnlInfo.add(lblTou);
        pnlInfo.add(lblCode);
        pnlInfo.add(lblBirth);
        pnlInfo.add(lblEducation);
        pnlInfo.add(cmbMarriage);
        pnlInfo.add(cmbBefore);
        pnlInfo.add(txtJoin);
        pnlInfo.add(cmbStrong);
        pnlInfo.add(lblTeam);
        pnlInfo.add(lblName);
        pnlInfo.add(lblGrade);
        pnlInfo.add(txtTeam);
        pnlInfo.add(txtName);
        pnlInfo.add(txtCode);
        pnlInfo.add(cmbGrade);
        pnlInfo.add(txtBirth);
        pnlInfo.add(lblMarriage);
        pnlInfo.add(lblBefor);
        pnlInfo.add(lblJoin);
        pnlInfo.add(lblStrong);
        pnlInfo.add(lblRemark);
        pnlInfo.add(txtAreaRe);
        pnlInfo.add(lblHomeNo);
        pnlInfo.add(lblPhone1);
        pnlInfo.add(lblPhone2);
        pnlInfo.add(lblEmail);
        pnlInfo.add(txtEmail);
        pnlInfo.add(txtHome);
        pnlInfo.add(txtPhone1);
        pnlInfo.add(txtPhone2);
        pnlInfo.add(btnFresh);
        pnlInfo.add(btnShang);
        pnlInfo.add(btnAdd);
        pnlInfo.add(btnQuery);
        pnlInfo.add(lblQQ);
        pnlInfo.add(txtQQ);
        pnlInfo.add(cmbEducation);
        pnlInfo.add(jScrollPane1);
        pnlInfo.add(btnUpPic);
        pnlInfo.add(btnSaveAmend);
        ImageIcon imgfamlily = new ImageIcon("image\\Team.jpg");
        lblFamily.setIcon(imgfamlily);
        ImageIcon imgPic = new ImageIcon("image\\NoPicture.png");
        lblTou.setIcon(imgPic);
        jScrollPane1.getViewport().add(tableTeamInfo);
        tableTeamInfo.setAutoResizeMode(tableTeamInfo.AUTO_RESIZE_OFF); //关闭自动平分
        dtm = new DefaultTableModel(Teamy, Teamx);
        tableTeamInfo.setModel(dtm);
    }

    String dir; //头像的路径


    JButton btnSaveAmend = new JButton();
    JButton btnUpPic = new JButton();
    JComboBox cmbEducation = new JComboBox();
    JComboBox cmbBefore = new JComboBox();
    JComboBox cmbMarriage = new JComboBox();
    JComboBox cmbGrade = new JComboBox();
    JComboBox cmbStrong = new JComboBox();
    JTabbedPane jTabbedPane1 = new JTabbedPane();
    JLabel lblFamily = new JLabel();
    JPanel pnlFamlily = new JPanel();
    JButton btnHuo = new JButton();
    JPanel pnlInfo = new JPanel();
    JLabel lblBefor = new JLabel();
    JLabel lblJoin = new JLabel();
    JLabel lblName = new JLabel();
    JLabel lblGrade = new JLabel();
    JLabel lblCode = new JLabel();
    JLabel lblMarriage = new JLabel();
    JLabel lblBirth = new JLabel();
    JLabel lblEducation = new JLabel();
    JLabel lblStrong = new JLabel();
    JLabel lblTeam = new JLabel();
    JTextField txtTeam = new JTextField();
    JTextField txtName = new JTextField();
    JTextField txtCode = new JTextField();
    JTextField txtJoin = new JTextField();
    JTextField txtBirth = new JTextField();
    TitledBorder titledBorder1 = new TitledBorder("");
    JTextField txtPhone1 = new JTextField();
    JTextField txtEmail = new JTextField();
    JLabel lblRemark = new JLabel();
    JTextArea txtAreaRe = new JTextArea();
    JLabel lblHomeNo = new JLabel();
    JLabel lblPhone2 = new JLabel();
    JLabel lblPhone1 = new JLabel();
    JLabel lblQQ = new JLabel();
    JTextField txtHome = new JTextField();
    JTextField txtPhone2 = new JTextField();
    JLabel lblEmail = new JLabel();
    JTextField txtQQ = new JTextField();
    JButton btnAdd = new JButton();
    JButton btnQuery = new JButton();
    JButton btnShang = new JButton();
    JButton btnFresh = new JButton();
    JLabel lblTou = new JLabel();

    //显示表格的相关信息
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable tableTeamInfo = new JTable();
    Vector Teamx = new Vector();
    Vector Teamy = new Vector();
    Vector my = new Vector();

    DefaultTableModel dtm;

    //上传头像
    FileDialog fd = new FileDialog(this);

    //重点研究代码
    public void tableTeamInfo_mouseClicked(MouseEvent e) {
        int rowNum = tableTeamInfo.getSelectedRow(); //获得选中的行号
        Vector Team = (Vector) Teamy.get(rowNum); //取得选中行的对象
        this.txtTeam.setText(Team.get(0).toString());
        this.txtCode.setText(Team.get(1).toString());
        this.txtName.setText(Team.get(3).toString());
        this.cmbGrade.setSelectedItem(Team.get(2));
        this.txtBirth.setText(Team.get(4).toString()); //(年龄)将选中行的单值放到文本中去
        this.cmbMarriage.setSelectedItem(Team.get(5));
        this.cmbEducation.setSelectedItem(Team.get(6));
        this.cmbBefore.setSelectedItem(Team.get(7));
        this.txtJoin.setText(Team.get(8).toString());
        this.cmbStrong.setSelectedItem(Team.get(9));
        this.txtHome.setText(Team.get(10).toString());
        this.txtPhone1.setText(Team.get(11).toString());
        this.txtPhone2.setText(Team.get(12).toString());
        this.txtQQ.setText(Team.get(13).toString());
        this.txtEmail.setText(Team.get(14).toString());
        this.txtAreaRe.setText(Team.get(15).toString());
//       System.out.println("============" + my.get(0).toString());
//        Vector vect = (Vector) my.get(rowNum);
//String imgPath= vect.get(16).toString();
//        ImageIcon imgTou = new ImageIcon(imgPath);
//        this.lblTou.setIcon(imgTou);
//        System.out.println("imgUrl===========" +imgPath);
        //方法中要使用的变量的声明
        String edu = cmbEducation.getSelectedItem().toString();
        String before = cmbBefore.getSelectedItem().toString();
        String grade = cmbGrade.getSelectedItem().toString();
        String marriage = cmbMarriage.getSelectedItem().toString();
        String strong = cmbStrong.getSelectedItem().toString();
        picture();

    }
}
