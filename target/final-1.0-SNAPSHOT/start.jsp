<%@ page import="main.Path" %>
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
    %>

    <video autoplay muted loop class="background-video">
        <source src="images/video.mp4">
    </video>

    <div id="headerMain">
        <div id="headerBlur"></div>
        <div id="headerBlackout"></div>
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo-start">Airliner</a></td>
                <td><button id="airplanes-start" onclick="document.location.href='${Path.PAGE__AIRPLANES}'">Airplanes</button></td>
                <td><button id="login-start" onclick="document.location.href='<%=loginButtonReaddress%>'"><%=loginButtonText%></button></td>
            </tr>
        </table>
    </div>

</body>
</html>