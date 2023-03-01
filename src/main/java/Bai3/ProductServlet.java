package Bai3;

import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "productServlet", urlPatterns = "/productServlet")
public class ProductServlet extends HttpServlet {
    private Gson gson = new Gson();
    private  List<Product> productList;

    @Override
    public void init() throws ServletException {

    }
    public List<Product> data() {
        productList = new ArrayList<Product>();
        productList.add(new Product(1, "Bánh", 10000));
        productList.add(new Product(2, "Kẹo", 12000));
        productList.add(new Product(3, "Xúc xích", 14000));
        productList.add(new Product(4, "Sữa chua", 18000));
        productList.add(new Product(5, "Nước ngọt", 10000));
        productList.add(new Product(6, "Mì tôm", 5000));
        return productList;
    }
    @Override
    public void destroy() {
        System.out.println("Ket Thuc voi Servlet!!!");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if(req.getQueryString() == null) {
            String productJsonString = this.gson.toJson(data());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(productJsonString);
            out.flush();
        }
        else
        {
            String id = req.getParameter("id");
            int intID = Integer.parseInt(id);
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == intID) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    out.print(productList.get(i).toString());
                    break;
                }
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        Product product = new Product(id, name, price);
        productList.add(product);
        out.print("Add product successfully");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        int intID = Integer.parseInt(id);
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == intID) {
                productList.remove(i);
                break;
            }
        }
        out.print("Remove product successfully");
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        int intID = Integer.parseInt(id);
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        Product product = new Product(intID, name, price);
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == intID) {
                productList.set(i, product);
                break;
            }
        }
        out.print("Update product successfully");
    }
}
