package cn.fanbaby.drug_sale.dao;

import cn.fanbaby.drug_sale.bean.User;
import cn.fanbaby.drug_sale.utils.MyDB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    public boolean register(String name, String pwd) throws SQLException {
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        String sql = "insert into user(name,pwd) values (?,?)";
        int row = qr.update(sql, name, pwd);
        return row > 0;
    }

    public int login(String name, String pwd) throws SQLException {
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        String sql = "select * from user where name=? and pwd=?";
        User user;
        BeanHandler<User> bh = new BeanHandler<>(User.class);
        user = qr.query(sql, bh, name, pwd);
        return user.get_id();
    }
}
