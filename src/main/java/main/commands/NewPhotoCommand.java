package main.commands;

import main.Path;
import main.db.dao.AirplaneDAO;
import main.db.entities.Airplane;
import main.db.entities.Photo;
import main.db.entities.Specs;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/newPhoto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class NewPhotoCommand extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(execute(req, resp));
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Airplane airplane = (Airplane) session.getAttribute("airplane");
        Photo photo = new Photo();
        airplane.setPhoto(photo);

        Part part = request.getPart("photoFile");
        photo.setFile(part.getInputStream());

        new AirplaneDAO().newAirplane(airplane);

        return Path.PAGE__START;
    }
}
