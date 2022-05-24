package cn.fanbaby.drug_sale.service;

import cn.fanbaby.drug_sale.bean.Drug;
import cn.fanbaby.drug_sale.dao.DrugDao;

import java.sql.SQLException;
import java.util.List;

public class DrugService {
    DrugDao dao = new DrugDao();

    public List<Drug> getMeds() throws SQLException {
        return dao.getMeds();
    }

    public Drug getMeds(String drug_id) throws SQLException {
        return dao.getMeds(drug_id);
    }
}
