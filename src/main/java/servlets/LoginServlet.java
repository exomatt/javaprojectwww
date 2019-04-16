package servlets;

import models.User;
import repo.UserRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login", initParams = {@WebInitParam(name = "login", value = "admin"), @WebInitParam(name = "password", value = "admin")})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
        String login = getInitParameter("login");
        String adminpass = getInitParameter("password");
        HttpSession session = request.getSession();
        UserRepo userRepo = new UserRepo();
        User byUsername = userRepo.getByUsername(username);
        if (byUsername != null) {
            if (byUsername.getPassword().equals(password)) {
                synchronized (session) {
                    session.setAttribute("login", username);
                }
                response.sendRedirect("game");
                return;
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (username.equals(login) && password.equals(adminpass)) {
            synchronized (session) {
                session.setAttribute("login", "admin");
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
        response.sendRedirect("game");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }
}
