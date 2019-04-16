package adminPanel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletShowListOfWords", urlPatterns = "/admin")
public class ServletShowListOfWords extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(System.getProperty("user.dir"));
        //TODO - niech kazdy zmieni sobie patha na swojego
        List<String> readLines = Files.readAllLines(Paths.get("E:\\Repozytoria\\javaprojectwww\\src\\main\\resources\\db.txt"));
        List<String[]> splittedLines = new ArrayList<>();
        readLines.forEach(line -> {
            String[] strings = line.split(",");
            splittedLines.add(strings);
        });
        HttpSession session = request.getSession();
        synchronized (session) {
            session.setAttribute("listOfWords", splittedLines);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminPanel.jsp");
        requestDispatcher.forward(request, response);
    }
}
