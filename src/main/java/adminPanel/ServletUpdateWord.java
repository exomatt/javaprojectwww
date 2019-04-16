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

@WebServlet(name = "ServletUpdateWord", urlPatterns = "/updateWord")
public class ServletUpdateWord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String word = request.getParameter("word");
        HttpSession session = request.getSession();

        synchronized (session) {
            String[] wordToUpdate = (String[]) session.getAttribute("wordToUpdate");
            FileManager.deleteWordFromFile(wordToUpdate[1]);
            session.removeAttribute("wordToUpdate");
            FileManager.addNewWordToFile(new String[]{language, word});
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
