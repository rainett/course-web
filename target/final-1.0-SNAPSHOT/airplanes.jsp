<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Airplane" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.dao.AirplaneDAO" %>
<%@ page import="main.db.dao.AirplanesSorting" %>
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
        String sorting = request.getParameter("sorting");
        List<Airplane> airplanes = new AirplaneDAO().getAllAirplanes(sorting);
        String uri = request.getRequestURL()+"?sorting=";
    %>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">Airliner</a></td>
                <td><button id="login" onclick="document.location.href='<%=loginButtonReaddress%>'"><%=loginButtonText%></button></td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <tr class="floating-row">
                <td colspan="3">
                    <div class="dropdown">
                        <button class="dropbtn">Sort</button>
                        <div class="dropdown-content">
                            <a href="<%=uri+AirplanesSorting.SORT__FROM_BIG%>">From bigger</a>
                            <a href="<%=uri+AirplanesSorting.SORT__FROM_SMALL%>">From smaller</a>
                            <a href="<%=uri+AirplanesSorting.SORT__BY_ALPHABET%>">By alphabet</a>
                            <a href="<%=uri+AirplanesSorting.SORT__BY_TYPE%>">By type</a>
                        </div>
                    </div>
                </td>
            </tr>
            <tr class="floating-row-s">
                <td>Image</td>
                <td>Name</td>
                <td>Type</td>
            </tr>
            <% for (Airplane a : airplanes) { %>
            <tr class="floating-row-l-clickable"
                onclick="window.location.href = '<%=Path.PAGE__AIRPLANE%>?airplaneId=<%=a.getId()%>';">
                <td><img class="image" src="data:image/jpg;base64, <%=a.getPhoto().getBase64()%>" alt="Airplane image"></td>
                <td><%=a.getName()%></td>
                <td><%=a.getType()%></td>
            </tr>
            <% } %>
            <tr class="floating-row-s">
            </tr>
        </table>
    </div>

</body>
</html>