package adminPanel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterAdminAuthorization")
public class FilterAdminAuthorization implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String login = null;
        boolean isLogged = false;
        HttpSession session = request.getSession();
        synchronized (session) {
            login = (String) session.getAttribute("login");
        }
        if (login != null) {
            if (login.matches("admin")) {
                chain.doFilter(req, resp);
            } else response.sendRedirect("index.jsp");
        } else response.sendRedirect("index.jsp");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
