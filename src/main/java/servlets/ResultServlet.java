package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ResultServlet", urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("continue") != null) {
            int lifes = (int) session.getAttribute("lives");
            if (lifes == 0) {
                synchronized (session) {
                    session.removeAttribute("points");
                }
            }
            response.sendRedirect("game");
            return;
        }
        if (request.getParameter("return") != null) {
            synchronized (session) {
                session.removeAttribute("points");
                session.removeAttribute("lives");
            }
            response.sendRedirect("leaderboard");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("result.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("result.jsp");
        requestDispatcher.forward(request, response);
    }
}
