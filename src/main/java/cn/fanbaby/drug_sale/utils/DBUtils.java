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

    public static int login(String name, String pwd) {
        Connection conn = getConn();
        String sql = "select _id from user where name=? and pwd=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, pwd);
            ResultSet rs = pstm.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            else
                return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getMeds() {
        Connection conn = getConn();
        String sql = "select * from drug";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getMeds(String drug_id) {
        Connection conn = getConn();
        String sql = "select * from drug where _id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, drug_id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addCart(String userId, String drugId) {
        Connection conn = getConn();
        String sql = "insert into cart(user_id,drug_id) values (?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, drugId);
            return pstmt.executeUpdate() >= 1;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * 获取用户购物车的内容
     *
     * @param userId 用户id
     * @return 用户购物车内容及数量
     */
    public static ResultSet getCart(String userId) {
        Connection conn = getConn();
        String sql = "select drug_id,num from cart where user_id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        ResultSet rs = getMeds("1");
        while (rs.next()) {
            String drug_id = rs.getString("_id");
            String num = rs.getString("name");
            System.out.println(drug_id + " " + num);
        }
    }
}
