package Bai2;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/image2")
public class ImageServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream out;
         out = resp.getOutputStream();

        FileInputStream image = new FileInputStream("E:\\IMG\\image2.jpg");

        BufferedInputStream bin = new BufferedInputStream(image);
        BufferedOutputStream bout = new BufferedOutputStream(out);

        int ch =0;
        while((ch=bin.read())!=-1)
        {

            bout.write(ch);
        }
        bin.close();
        image.close();
        bout.close();
        out.close();
    }
}
