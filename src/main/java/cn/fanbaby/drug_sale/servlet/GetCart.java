package cn.fanbaby.drug_sale.servlet;

import cn.fanbaby.drug_sale.bean.Cart;
import cn.fanbaby.drug_sale.bean.Drug;
import cn.fanbaby.drug_sale.service.CartService;
import cn.fanbaby.drug_sale.service.DrugService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetCart", urlPatterns = "/GetCart")
public class GetCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user_id = request.getParameter("user_id");
        CartService cs = new CartService();
        List<Cart> carts;
        try {
            carts = cs.getCart(user_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray();
        for (Cart cart : carts) {
            DrugService ds = new DrugService();
            Drug drug;
            try {
                drug = ds.getMeds(String.valueOf(cart.getDrug_id()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            JSONObject obj;
            obj = JSON.parseObject(drug.toString());
            obj.put("drug_id", cart.getDrug_id());
            obj.put("num", cart.getNum());
            jsonArray.add(obj);
        }
        out.println(jsonArray);
    }
}
