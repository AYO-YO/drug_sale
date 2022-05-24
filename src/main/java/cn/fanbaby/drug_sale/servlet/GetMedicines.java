package cn.fanbaby.drug_sale.servlet;

import cn.fanbaby.drug_sale.bean.Drug;
import cn.fanbaby.drug_sale.service.DrugService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetMedicines", urlPatterns = "/GetMedicines")
public class GetMedicines extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String drug_id = request.getParameter("drug_id");
        DrugService ds = new DrugService();
        List<Drug> drugs = new ArrayList<>();
        Drug drug = new Drug();
        JSONArray jsonArray = new JSONArray();
        if (drug_id == null) {
            try {
                drugs = ds.getMeds();
                for (Drug d : drugs) {
                    JSONObject obj = JSON.parseObject(d.toString());
                    jsonArray.add(obj);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                drug = ds.getMeds(drug_id);
                JSONObject obj = JSON.parseObject(drug.toString());
                jsonArray.add(obj);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        out.println(jsonArray);
    }
}
