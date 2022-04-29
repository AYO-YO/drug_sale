package cn.fanbaby.drug_sale.dao;

import cn.fanbaby.drug_sale.utils.DBUtils;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "GetCart", value = "/GetCart")
public class GetCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_id = request.getParameter("user_id");
        ResultSet rs = DBUtils.getCart(user_id);
        JSONArray jsonArray = new JSONArray();
        while (true) {
            try {
                if (!rs.next()) break;
                String drug_id = rs.getString("drug_id");
                String num = rs.getString("num");
                String name = "", price = null;
                ResultSet drug_rs = DBUtils.getMeds(drug_id);
                while (drug_rs.next()) {
                    name = drug_rs.getString("name");
                    price = drug_rs.getString("price");
                }
                JSONObject obj = new JSONObject();
                obj.put("drug_id", drug_id);
                obj.put("name", name);
                obj.put("price", price);
                obj.put("num", num);
                jsonArray.add(obj);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        out.println(jsonArray);
    }
}
