package cn.fanbaby.drug_sale.service;

import cn.fanbaby.drug_sale.dao.UserDao;

import java.sql.SQLException;

public class UserService {
    UserDao dao = new UserDao();

    public boolean register(String name, String pwd) throws SQLException {
        return dao.register(name, pwd);
    }

    public int login(String name, String pwd) throws SQLException {
        return dao.login(name, pwd);
    }
}
