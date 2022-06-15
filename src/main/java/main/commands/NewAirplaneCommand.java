package main.commands;

import main.Path;
import main.db.entities.Airplane;
import main.db.entities.Specs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class NewAirplaneCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Airplane airplane = new Airplane();
        Specs specs = new Specs();

        airplane.setName(request.getParameter("airplaneName"));
        airplane.setType(request.getParameter("airplaneType"));
        airplane.setDescription(request.getParameter("airplaneDescription"));
        airplane.setSpecs(specs);

        specs.setCrew(Integer.parseInt(request.getParameter("specsCrew")));
        specs.setLen(Double.parseDouble(request.getParameter("specsLen")));
        specs.setWingsSpan(Double.parseDouble(request.getParameter("specsWingsSpan")));
        specs.setHeight(Double.parseDouble(request.getParameter("specsHeight")));
        specs.setEmptyWeight(Integer.parseInt(request.getParameter("specsEmptyWeight")));
        specs.setMaxWeight(Integer.parseInt(request.getParameter("specsMaxWeight")));
        specs.setWeight(Integer.parseInt(request.getParameter("specsWeight")));
        specs.setSpeed(Integer.parseInt(request.getParameter("specsSpeed")));
        specs.setRange(Integer.parseInt(request.getParameter("specsRange")));
        specs.setCeiling(Integer.parseInt(request.getParameter("specsCeiling")));
        specs.setCombatRange(Integer.parseInt(request.getParameter("specsCombatRange")));

        HttpSession session = request.getSession();
        session.setAttribute("airplane", airplane);

        return Path.PAGE__ADD_PHOTO;
    }
}
