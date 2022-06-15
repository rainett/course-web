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
        if (session.getAttribute("username") == null) {
            response.sendRedirect(Path.PAGE__LOGIN);
        }
    %>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">Airliner</a></td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" onclick=document.location.href='${Path.PAGE__NEW_AIRPLANE}'>Add new airplane</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button class="floating-button">Edit existing airplane</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button form="logoutForm" class="floating-button-danger">Log Out</button>
                </td>
            </tr>
        </table>
        <form id="logoutForm" action="controller">
            <input type="hidden" name="command" value="logout">
        </form>
    </div>

</body>
</html>