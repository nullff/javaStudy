import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        session.setAttribute("name", URLEncoder.encode("小凯","utf-8"));
        String sessionId = session.getId();
        if (session.isNew()){
            resp.getWriter().write("sessionID创建成功，id:"+sessionId);
        }else {
            resp.getWriter().write("sessionID已经存在，id:"+sessionId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
