package cn.fanbaby.drug_sale.dao;

import cn.fanbaby.drug_sale.utils.DBUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetMedicines", value = "/GetMedicines")
public class GetMedicines extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String drug_id = request.getParameter("drug_id");
        ResultSet rs;
        if (drug_id == null)
            rs = DBUtils.getMeds();
        else
            rs = DBUtils.getMeds(drug_id);
        ArrayList<String[]> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("_id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");
                String product = rs.getString("product_date");
                String shelfLife = rs.getString("shelf_life");
                String[] res = new String[]{id, name, price, stock, product, shelfLife};
                arr.add(res);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.size(); i++) {
            String[] strings = arr.get(i);
            sb.append("{");
            sb.append("\"id\":\"").append(strings[0]).append("\",");
            sb.append("\"name\":\"").append(strings[1]).append("\",");
            sb.append("\"price\":\"").append(strings[2]).append("\",");
            sb.append("\"stock\":\"").append(strings[3]).append("\",");
            sb.append("\"date\":\"").append(strings[4]).append("\",");
            sb.append("\"life\":\"").append(strings[5]).append("\"");
            sb.append("}");
            if (i < arr.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        out.println(sb);
    }
}
