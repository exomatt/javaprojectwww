package adminPanel;

import utils.FileManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletProcessList", urlPatterns = "/process")
public class ServletProcessList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("updateButton") != null) {
            String word = request.getParameter("selected");
            String[] wordDetails = FileManager.getWordDetails(word);
            HttpSession session = request.getSession();
            synchronized (session) {
                session.setAttribute("wordToUpdate", wordDetails);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("updateWord.jsp");
            requestDispatcher.forward(request, response);
        } else if (request.getParameter("deleteButton") != null) {
            String[] checked = request.getParameterValues("checked");
            if (checked != null) {
                for (String word : checked) {
                    FileManager.deleteWordFromFile(word);
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
