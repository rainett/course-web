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
        if (session.getAttribute("username") != null) {
            response.sendRedirect(Path.PAGE__START);
        }
        String errorMessage = "";
        boolean error = false;
        if (session.getAttribute("inputError") != null) {
            errorMessage = (String) session.getAttribute("inputError");
            error = true;
        }
    %>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">Airliner</a></td>
                <td><button id="login" onclick=document.location.href='${Path.PAGE__APPLY}'>Send application</button></td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login">
            <table>
                <%  if(error)   {%>
                        <tr class="floating-row-s">
                            <td>
                                <div class="error-message"><%=errorMessage%></div>
                            </td>
                        </tr>
                <%  } %>
                <tr class="floating-row">
                    <td>
                        <input class="floating-input" type="text" name="username" placeholder="Username" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <input class="floating-input" type="password" name="password" placeholder="Password" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <button class="floating-button">Log in</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>