package cn.fanbaby.drug_sale.dao;

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
}
