package cn.fanbaby.drug_sale.dao;

import cn.fanbaby.drug_sale.bean.Cart;
import cn.fanbaby.drug_sale.bean.Drug;
import cn.fanbaby.drug_sale.utils.MyDB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DrugDao {
    public List<Drug> getMeds() throws SQLException {
        String sql = "select * from drug";
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        return qr.query(sql, new BeanListHandler<>(Drug.class));
    }

    public Drug getMeds(String drug_id) throws SQLException {
        String sql = "select * from drug where _id=?";
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        BeanHandler<Drug> bh = new BeanHandler<>(Drug.class);
        return qr.query(sql, bh, drug_id);
    }

    public boolean addCart(String userId, String drugId) throws SQLException {
        String sql = "insert into cart(user_id,drug_id) values (?,?)";
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        int row = qr.update(sql, userId, drugId);
        return row > 0;
    }

    public List<Cart> getCart(String userId) throws SQLException {
        String sql = "select drug_id,num from cart where user_id=?";
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        return qr.query(sql, new BeanListHandler<Cart>(Cart.class), userId);
    }

    public boolean delCart(String user_id, String drug_id) throws SQLException {
        String sql = "delete from cart where user_id=? and drug_id=?";
        QueryRunner qr = new QueryRunner(MyDB.getSource());
        int row = qr.update(sql, user_id, drug_id);
        return row > 0;
    }
}
