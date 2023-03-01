package Bai2;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        String queryString = req.getQueryString();
        String pathName = "image2.jpg";
        String path = "E:\\IMG\\";
        if(queryString == null) {
            printWriter.write("File not found");

        }else if(queryString.equals("file=image2.jpg")) {
            resp.setContentType("image/x-citrix-jpeg");
            resp.setHeader("Content-Disposition",
                        "attachment; filename=\""
                                + pathName + "\"");
            FileInputStream inputStream  = new FileInputStream(path + pathName);
            int in;
            while ((in = inputStream.read()) != -1) {
                printWriter.write(in);
            }
            inputStream.close();
            printWriter.close();
        }else {
            printWriter.write("File is not available");
        }
    }

}
