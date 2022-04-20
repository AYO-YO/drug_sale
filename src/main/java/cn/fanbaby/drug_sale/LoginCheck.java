package cn.fanbaby.drug_sale;

import cn.fanbaby.drug_sale.utils.DBUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginCheck", value = "/LoginCheck")
public class LoginCheck extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if (DBUtils.login(user, pwd)) out.println("登录成功！");
        else out.println("登录失败！请检查用户名及密码！");
    }

    public void destroy() {
    }
}