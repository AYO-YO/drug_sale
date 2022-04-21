package cn.fanbaby.drug_sale.utils;

import java.sql.*;

public class DBUtils {
    static Config conf = new Config();

    static Connection getConn() {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(conf.getURL(), conf.getUSER(), conf.getPWD());
            System.out.println("数据库连接成功！");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库连接失败！");
            throw new RuntimeException(e);
        }
        return conn;
    }


    public static boolean register(String name, String pwd) {
        Connection conn = getConn();
        String sql = "insert into user(name,pwd) values (?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, pwd);
            return pstm.executeUpdate() >= 1;

        } catch (SQLException e) {
            System.out.println("插入失败！");
            return false;
        }
    }

    public static boolean login(String name, String pwd) {
        Connection conn = getConn();
        String sql = "select * from user where name=? and pwd=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, pwd);
            ResultSet rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
