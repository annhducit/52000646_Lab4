package Bai4;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            part.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
        }
        req.setAttribute("message", "File has been uploaded");
        getServletContext().getRequestDispatcher("result.html").forward(req, resp);
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
    public File getFolderUpload() {
        File folderUpload = new File("E:\\Term2Year3\\JavaTecnology\\Practice\\Day4\\Lab04_52000646\\src\\main\\java\\FileUpload");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }


}
