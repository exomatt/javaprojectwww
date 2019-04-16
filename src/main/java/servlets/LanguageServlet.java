package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LanguageServlet", urlPatterns = "/chooseLanguage")
public class LanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        if (language.equals("PL")) {
            //todo pobranie słow polskich ustawienie ciasteczka
        } else if (language.equals("ENG")) {
            //todo pobranie słow angielskich
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}
