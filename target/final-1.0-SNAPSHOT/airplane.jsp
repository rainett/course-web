<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Airplane" %>
<%@ page import="main.db.dao.AirplaneDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Airliner</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        String loginButtonText = "Log In";
        String loginButtonReaddress = Path.PAGE__LOGIN;
        if (session.getAttribute("username") != null) {
            loginButtonText = String.format("Hi, %s!", session.getAttribute("username"));
            loginButtonReaddress = Path.PAGE__USER;
        }
        if (request.getParameter("airplaneId") == null) {
            response.sendRedirect(Path.PAGE__AIRPLANES);
        }
        Airplane airplane = new AirplaneDAO().getAirplane(Integer.parseInt(request.getParameter("airplaneId")));
    %>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">Airliner</a></td>
                <td><button id="meals" onclick="document.location.href='${Path.PAGE__AIRPLANES}'">Airplanes</button></td>
                <td><button id="login" onclick="document.location.href='<%=loginButtonReaddress%>'"><%=loginButtonText%></button></td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <tr class="floating-row-s">
                <td style="font-size: 3vh" colspan="4">
                    <%=airplane.getName()%>
                </td>
            </tr>
            <tr class="floating-row-xl">
                <td colspan="4">
                    <img id="image-solo" class="image" src="data:image/jpg;base64, <%=airplane.getPhoto().getBase64()%>" alt="Airplane Image">
                </td>
            </tr>
            <tr class="floating-row-s">
                <td colspan="4">Specifications</td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
                <td>Type: <%=airplane.getType()%></td>
                <td>Crew: <%=airplane.getSpecs().getCrew()%></td>
                <td></td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
                <td>Length: <%=String.format("%.2f", airplane.getSpecs().getLen())%>m</td>
                <td>Wings span: <%=String.format("%.2f", airplane.getSpecs().getWingsSpan())%>m</td>
                <td></td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
                <td>Height: <%=String.format("%.2f", airplane.getSpecs().getHeight())%>m</td>
                <td>Empty Weight: <%=airplane.getSpecs().getEmptyWeight()%>kg</td>
                <td></td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
                <td>Weight: <%=airplane.getSpecs().getWeight()%>kg</td>
                <td>Maximum weight: <%=airplane.getSpecs().getMaxWeight()%>kg</td>
                <td></td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
                <td>Speed: <%=airplane.getSpecs().getSpeed()%>m/s</td>
                <td>Ceiling: <%=airplane.getSpecs().getCeiling()%>m</td>
                <td></td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
                <td>Range: <%=airplane.getSpecs().getRange()%>m</td>
                <td>Combat range: <%=airplane.getSpecs().getCombatRange()%>m</td>
                <td></td>
            </tr>
            <tr class="floating-row-s">
                <td colspan="4" class="description-cell"><%=airplane.getDescription()%></td>
            </tr>
            <tr class="floating-row-s">
                <td></td>
            </tr>
        </table>
    </div>

</body>
</html>