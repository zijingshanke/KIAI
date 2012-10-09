package insurance;

import javax.swing.UIManager;
import java.sql.*;
class main {
    static Connection con;
    static Statement stmt;
    public static void main(String[] args) {
        //l����ݿ�
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:insurance", "sa", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //ʹ�õ���ؼ�
        try {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
            com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //��¼����
        frmLogin login = new frmLogin();
        login.setUndecorated(true); //ȥ�����8
        login.setSize(650, 480);
        login.setLocationRelativeTo(null);
        login.setVisible(true);


    }
}
