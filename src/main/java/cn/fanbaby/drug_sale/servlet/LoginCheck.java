package cn.fanbaby.drug_sale.servlet;

import cn.fanbaby.drug_sale.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginCheck", urlPatterns = "/LoginCheck")
public class LoginCheck extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        PrintWriter out = response.getWriter();
        UserService us = new UserService();
        try {
            out.println(us.login(user, pwd));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}