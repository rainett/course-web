package main.commands;

import main.Path;
import main.db.dao.ManagerDAO;
import main.db.entities.Manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Manager manager = new ManagerDAO().getManager(username);

        if (manager == null) {
            HttpSession session = request.getSession();
            session.setAttribute("inputError", "Wrong username");
            return Path.PAGE__LOGIN;
        }
        if (!manager.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("inputError", "Wrong password");
            return Path.PAGE__LOGIN;
        }
        else {
            HttpSession session = request.getSession();
            session.removeAttribute("inputError");
            session.setAttribute("username", manager.getUsername());
            session.setMaxInactiveInterval(24*60*60);
            return Path.PAGE__START;
        }
    }
}
