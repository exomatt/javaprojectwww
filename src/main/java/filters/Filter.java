package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter", urlPatterns = {"/game", "/result"})
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session == null)
            response.sendRedirect("index.jsp");
        String login = (String) session.getAttribute("login");
        if (login == null) {
            response.sendRedirect("index.jsp");
        } else {
            if (!login.equals("admin"))
                chain.doFilter(req, resp);
            else chain.doFilter(req, resp);//todo zmienic na amidna jak juz bedzie
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
