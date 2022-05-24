package cn.fanbaby.drug_sale.servlet;

import cn.fanbaby.drug_sale.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "DelCart", urlPatterns = "/DelCart")
public class DelCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        String drug_id = request.getParameter("drug_id");
        PrintWriter out = response.getWriter();
        CartService cs = new CartService();
        try {
            out.print(cs.delCart(user_id, drug_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
