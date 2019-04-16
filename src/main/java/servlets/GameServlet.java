package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "GameServlet", urlPatterns = "/game")
public class GameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<String> words;
        synchronized (session) {
            String word = (String) session.getAttribute("word");
            String wordMask = (String) session.getAttribute("wordMask");
            String letter = (String) request.getAttribute("letter");

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<String> words;
        synchronized (session) {
            String word = (String) session.getAttribute("word");
            if (word == null) {
//                    words = (List<String>) session.getAttribute("wordList");
                words = new ArrayList<>();
                words.add("abecadlo");
                words.add("zpieca");
                words.add("spadlo");
                words.add("def");
                word = words.get(new Random().nextInt(words.size()));
                String wordMask;
                wordMask = word.replaceAll("\\w", "_");
                session.setAttribute("word", word);
                session.setAttribute("wordMask", wordMask);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp");
        requestDispatcher.forward(request, response);
    }
}
