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
        synchronized (session) {
            String word = (String) session.getAttribute("word");
            String wordMask = (String) session.getAttribute("wordMask");
            String letter = (String) request.getParameter("letter");
            boolean rightAnswer = false;
            if (letter.length() == word.length()) {
                if (letter.equals(word)) {
                    rightAnswer = true;
                    session.setAttribute("wordMask", word);
                }
            } else if (letter.length() == 1) {
                StringBuilder updatedWordMask = new StringBuilder(wordMask);
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter.charAt(0)) {
                        rightAnswer = true;
                        updatedWordMask.setCharAt(i*2, word.charAt(i));
                    }
                }
                session.setAttribute("wordMask", updatedWordMask.toString());
            }

            Integer lifes = (Integer) session.getAttribute("lifes");
            Integer points = (Integer) session.getAttribute("points");
            if (!rightAnswer) {
                lifes--;
                session.setAttribute("lifes", lifes);
            } else {
                points++;
                session.setAttribute("points", points);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<String> words;
        synchronized (session) {
            String word;
            words = (List<String>) session.getAttribute("list");
            word = words.get(new Random().nextInt(words.size()));
            String wordMask;
            wordMask = word.replaceAll("\\w", "_ ");
            session.setAttribute("word", word);
            session.setAttribute("wordMask", wordMask);

            Integer lifes = 9;
            session.setAttribute("lifes", lifes);
            Integer points = 0;
            session.setAttribute("points", points);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp");
        requestDispatcher.forward(request, response);
    }
}
