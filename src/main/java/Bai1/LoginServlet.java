package Bai1;

import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Bat dau voi Servlet!!!");
    }
    @Override
    public void destroy() {
        System.out.println("Ket Thuc voi Servlet!!!");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(checkAccount(username, password)){
            printWriter.println("Name/Password match");
        }else{
            printWriter.println("Name/Password does not match");
        }
    }
    public boolean checkAccount(String username, String password) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("admin", "12345");
        map.put("anhduc", "anhduc");
        map.put("duc", "123");
        map.put("ntd", "12345");
        for (Map.Entry entry : map.entrySet())
            if (username.equals(entry.getKey()) && password.equals(entry.getValue()))
                return true;
        return false;
    }


}
