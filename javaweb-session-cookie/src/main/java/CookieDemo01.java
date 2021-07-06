import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CookieDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            out.write("上一次登录的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("LastLoginTime")){
                    String value = cookie.getValue();
                    long Date = Long.parseLong(value);
                    out.write(new Date(Date).toLocaleString());
                }

            }
        }else {
            out.write("这是第一次登录");
        }
        //给服务器端一个响应Cookie
        Cookie lastLoginTime = new Cookie("LastLoginTime", System.currentTimeMillis() + "");
        lastLoginTime.setMaxAge(24*60*60);
        resp.addCookie(lastLoginTime);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
