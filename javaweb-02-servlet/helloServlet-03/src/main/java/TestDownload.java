import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class TestDownload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取下载文件的路径;
//        2.下载的文件名获取;
//        3.让浏览器支持下载;
//        4.获取下载文件的输入流;
//        5.创建缓冲区;
//        6.获取outputstream对象;
//        7.写入缓冲区;
//        8.输出到客户端;
        String realPath = "D:\\code\\javaweb-02-servlet\\helloServlet-03\\target\\classes\\images\\我是猪.png";
        System.out.println("文件路径："+realPath);
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
        resp.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        FileInputStream inputStream = new FileInputStream(realPath);
        int len = 0;
        byte[] buffer = new byte[1024];
        ServletOutputStream outputStream = resp.getOutputStream();
        while ((len = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        inputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
