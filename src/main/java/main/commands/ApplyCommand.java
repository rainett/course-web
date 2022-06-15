package main.commands;

import main.Path;
import main.db.dao.ManagerDAO;
import main.db.entities.Manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Manager manager = new Manager();
        HttpSession session = request.getSession();
        manager.setUsername(request.getParameter("username"));
        manager.setPassword(request.getParameter("password"));
        manager.setEmail(request.getParameter("email"));
        manager.setState("active");
        String passwordRepeated = request.getParameter("passwordRepeated");
        Pattern pattern = Pattern.compile("^\\w+?$");
        Matcher matcher = pattern.matcher(manager.getUsername());

        if (manager.getUsername().length() < 4 || manager.getUsername().length() > 50) {
            session.setAttribute("inputError", "Username should be between 4 to 50 characters");
            return Path.PAGE__APPLY;
        }
        if (!matcher.find()) {
            session.setAttribute("inputError", "Username should contain only latin letters, numbers or underscores");
            return Path.PAGE__APPLY;
        }

        pattern = Pattern.compile("^\\w+@\\w+.\\w+$");
        matcher = pattern.matcher(manager.getEmail());
        if (!matcher.find()) {
            session.setAttribute("inputError", "Incorrect e-mail");
            return Path.PAGE__APPLY;
        }

        pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,50}$");
        matcher = pattern.matcher(manager.getPassword());
        if (!matcher.find()) {
            session.setAttribute("inputError", "Password should be between 6 and 50 characters and contain at least: 1 capital, 1 lower letter and a digit");
            return Path.PAGE__APPLY;
        }
        if (!manager.getPassword().equals(passwordRepeated)) {
            session.setAttribute("inputError", "Passwords don`t match");
            return Path.PAGE__APPLY;
        }
        new ManagerDAO().newManager(manager);
        session.removeAttribute("inputError");
        session.setAttribute("username", manager.getUsername());
        session.setMaxInactiveInterval(24*60*60);
        return Path.PAGE__START;
    }
}
