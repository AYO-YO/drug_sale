package cn.fanbaby.drug_sale.service;

import cn.fanbaby.drug_sale.bean.Cart;
import cn.fanbaby.drug_sale.dao.CartDao;

import java.sql.SQLException;
import java.util.List;

public class CartService {
    CartDao dao = new CartDao();

    public boolean addCart(String userId, String drugId) throws SQLException {
        return dao.addCart(userId, drugId);
    }

    public List<Cart> getCart(String userId) throws SQLException {
        return dao.getCart(userId);
    }

    public boolean delCart(String user_id, String drug_id) throws SQLException {
        return dao.delCart(user_id, drug_id);
    }
}
