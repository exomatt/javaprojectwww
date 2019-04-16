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
        Integer lives;
        String word;
        String wordMask;
        synchronized (session) {
            word = (String) session.getAttribute("word");
            wordMask = (String) session.getAttribute("wordMask");
            String letter = (String) request.getParameter("letter");
            boolean rightAnswer = false;
            if (letter.length() == word.length()) {
                if (letter.equals(word)) {
                    rightAnswer = true;
                    session.setAttribute("wordMask", word);
                }
            } else if (letter.length() >= 1) {
                StringBuilder updatedWordMask = new StringBuilder(wordMask);
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter.charAt(0)) {
                        rightAnswer = true;
                        updatedWordMask.setCharAt(i * 2, word.charAt(i));
                    }
                }
                session.setAttribute("wordMask", updatedWordMask.toString());
            }
            wordMask = (String) session.getAttribute("wordMask");
            if (word.equals(wordMask.replaceAll("\\s", ""))) {
                session.setAttribute("wordMask", word);
            }
            lives = (Integer) session.getAttribute("lives");
            Integer points = (Integer) session.getAttribute("points");
            if (!rightAnswer) {
                lives--;
                session.setAttribute("lives", lives);

            } else {
                points++;
                session.setAttribute("points", points);
            }
        }
        if (lives == 0 || word.equals(wordMask.replaceAll("\\s", ""))) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("result.jsp");
            requestDispatcher.forward(request, response);
        }else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp");
            requestDispatcher.forward(request, response);
        }
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

            Integer lives = 9;
            session.setAttribute("lives", lives);
            if (session.getAttribute("points") == null) {
                Integer points = 0;
                session.setAttribute("points", points);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp");
        requestDispatcher.forward(request, response);
    }
}
