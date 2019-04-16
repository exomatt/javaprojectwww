package servlets;

import utils.FileManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LanguageServlet", urlPatterns = "/chooseLanguage")
public class LanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        HttpSession session = request.getSession();
        if (language.equals("PL")) {
            List<String> allWords = FileManager.getAllWords(FileManager.PL);
            if (!allWords.isEmpty())
                synchronized (session) {
                    session.setAttribute("list", allWords);
                }
        } else if (language.equals("ENG")) {
            List<String> allWords = FileManager.getAllWords(FileManager.ENG);
            if (!allWords.isEmpty())
                synchronized (session) {
                    session.setAttribute("list", allWords);
                }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}
