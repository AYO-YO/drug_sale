package cn.fanbaby.drug_sale.servlet;

import cn.fanbaby.drug_sale.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        PrintWriter out = response.getWriter();
        UserService us = new UserService();
        try {
            out.println(us.register(user, pwd));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
