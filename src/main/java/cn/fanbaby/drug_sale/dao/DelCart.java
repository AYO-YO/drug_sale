package cn.fanbaby.drug_sale.dao;

import cn.fanbaby.drug_sale.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DelCart", value = "/DelCart")
public class DelCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String user_id = request.getParameter("user_id");
        String drug_id = request.getParameter("drug_id");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(DBUtils.delCart(user_id, drug_id));
    }
}
