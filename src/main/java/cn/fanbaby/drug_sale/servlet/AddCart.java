package cn.fanbaby.drug_sale.servlet;

import cn.fanbaby.drug_sale.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCart", urlPatterns = "/AddCart")
public class AddCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String drug_id = request.getParameter("medid");
        String user_id = request.getParameter("user_id");
        response.setContentType("text/html;utf-8");
        PrintWriter out = response.getWriter();
        out.print(DBUtils.addCart(user_id, drug_id));
    }
}
