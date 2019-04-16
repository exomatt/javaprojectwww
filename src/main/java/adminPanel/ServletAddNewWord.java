package adminPanel;

import utils.FileManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAddNewWord", urlPatterns = "/addNewWord")
public class ServletAddNewWord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String word = request.getParameter("word");
        FileManager.addNewWordToFile(new String[]{language, word});
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addNewWord.jsp");
        requestDispatcher.forward(request, response);
    }
}
