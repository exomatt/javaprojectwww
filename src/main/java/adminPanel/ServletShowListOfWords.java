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
import java.util.List;

@WebServlet(name = "ServletShowListOfWords", urlPatterns = "/admin")
public class ServletShowListOfWords extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> linesFromFile = FileManager.getLinesFromFile();
        HttpSession session = request.getSession();
        synchronized (session) {
            session.setAttribute("listOfWords", linesFromFile);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminPanel.jsp");
        requestDispatcher.forward(request, response);
    }
}
